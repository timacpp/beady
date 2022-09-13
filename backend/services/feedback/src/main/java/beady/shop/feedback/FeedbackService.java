package beady.shop.feedback;

import beady.shop.feedback.dto.CreateFeedbackDTO;
import beady.shop.feedback.exception.InvalidFeedbackException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackService {

	private final FeedbackRepository feedbackRepository;

	public void createFeedback(CreateFeedbackDTO dto) throws InvalidFeedbackException {
		requestItemRatingUpdate(dto.getItemId(), dto.getRating());

		Feedback feedback = Feedback.builder()
				.itemId(dto.getItemId())
				.rating(dto.getRating())
				.build();

		feedbackRepository.save(feedback);
	}

	private void requestItemRatingUpdate(Long itemId, Integer rating) throws InvalidFeedbackException {
		try {
			Integer feedbacks = feedbackRepository.countByItemId(itemId).intValue();
			postItemRatingUpdate(itemId, rating, feedbacks);
		} catch (Exception exception) {
			throw new InvalidFeedbackException(exception.getMessage());
		}
	}

	private void postItemRatingUpdate(Long itemId, Integer rating, Integer feedbacks) {

	}
}
