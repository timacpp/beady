package beady.shop.feedback.dto;

import beady.shop.feedback.Feedback;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRatingDTO implements Serializable {

	@NotNull
	private Long itemId;

	@NotNull
	@Range(min = Feedback.MIN_RATING, max = Feedback.MAX_RATING)
	private Integer latestRating;

	@NotNull
	@Min(0)
	private Integer feedbacks;
}
