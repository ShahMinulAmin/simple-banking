package com.mns.banking.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer id;
    private Integer fromAccId;
    private Integer toAccId;
    private Double amount;
    private Integer type;
    private Date transactionDate;
}
