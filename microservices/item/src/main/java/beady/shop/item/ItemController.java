package beady.shop.item;

import beady.shop.item.dto.ReduceItemDTO;
import beady.shop.item.exception.ItemException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PutMapping("/reduce")
	public ResponseEntity<?> reduceItem(ReduceItemDTO dto) {
		try {
			itemService.reduceItem(dto);
			return ResponseEntity.ok().build();
		} catch (ItemException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
