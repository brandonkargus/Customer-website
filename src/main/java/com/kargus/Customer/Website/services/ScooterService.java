package com.kargus.Customer.Website.services;

import com.kargus.Customer.Website.models.Scooter;

import java.util.List;

public interface ScooterService {

    List<Scooter> getAllScooters();

    Scooter saveScooter(Scooter scooter);

    Scooter getScooter(Long id);

    void deleteScooter(Long id);

    List<Scooter> saveAllScooter(List<Scooter> scooterList);

    void assignScooterToCustomer(Long scooterId, Long customerId);
}
