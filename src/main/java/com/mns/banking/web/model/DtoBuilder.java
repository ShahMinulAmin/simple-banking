package com.mns.banking.web.model;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting entities to data transfer objects
 *
 */
public class DtoBuilder {

    public static AccountDto buildAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .name(account.getName())
                .address(account.getAddress())
                .balance(account.getBalance().doubleValue())
                .build();
    }

    public static TransactionDto buildTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .fromAccId(transaction.getFromAccount() != null ? transaction.getFromAccount().getId() : null)
                .toAccId(transaction.getToAccount() != null ? transaction.getToAccount().getId() : null)
                .amount(transaction.getAmount().doubleValue())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public static StatementDto buildStatementDto(Account account, List<Transaction> transactionList) {

        List<TransactionDto> transactionDtoList = transactionList.stream().map(DtoBuilder::buildTransactionDto)
                .collect(Collectors.toList());

        return StatementDto.builder()
                .accountId(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .transactionDtoList(transactionDtoList)
                .build();
    }
}
