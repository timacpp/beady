package beady.shop.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Long quantity;
}
