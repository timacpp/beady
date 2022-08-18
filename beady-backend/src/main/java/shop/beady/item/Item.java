package shop.beady.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.beady.generic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String code;

	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Long quantity;
}
