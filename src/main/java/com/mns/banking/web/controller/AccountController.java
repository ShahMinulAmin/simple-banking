package com.mns.banking.web.controller;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.EntityBuilder;
import com.mns.banking.exception.ResourceNotFoundException;
import com.mns.banking.service.AccountService;
import com.mns.banking.web.model.AccountDto;
import com.mns.banking.web.model.DtoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    EntityBuilder entityBuilder;
    @Autowired
    AccountService accountService;

    /** */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(value = "accountNumber") String accountNumber)
            throws ResourceNotFoundException {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        AccountDto accountDto = DtoBuilder.buildAccountDto(account);
        return ResponseEntity.ok().body(accountDto);
    }

    /** */
    @PostMapping()
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        Account account = entityBuilder.buildAccountEntity(accountDto);
        Account createdAccount = accountService.saveOrUpdateAccount(account);
        if (createdAccount == null) {
            return ResponseEntity.notFound().build();
        }
        AccountDto createdAccountDto = DtoBuilder.buildAccountDto(createdAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccountDto);
    }

}
