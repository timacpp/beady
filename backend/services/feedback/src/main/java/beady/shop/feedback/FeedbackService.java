package beady.shop.feedback;

import beady.shop.feedback.dto.CreateFeedbackDTO;
import beady.shop.feedback.dto.UpdateRatingDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

	private final FeedbackRepository feedbackRepository;

	@Autowired
	private DirectExchange exchange;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public FeedbackService(FeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}

	public void createFeedback(CreateFeedbackDTO dto) {
		queueItemRatingUpdate(dto.getItemId(), dto.getRating());

		Feedback feedback = Feedback.builder()
				.itemId(dto.getItemId())
				.rating(dto.getRating())
				.build();

		feedbackRepository.save(feedback);
	}

	private void queueItemRatingUpdate(Long itemId, Integer rating) {
		Integer feedbacks = feedbackRepository.countByItemId(itemId).intValue();
		UpdateRatingDTO dto = UpdateRatingDTO.builder()
				.itemId(itemId)
				.latestRating(rating)
				.feedbacks(feedbacks)
				.build();

		queueItemRatingUpdate(dto);
	}

	private void queueItemRatingUpdate(UpdateRatingDTO dto) {
		rabbitTemplate.convertAndSend(exchange.getName(), "routing.rating", dto);
	}
}
