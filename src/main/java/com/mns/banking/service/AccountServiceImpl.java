package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.exception.ResourceNotFoundException;
import com.mns.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Service implementation to do CRUD operations on Account entity
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Used to get an account by its id
     *
     * @param id The id of account
     * @return Account instance
     */
    @Override
    public Account getAccountById(Integer id) throws ResourceNotFoundException {
        LOG.info("Getting account by id");
        Optional<Account> account = accountRepository.findById(id);
        if (!account.isPresent()) {
            throw new ResourceNotFoundException("Account not found for this id: " + id);
        }
        return account.get();
    }

    /**
     * Used to get an account by its account number
     *
     * @param accountNumber The account number of the account
     * @return Account instance
     */
    @Override
    public Account getAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException {
        LOG.info("Getting account by account number");
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found for this account number: " + accountNumber);
        }
        return accountRepository.findByAccountNumber(accountNumber);
    }

    /**
     * Used to store account instance
     *
     * @param account The account object to store
     * @return Account instance
     */
    @Override
    public Account saveOrUpdateAccount(Account account) {
        LOG.info("Storing account entity");
        Account accountInstance = accountRepository.save(account);
        return accountInstance;
    }
}
