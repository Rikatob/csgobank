package com.pg3402.csgobank.transactionValidator.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    
    Optional<List<Transaction>> findAllByToAccountId(long id);


    Optional<List<Transaction>> findAllByFromAccountId(long id);

    boolean existsByItemId(long id);
}
