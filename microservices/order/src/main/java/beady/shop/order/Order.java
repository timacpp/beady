package beady.shop.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String emailAddress;

	@Column(nullable = false)
	private String shipmentAddress;

	@Column(nullable = false)
	private Long itemId;

	@Column(nullable = false)
	private Long quantity;
}
