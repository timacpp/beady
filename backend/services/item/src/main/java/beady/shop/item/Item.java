package beady.shop.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
	public static final int MIN_RATING = 1;

	public static final int MAX_RATING = 5;

	public static final int RATING_SCALE = 2;

	public static final int PRICE_SCALE = 2;

	public static final int RATING_PRECISION = 3;

	public static final int PRICE_PRECISION = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@Min(0)
	@Column(precision = PRICE_PRECISION, scale = PRICE_SCALE)
	private BigDecimal price;

	@NotNull
	@Min(0)
	private Integer quantity;

	@Range(min = MIN_RATING, max = MAX_RATING)
	@Column(precision = RATING_PRECISION, scale = RATING_SCALE)
	private BigDecimal rating;
}
