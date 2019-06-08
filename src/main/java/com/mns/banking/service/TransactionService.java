package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    public Transaction storeTransaction(Transaction transaction);

    public List<Transaction> getTransactions(Account account);

    public Transaction transferAmount(Account fromAccount, Account toAccount, BigDecimal amount)
            throws InsufficientBalanceException;

}
