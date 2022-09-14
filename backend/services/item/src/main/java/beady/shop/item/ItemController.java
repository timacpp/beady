package beady.shop.item;

import beady.shop.item.dto.ReduceItemDTO;
import beady.shop.item.dto.UpdateRatingDTO;
import beady.shop.item.exception.ItemException;
import beady.shop.item.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@GetMapping("/stock")
	public ResponseEntity<?> getItemsInStock() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@PostMapping("/reduce-quantity")
	public ResponseEntity<?> reduceQuantity(@RequestBody @Valid ReduceItemDTO dto) {
		try {
			itemService.reduceQuantity(dto);
			return ResponseEntity.ok().build();
		} catch (ItemException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
