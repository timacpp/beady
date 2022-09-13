package beady.shop.order;

import beady.shop.order.dto.CreateOrderDTO;
import beady.shop.order.exception.IllegalOrderException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;

	private final RestTemplate restTemplate;

	public void createOrder(CreateOrderDTO dto) throws IllegalOrderException {
		requestItemQuantityUpdate(dto.getItemId(), dto.getQuantity());

		Order order = Order.builder()
				.email(dto.getEmail())
				.itemId(dto.getItemId())
				.quantity(dto.getQuantity())
				.build();

		orderRepository.save(order);
	}

	private void requestItemQuantityUpdate(Long itemId, Integer quantity) throws IllegalOrderException {
		try {
			postItemUpdate(itemId, quantity);
		} catch (HttpClientErrorException exception) {
			throw new IllegalOrderException(exception.getResponseBodyAsString());
		}
	}

	private void postItemUpdate(Long itemId, Integer quantity) throws HttpClientErrorException {
		restTemplate.postForEntity(
				"http://localhost:8000/item/reduce-quantity",
				Map.of("itemId", itemId, "reduceBy", quantity),
				String.class
		);
	}
}
