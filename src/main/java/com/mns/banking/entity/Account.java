package com.mns.banking.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity representing a bank account
 *
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", scale = 0)
    private Integer id;

    @Basic
    @Column(name = "account_number", length = 256)
    private String accountNumber;

    @Basic
    @Column(name = "name", length = 256)
    private String name;

    @Basic
    @Column(name = "address", length = 1024)
    private String address;

    @Basic
    @Column(name = "balance", precision = 20, scale = 10)
    private BigDecimal balance;


    public Account() {
    }

    public Account(String accountNumber, String name, String address, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public Account(Integer id, String accountNumber, String name, String address, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

