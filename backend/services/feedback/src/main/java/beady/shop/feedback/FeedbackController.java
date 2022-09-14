package beady.shop.feedback;

import beady.shop.feedback.dto.CreateFeedbackDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

	private final FeedbackService feedbackService;

	@PostMapping("/create")
	public ResponseEntity<?> createFeedback(@RequestBody @Valid CreateFeedbackDTO dto) {
		feedbackService.createFeedback(dto);
		return ResponseEntity.ok().build();
	}
}
