package beady.shop.order.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderDTO {

	@Email
	@NotNull
	private String email;

	@NotNull
	private Long itemId;

	@NotNull
	@Min(1)
	private Integer quantity;
}
