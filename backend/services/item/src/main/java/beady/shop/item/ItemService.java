package beady.shop.item;

import beady.shop.item.dto.ReduceItemDTO;
import beady.shop.item.dto.UpdateRatingDTO;
import beady.shop.item.exception.ItemException;
import beady.shop.item.exception.NotEnoughItemsException;
import beady.shop.item.exception.ItemNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@EnableRabbit
@AllArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@RabbitListener(queues = "queue.rating")
	public void updateRating(Message message) throws ItemNotFoundException, IOException {
		UpdateRatingDTO dto = new ObjectMapper().readValue(message.getBody(), UpdateRatingDTO.class);

		Item item = findItemById(dto.getItemId());
		updateItemRating(item, dto.getLatestRating(), dto.getFeedbacks());
	}

	private void updateItemRating(Item item, Integer latestRating, Integer feedbacks) {
		updateItemRating(item, new BigDecimal(latestRating), new BigDecimal(feedbacks));
	}

	private void updateItemRating(Item item, BigDecimal latestRating, BigDecimal feedbacks) {
		BigDecimal newRating = item.getRating().multiply(feedbacks).add(latestRating)
				.divide(feedbacks.add(BigDecimal.ONE), Item.RATING_SCALE, RoundingMode.HALF_UP);

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
					"Not enough items, available: %d, requested: %d", item.getQuantity(), reduceBy));
		}

		item.setQuantity(item.getQuantity() - reduceBy);
		itemRepository.save(item);
	}
}
