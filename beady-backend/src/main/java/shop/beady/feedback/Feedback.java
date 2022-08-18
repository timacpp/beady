package shop.beady.feedback;

import lombok.*;
import shop.beady.generic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Feedback extends AbstractEntity {

	@Column(nullable = false)
	private String opinion;

	private String author;
}
