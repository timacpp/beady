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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	private String description;

	@NotNull
	@Min(0)
	@Column(precision = 5, scale = 2)
	private BigDecimal price;

	@NotNull
	@Min(0)
	private Long quantity;

	@Range(min = 1, max = 5)
	@Column(precision = 3, scale = 2)
	private BigDecimal averageRating;
}
