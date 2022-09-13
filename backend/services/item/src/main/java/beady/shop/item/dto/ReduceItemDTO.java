package beady.shop.item.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReduceItemDTO {

	@NotNull
	private Long itemId;

	@NotNull
	@Min(0)
	private Integer reduceBy;
}
