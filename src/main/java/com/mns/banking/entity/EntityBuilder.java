package com.mns.banking.entity;

import com.mns.banking.web.model.AccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Utility class for converting data transfer objects to entities
 *
 */
@Component
public class EntityBuilder {

    public Account buildAccountEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setName(accountDto.getName());
        account.setAddress(accountDto.getAddress());
        account.setBalance(new BigDecimal(accountDto.getBalance()));
        return account;
    }
}
