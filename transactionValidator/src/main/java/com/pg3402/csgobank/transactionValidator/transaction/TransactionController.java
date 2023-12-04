package com.pg3402.csgobank.transactionValidator.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * @param transaction
     * @return transaction with updated isValidated, wrapped in a ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<Transaction> validateTransaction(Transaction transaction) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.validateTransaction(transaction));
    }
}
