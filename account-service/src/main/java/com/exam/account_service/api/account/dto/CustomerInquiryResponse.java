package com.exam.account_service.api.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"customerNumber", "customerName", "customerMobile", "customerEmail",
        "address1", "address2", "savings", "checking",
        "transactionStatusCode", "transactionStatusDescription"})
public class CustomerInquiryResponse extends StatusResponse {

    private final String customerNumber;
    private final String customerName;
    private final String customerMobile;
    private final String customerEmail;
    private final String address1;
    private final String address2;
    private final List<AccountSummary> savings;
    private final List<AccountSummary> checking;

    public CustomerInquiryResponse(String customerNumber, String customerName, String customerMobile,
                                   String customerEmail, String address1, String address2,
                                   List<AccountSummary> savings, List<AccountSummary> checking,
                                   int statusCode, String statusDescription) {
        super(statusCode, statusDescription);
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.savings = savings;
        this.checking = checking;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public List<AccountSummary> getSavings() {
        return savings;
    }

    public List<AccountSummary> getChecking() {
        return checking;
    }
}
