package com.kargus.Customer.Website.repositories;

import com.kargus.Customer.Website.models.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE scooters SET customer_id = ?2 WHERE id = ?1")
    void addCustomerToScooter(Long scooterId, Long customerId);

}
