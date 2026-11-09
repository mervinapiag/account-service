package com.exam.account_service.api.account.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerNumber) {

        super("Customer not found: " + customerNumber);

    }
}
