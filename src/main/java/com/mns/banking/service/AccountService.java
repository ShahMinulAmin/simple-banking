package com.mns.banking.service;

import com.mns.banking.entity.Account;
import com.mns.banking.exception.ResourceNotFoundException;

/** */
public interface AccountService {

    /** */
    public Account getAccountById(Integer id) throws ResourceNotFoundException;;

    /** */
    public Account getAccountByAccountNumber(String accountNumber) throws ResourceNotFoundException;;

    /** */
    public Account saveOrUpdateAccount(Account account);

}
