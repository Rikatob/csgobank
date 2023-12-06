package com.pg3402.csgobank.transactionhistory.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
}
