package com.exam.account_service.api.account.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer",
        uniqueConstraints = @UniqueConstraint(name = "uk_customer_email", columnNames = "email"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_number_seq")
    @SequenceGenerator(name = "customer_number_seq", sequenceName = "customer_number_seq",
            initialValue = 10000001, allocationSize = 1)
    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address1", nullable = false, length = 100)
    private String address1;

    @Column(name = "address2", length = 100)
    private String address2;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    protected Customer() {
        // required by JPA
    }

    public Customer(String name, String mobile, String email, String address1, String address2) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
