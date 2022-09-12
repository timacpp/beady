package beady.shop.item;

import beady.shop.item.dto.ReduceItemDTO;
import beady.shop.item.exception.ItemException;
import beady.shop.item.exception.NotEnoughItemsException;
import beady.shop.item.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	public void reduceItem(ReduceItemDTO dto) throws ItemException {
		Item item = findItemById(dto.getItemId());
		reduceItemQuantityBy(item, dto.getReduceBy());
	}
	
	private Item findItemById(Long itemId) throws ItemNotFoundException {
		return itemRepository.findById(itemId).orElseThrow(() ->
				new ItemNotFoundException(String.format("Item with id %d does not exist", itemId)));
	}
	
	private void reduceItemQuantityBy(Item item, Long reduceBy) throws NotEnoughItemsException {
		if (item.getQuantity() < reduceBy) {
			throw new NotEnoughItemsException(String.format(
					"Cannot reduce item quantity from %d by %d", item.getQuantity(), reduceBy));
		}

		item.setQuantity(item.getQuantity() - reduceBy);
		itemRepository.save(item);
	}
}
