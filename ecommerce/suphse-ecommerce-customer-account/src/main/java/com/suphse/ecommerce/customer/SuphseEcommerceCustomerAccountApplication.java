package com.suphse.ecommerce.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SuphseEcommerceCustomerAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuphseEcommerceCustomerAccountApplication.class, args);
	}

}
