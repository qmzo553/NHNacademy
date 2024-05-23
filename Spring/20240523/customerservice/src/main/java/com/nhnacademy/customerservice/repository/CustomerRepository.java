package com.nhnacademy.customerservice.repository;

import com.nhnacademy.customerservice.domain.Customer;

public interface CustomerRepository {

    Customer getCustomer(String id);
    boolean exists(String id);
    boolean matches(String id, String password);
}
