package beady.shop.feedback;

import beady.shop.feedback.dto.CreateFeedbackDTO;
import beady.shop.feedback.exception.InvalidFeedbackException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

	private final FeedbackService feedbackService;

	@PostMapping("/create")
	public ResponseEntity<?> createFeedback(@Valid CreateFeedbackDTO dto) {
		try {
			feedbackService.createFeedback(dto);
			return ResponseEntity.ok().build();
		} catch (InvalidFeedbackException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
