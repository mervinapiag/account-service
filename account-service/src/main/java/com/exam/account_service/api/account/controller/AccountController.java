package com.exam.account_service.api.account.controller;

import com.exam.account_service.api.account.dto.CreateAccountRequest;
import com.exam.account_service.api.account.dto.CreateAccountResponse;
import com.exam.account_service.api.account.dto.CustomerInquiryResponse;
import com.exam.account_service.api.account.service.impl.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Long customerNumber = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccountResponse(customerNumber, 201, "Customer account created"));
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerInquiryResponse> getCustomer(@PathVariable Long customerNumber) {
        return ResponseEntity.status(HttpStatus.FOUND).body(accountService.findCustomer(customerNumber));
    }
}
