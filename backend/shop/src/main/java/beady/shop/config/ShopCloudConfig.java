package beady.shop.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopCloudConfig {

	@Bean
	public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/item/**").uri("http://localhost:8081"))
				.route(r -> r.path("/order/**").uri("http://localhost:8082"))
				.route(r -> r.path("/feedback/**").uri("http://localhost:8083"))
				.build();
	}
}
