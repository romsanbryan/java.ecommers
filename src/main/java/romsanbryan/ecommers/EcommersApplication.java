package romsanbryan.ecommers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import romsanbryan.ecommers.annotations.Generated;
import romsanbryan.ecommers.component.CartComponent;

@SpringBootApplication
@Generated
@EnableScheduling
public class EcommersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommersApplication.class, args);
		SpringApplication.run(CartComponent.class);
	}

}
