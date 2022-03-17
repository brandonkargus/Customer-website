package com.kargus.Customer.Website.services;

import com.kargus.Customer.Website.models.Customer;
import com.kargus.Customer.Website.models.Scooter;
import com.kargus.Customer.Website.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    final CustomerRepository customerRepository;

    @Autowired
    final ScooterService scooterService;

    // The findAll function gets all the customers by doing a SELECT query in the DB.
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // The save function uses an INSERT query in the DB.
    @Override
    @Transactional
    public void saveCustomer(Customer customer) throws IllegalStateException {
        customer.validate();
        customerRepository.save(customer);
    }

    // The findById function uses a SELECT query with a WHERE clause in the DB.
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElse(null);
    }

    // The deleteById function deletes the customer by doing a DELETE in the DB.
    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // The saveAll function would do multiple INSERTS into the DB.
    @Override
    @Transactional
    public void saveAllCustomer(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }

    @Override
    @Transactional
    @Modifying
    public void assignScooterToCustomer(Long customerId, Long scooterId) {

        //customerRepository.addScooterToCustomer(customerId, scooterId);
        Customer customer = customerRepository.getById(customerId);
        Scooter scooter = scooterService.getScooter(scooterId);

        customer.setScooter(scooter);
        customerRepository.save(customer);

    }

    @Override
    @Transactional
    @Modifying
    public void removeScooterFromCustomer(Long customerId) {

        Customer customer = customerRepository.getById(customerId);

        customer.setScooter(null);
        customerRepository.save(customer);

    }
}
