package com.mns.banking.repository;

import com.mns.banking.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByAccountNumber(String accountNumber);

}
