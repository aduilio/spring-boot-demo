package com.aduilio.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aduilio.spring.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
