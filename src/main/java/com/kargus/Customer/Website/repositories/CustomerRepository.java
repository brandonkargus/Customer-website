package com.kargus.Customer.Website.repositories;

import com.kargus.Customer.Website.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
