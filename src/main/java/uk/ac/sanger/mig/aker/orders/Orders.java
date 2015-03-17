package uk.ac.sanger.mig.aker.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pi1
 * @since March 2015
 */
@SpringBootApplication
@ComponentScan
public class Orders {

	public static void main(String[] args) {
		SpringApplication.run(Orders.class, args);
	}

}
