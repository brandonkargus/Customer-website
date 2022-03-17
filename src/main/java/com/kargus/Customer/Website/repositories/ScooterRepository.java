package com.kargus.Customer.Website.repositories;

import com.kargus.Customer.Website.models.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

}
