package beady.shop.feedback.dto;

import beady.shop.feedback.Feedback;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class CreateFeedbackDTO {

	@NotNull
	private Long itemId;

	@Range(min = Feedback.MIN_RATING, max = Feedback.MAX_RATING)
	private Integer rating;
}
