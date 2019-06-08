package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface to store and get transactions and transfer amount from one account to another
 */
public interface TransactionService {

    /**
     * Used to store transaction instance
     *
     * @param transaction The transaction object to store
     * @return Transaction instance
     */
    public Transaction storeTransaction(Transaction transaction);

    /**
     * Used to get list of transactions of an account
     *
     * @param account The account object
     * @return List<Transaction> list of transactions
     */
    public List<Transaction> getTransactions(Account account);

    /**
     * Used to transfer amount from one account to another
     *
     * @param fromAccount The source account
     * @param toAccount The destination account
     * @param amount The amount to transfer
     * @return Transaction instance
     */
    public Transaction transferAmount(Account fromAccount, Account toAccount, BigDecimal amount)
            throws InsufficientBalanceException;

}
