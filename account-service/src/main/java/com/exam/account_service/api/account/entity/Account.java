package com.exam.account_service.api.account.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account", indexes = @Index(name = "idx_account_customer", columnList = "customer_number"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_number_seq")
    @SequenceGenerator(name = "account_number_seq", sequenceName = "account_number_seq",
            initialValue = 10001, allocationSize = 1)
    @Column(name = "account_number")
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 10)
    private AccountType accountType;

    @Column(name = "available_balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal availableBalance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_number", nullable = false)
    private Customer customer;

    protected Account() {
        // required by JPA
    }

    public Account(AccountType accountType, BigDecimal availableBalance) {
        this.accountType = accountType;
        this.availableBalance = availableBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
