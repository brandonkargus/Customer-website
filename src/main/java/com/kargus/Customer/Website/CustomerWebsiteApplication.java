package com.kargus.Customer.Website;

import com.kargus.Customer.Website.models.Customer;
import com.kargus.Customer.Website.models.Scooter;
import com.kargus.Customer.Website.services.CustomerService;
import com.kargus.Customer.Website.services.ScooterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class CustomerWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWebsiteApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadInitialData(CustomerService customerService, ScooterService scooterService) {
		return (args) -> {
			if (customerService.getAllCustomers().isEmpty()) {
				customerService.saveAllCustomer(Arrays.asList(
						Customer.builder().fullName("Customer 1").emailAddress("customer1@gmail.com").address("Customer Address One").age(30).build(),
						Customer.builder().fullName("Customer 2").emailAddress("customer2@gmail.com").address("Customer Address Two").age(28).build(),
						Customer.builder().fullName("Customer 3").emailAddress("customer3@gmail.com").address("Customer Address Three").age(32).build()));
			}
			if (scooterService.getAllScooters().isEmpty()) {
				scooterService.saveAllScooter(Arrays.asList(
						Scooter.builder().year(2020).manufacturer("Honda").model("HZF").engineSize(125).build(),
						Scooter.builder().year(2005).manufacturer("Kawasaki").model("Crawler").engineSize(250).build(),
						Scooter.builder().year(2014).manufacturer("Yamaha").model("Street Eater").engineSize(150).build(),
						Scooter.builder().year(1979).manufacturer("Suzuki").model("Roadie").engineSize(85).build()));

			}

		};
	}
}
