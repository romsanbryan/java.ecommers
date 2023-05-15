package romsanbryan.ecommers.component;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import romsanbryan.ecommers.services.CartService;

@Component
public class CartComponent {
	private static final Logger log = LoggerFactory
			.getLogger(CartComponent.class);

	@Autowired
	private Environment environment;

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info(CartService.hashMap.toString());

		CartService.hashMap.entrySet().stream().filter(x -> new Date().getTime()
				- x.getValue().getCreateTime().getTime() >= Integer.valueOf(
						environment.getProperty("cart.alive.time.value")))
				.map(x -> CartService.hashMap.remove(x.getKey()))
				.collect(Collectors.toList());

	}

}
