package beady.shop.order;

import beady.shop.order.dto.CreateOrderDTO;
import beady.shop.order.exception.IllegalOrderException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderDTO dto) {
		try {
			orderService.createOrder(dto);
			return ResponseEntity.ok().build();
		} catch (IllegalOrderException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
