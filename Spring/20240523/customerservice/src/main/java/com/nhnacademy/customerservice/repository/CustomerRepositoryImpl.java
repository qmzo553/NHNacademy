package com.nhnacademy.customerservice.repository;

import com.nhnacademy.customerservice.domain.Customer;
import com.nhnacademy.customerservice.exception.CustomerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    Map<String, Customer> customerMap = new HashMap<>();

    public CustomerRepositoryImpl() {
        customerMap.put("user", Customer.create("user", "123", "user", 10, "01012345678", "a@a"));
    }

    @Override
    public Customer getCustomer(String id) {
        if(exists(id)) {
            throw new CustomerNotFoundException();
        }

        return customerMap.get(id);
    }

    @Override
    public boolean exists(String id) {
        if(id == null || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return customerMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getCustomer(id))
                .map(Customer -> Customer.getPassword().equals(password))
                .orElse(false);
    }
}
