package beady.shop.item.dto;

import beady.shop.item.Item;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateRatingDTO {

	@NotNull
	private Long itemId;

	@NotNull
	@Range(min = Item.MIN_RATING, max = Item.MAX_RATING)
	private Integer latestRating;

	@NotNull
	@Min(0)
	private Integer feedbacks;
}
