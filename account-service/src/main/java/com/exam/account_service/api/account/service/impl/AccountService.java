package com.exam.account_service.api.account.service.impl;

import com.exam.account_service.api.account.dto.AccountSummary;
import com.exam.account_service.api.account.dto.CreateAccountRequest;
import com.exam.account_service.api.account.dto.CustomerInquiryResponse;
import com.exam.account_service.api.account.entity.Account;
import com.exam.account_service.api.account.entity.AccountType;
import com.exam.account_service.api.account.entity.Customer;
import com.exam.account_service.api.account.exception.CustomerNotFoundException;
import com.exam.account_service.api.account.exception.DuplicateEmailException;
import com.exam.account_service.api.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AccountService {

    private final CustomerRepository customerRepository;

    public AccountService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long createAccount(CreateAccountRequest request) {
        String email = request.getCustomerEmail().trim().toLowerCase(Locale.ROOT);
        if (customerRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }

        Customer customer = new Customer(
                request.getCustomerName().trim(),
                request.getCustomerMobile().trim(),
                email,
                request.getAddress1().trim(),
                request.getAddress2() == null ? null : request.getAddress2().trim());
        customer.addAccount(new Account(AccountType.fromCode(request.getAccountType()), BigDecimal.ZERO));

        return customerRepository.save(customer).getCustomerNumber();
    }

    @Transactional(readOnly = true)
    public CustomerInquiryResponse findCustomer(Long customerNumber) {
        Customer customer = customerRepository.findById(customerNumber)
                .orElseThrow(() -> new CustomerNotFoundException(customerNumber));

        List<AccountSummary> savings = new ArrayList<>();
        List<AccountSummary> checking = new ArrayList<>();
        for (Account account : customer.getAccounts()) {
            AccountSummary summary = new AccountSummary(account.getAccountNumber(),
                    account.getAccountType().getDescription(), account.getAvailableBalance());
            (account.getAccountType() == AccountType.SAVINGS ? savings : checking).add(summary);
        }

        return new CustomerInquiryResponse(String.valueOf(customer.getCustomerNumber()),
                customer.getName(), customer.getMobile(), customer.getEmail(),
                customer.getAddress1(), customer.getAddress2(),
                savings, checking, 302, "Customer Account found");
    }
}
