package com.exam.account_service.api.account.repository;


import com.exam.account_service.api.account.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    @Override
    @EntityGraph(attributePaths = "accounts")
    Optional<Customer> findById(Long customerNumber);
}
