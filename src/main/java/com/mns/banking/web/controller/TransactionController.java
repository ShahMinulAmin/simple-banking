package com.mns.banking.web.controller;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.exception.InsufficientBalanceException;
import com.mns.banking.exception.ResourceNotFoundException;
import com.mns.banking.service.AccountService;
import com.mns.banking.service.TransactionService;
import com.mns.banking.web.model.DtoBuilder;
import com.mns.banking.web.model.TransactionDto;
import com.mns.banking.web.model.TransferDto;
import com.mns.banking.web.validator.TransferDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * REST API to transfer amount from one account to another
 */
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @InitBinder("transferDto")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new TransferDtoValidator());
    }


    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    /**
     * Used to transfer amount from one account to another
     */
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transferAmount(@Valid @RequestBody TransferDto transferDto)
            throws ResourceNotFoundException, InsufficientBalanceException {
        Account fromAccount = accountService.getAccountByAccountNumber(transferDto.getFromAccNumber());
        Account toAccount = accountService.getAccountByAccountNumber(transferDto.getToAccNumber());
        BigDecimal amount = new BigDecimal(transferDto.getAmount());

        Transaction transaction = transactionService.transferAmount(fromAccount, toAccount, amount);
        TransactionDto transactionDto = DtoBuilder.buildTransactionDto(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

}
