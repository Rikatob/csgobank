package com.pg3402.csgobank.transactionValidator.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    
    List<Transaction> findAllByToAccountIdAndType(long id, TransactionType type);


    List<Transaction> findAllByFromAccountIdAndType(long id, TransactionType type);

    boolean existsByItemId(long id);
}
