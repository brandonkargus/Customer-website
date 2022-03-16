package com.kargus.Customer.Website.repositories;

import com.kargus.Customer.Website.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE customers SET scooter_id = ?2 WHERE id = ?1")
    void addScooterToCustomer(Long customerId, Long scooterId);

    @Transactional
    @Modifying
    @Query("UPDATE customers SET scooter_id = NULL WHERE id = ?1")
    void deleteScooterFromCustomer(Long customerId);


}
