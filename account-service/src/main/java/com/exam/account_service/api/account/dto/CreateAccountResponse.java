package com.exam.account_service.api.account.dto;

public class CreateAccountResponse extends StatusResponse {

    private final Long customerNumber;

    public CreateAccountResponse(Long customerNumber, int statusCode, String statusDescription) {
        super(statusCode, statusDescription);
        this.customerNumber = customerNumber;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }
}
