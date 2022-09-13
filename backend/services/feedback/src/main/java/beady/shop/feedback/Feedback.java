package beady.shop.feedback;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	public static final int MIN_RATING = 1;

	public static final int MAX_RATING = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;

	@NotNull
	private Long itemId;

	@NotNull
	@Range(min = 1, max = 5)
	private Integer rating;
}
