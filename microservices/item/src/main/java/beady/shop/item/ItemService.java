package beady.shop.item;

import beady.shop.item.dto.ReduceItemDTO;
import beady.shop.item.dto.UpdateRatingDTO;
import beady.shop.item.exception.ItemException;
import beady.shop.item.exception.NotEnoughItemsException;
import beady.shop.item.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public void updateRating(UpdateRatingDTO dto) throws ItemNotFoundException {
		Item item = findItemById(dto.getItemId());
		updateItemRating(item, dto.getLatestRating(), dto.getFeedbacks());
	}

	private void updateItemRating(Item item, Integer latestRating, Integer feedbacks) {
		updateItemRating(item, new BigDecimal(latestRating), new BigDecimal(feedbacks));
	}

	private void updateItemRating(Item item, BigDecimal latestRating, BigDecimal feedbacks) {
		BigDecimal newRating = item.getRating().add(latestRating)
				.divide(feedbacks, Item.RATING_SCALE, RoundingMode.HALF_UP);

		item.setRating(newRating);
		itemRepository.save(item);
	}

	public void reduceQuantity(ReduceItemDTO dto) throws ItemException {
		Item item = findItemById(dto.getItemId());
		reduceItemQuantityBy(item, dto.getReduceBy());
	}
	
	private Item findItemById(Long itemId) throws ItemNotFoundException {
		return itemRepository.findById(itemId).orElseThrow(() ->
				new ItemNotFoundException(String.format("Item with id %d does not exist", itemId)));
	}
	
	private void reduceItemQuantityBy(Item item, Integer reduceBy) throws NotEnoughItemsException {
		if (item.getQuantity() < reduceBy) {
			throw new NotEnoughItemsException(String.format(
					"Cannot reduce item quantity from %d by %d", item.getQuantity(), reduceBy));
		}

		item.setQuantity(item.getQuantity() - reduceBy);
		itemRepository.save(item);
	}
}
