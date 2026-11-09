package com.exam.account_service.api.account.dto;

import java.math.BigDecimal;

public class AccountSummary {

    private final Long accountNumber;
    private final String accountType;
    private final BigDecimal availableBalance;

    public AccountSummary(Long accountNumber, String accountType, BigDecimal availableBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.availableBalance = availableBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }
}
