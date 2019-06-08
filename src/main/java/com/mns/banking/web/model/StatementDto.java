package com.mns.banking.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementDto {
    private Integer accountId;
    private String accountNumber;
    private BigDecimal balance;
    private List<TransactionDto> transactionDtoList;
}
