package beady.shop.item.dto;

import beady.shop.item.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRatingDTO implements Serializable {

	@NotNull
	@JsonProperty("itemId")
	private Long itemId;

	@NotNull
	@Range(min = Item.MIN_RATING, max = Item.MAX_RATING)
	@JsonProperty("latestRating")
	private Integer latestRating;

	@NotNull
	@Min(0)
	@JsonProperty("feedbacks")
	private Integer feedbacks;
}
