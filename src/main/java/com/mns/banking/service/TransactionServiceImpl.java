package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.exception.InsufficientBalanceException;
import com.mns.banking.repository.TransactionRepository;
import com.mns.banking.types.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service implementation to store and get transactions and transfer amount from one account to another
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOG = Logger.getLogger(TransactionServiceImpl.class.getName());

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;

    /**
     * Used to store transaction instance
     *
     * @param transaction The transaction object to store
     * @return Transaction instance
     */
    @Override
    public Transaction storeTransaction(Transaction transaction) {
        LOG.info("Storing a transaction");
        Transaction transactionInstance = transactionRepository.save(transaction);
        return transactionInstance;
    }

    /**
     * Used to get list of transactions of an account
     *
     * @param account The account object
     * @return List<Transaction> list of transactions
     */
    @Override
    public List<Transaction> getTransactions(Account account) {
        LOG.info("Getting list of transactions of an account");
        return transactionRepository.getTransactionsBy(account.getId());
    }

    /**
     * Used to transfer amount from one account to another
     *
     * @param fromAccount The source account
     * @param toAccount The destination account
     * @param amount The amount to transfer
     * @return Transaction instance
     */
    @Override
    @Transactional(rollbackOn = {Exception.class})
    public Transaction transferAmount(Account fromAccount, Account toAccount, BigDecimal amount)
            throws InsufficientBalanceException {
        LOG.info("Transferring an amount from one account to another");

        BigDecimal balanceFromAcc = fromAccount.getBalance();
        if (amount.compareTo(balanceFromAcc) > 0) {
            throw new InsufficientBalanceException("Account " + fromAccount.getAccountNumber()
                    + " does not have sufficient balance to transfer " + amount);
        }

        // Subtract the amount from source account
        BigDecimal updatedBalanceFromAcc = balanceFromAcc.subtract(amount);
        fromAccount.setBalance(updatedBalanceFromAcc);
        Account updatedFromAccount = accountService.saveOrUpdateAccount(fromAccount);

        // Add the amount from destination account
        BigDecimal balanceToAcc = toAccount.getBalance();
        BigDecimal updatedBalanceToAcc = balanceToAcc.add(amount);
        toAccount.setBalance(updatedBalanceToAcc);
        Account updatedToAccount = accountService.saveOrUpdateAccount(toAccount);

        // Store the transaction
        Transaction transaction = new Transaction(updatedFromAccount, updatedToAccount, amount,
                TransactionType.TRANSFER.getCode(), new Date());
        Transaction transactionInstance = transactionRepository.save(transaction);
        return transactionInstance;
    }
}
