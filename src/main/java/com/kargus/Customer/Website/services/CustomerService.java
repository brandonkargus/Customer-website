package com.kargus.Customer.Website.services;

import com.kargus.Customer.Website.models.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    void saveAllCustomer(List<Customer> customerList);

    void assignScooterToCustomer(Long customer, Long scooterId);

    void removeScooterFromCustomer(Long customer);

}
