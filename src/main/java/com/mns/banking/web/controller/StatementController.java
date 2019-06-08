package com.mns.banking.web.controller;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.exception.ResourceNotFoundException;
import com.mns.banking.service.AccountService;
import com.mns.banking.service.TransactionService;
import com.mns.banking.web.model.DtoBuilder;
import com.mns.banking.web.model.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/api/v1/statements")
public class StatementController {

    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    /** */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<StatementDto> getStatement(@PathVariable(value = "accountNumber") String accountNumber)
            throws ResourceNotFoundException {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        List<Transaction> transactionList = transactionService.getTransactions(account);
        StatementDto statementDto = DtoBuilder.buildStatementDto(account, transactionList);

        return ResponseEntity.ok().body(statementDto);
    }


}
