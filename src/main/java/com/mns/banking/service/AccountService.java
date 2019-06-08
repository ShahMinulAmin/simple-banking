package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.exception.ResourceNotFoundException;

/**
 * Service interface to do CRUD operations on Account entity
 */
public interface AccountService {

    /**
     * Used to get an account by its id
     *
     * @param id The id of account
     * @return Account instance
     */
    public Account getAccountById(Integer id) throws ResourceNotFoundException;;

    /**
     * Used to get an account by its account number
     *
     * @param accountNumber The account number of the account
     * @return Account instance
     */
    public Account getAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;;

    /**
     * Used to store account instance
     *
     * @param account The account object to store
     * @return Account instance
     */
    public Account saveOrUpdateAccount(Account account);

}
