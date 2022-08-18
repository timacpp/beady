package shop.beady.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.beady.generic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractEntity {

	@Column(nullable = false)
	private String emailAddress;

	private String shipmentAddress;
}
