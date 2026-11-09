package com.exam.account_service.api.account.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateAccountRequest {

    @NotBlank(message = "Customer name is required field")
    @Size(max = 50, message = "Customer name must not exceed 50 characters")
    private String customerName;

    @NotBlank(message = "Customer mobile is required field")
    @Size(max = 20, message = "Customer mobile must not exceed 20 characters")
    private String customerMobile;

    @NotBlank(message = "Email is required field")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Pattern(regexp = "^$|^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$",
            message = "Email is invalid")
    private String customerEmail;

    @NotBlank(message = "Address1 is required field")
    @Size(max = 100, message = "Address1 must not exceed 100 characters")
    private String address1;

    @Size(max = 100, message = "Address2 must not exceed 100 characters")
    private String address2;

    @NotBlank(message = "Account type is required field")
    @Pattern(regexp = "^$|^[SsCc]$", message = "Account type must be S (Savings) or C (Checking)")
    private String accountType;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
