package com.mns.banking.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity representing a transaction
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", scale = 0)
    private Integer id;

    @OneToOne(targetEntity = Account.class)
    @JoinColumn(name = "from_account", referencedColumnName = "id")
    private Account fromAccount;

    @OneToOne(targetEntity = Account.class)
    @JoinColumn(name = "to_account", referencedColumnName = "id")
    private Account toAccount;

    @Basic
    @Column(name = "amount", precision = 20, scale = 10)
    private BigDecimal amount;

    @Basic
    @Column(name = "type")
    private Integer type;

    @Basic
    @Column(name = "transaction_date")
    private Date transactionDate;


    public Transaction() {
    }


    public Transaction(Account fromAccount, Account toAccount, BigDecimal amount, Integer type, Date transactionDate) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public Transaction(Integer id, Account fromAccount, Account toAccount, BigDecimal amount, Integer type, Date transactionDate) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
