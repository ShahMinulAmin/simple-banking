package com.mns.banking.repository;

import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("select transaction from Transaction transaction WHERE " +
            " (transaction.fromAccount is not null AND transaction.fromAccount.id = :accountId) " +
            "OR (transaction.toAccount is not null AND transaction.toAccount.id = :accountId) ")
    public List<Transaction> getTransactionsBy(@Param("accountId") Integer accountId);

}

