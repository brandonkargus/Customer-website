package com.kargus.Customer.Website.services;

import com.kargus.Customer.Website.models.Scooter;
import com.kargus.Customer.Website.repositories.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScooterServiceImpl implements ScooterService {

    @Autowired
    final ScooterRepository scooterRepository;

    // The findAll function gets all the scooters by doing a SELECT query in the DB.
    @Override
    public List<Scooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    // The save function uses an INSERT query in the DB.
    @Override
    @Transactional
    public Scooter saveScooter(Scooter scooter) throws IllegalStateException {
        scooter.validate();
        return scooterRepository.save(scooter);
    }

    // The findById function uses a SELECT query with a WHERE clause in the DB.
    @Override
    public Scooter getScooter(Long id) {
        return scooterRepository.findById(id)
                .orElse(null);
    }

    // The deleteById function deletes the scooter by doing a DELETE in the DB.
    @Override
    @Transactional
    public void deleteScooter(Long id) {
        scooterRepository.deleteById(id);
    }

    // The saveAll function would do multiple INSERTS into the DB.
    @Override
    @Transactional
    public List<Scooter> saveAllScooter(List<Scooter> scooterList) {
        return scooterRepository.saveAll(scooterList);
    }

    @Override
    @Transactional
    public void assignScooterToCustomer(Long scooterId, Long customerId) {
        scooterRepository.addCustomerToScooter(scooterId, customerId);

    }
}

