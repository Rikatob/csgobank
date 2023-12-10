package com.pg3402.csgobank.transactionValidator.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> validateTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.validateTransaction(transaction));
    }


    @GetMapping(value = "/incoming/{id}")
    public ResponseEntity<List<Transaction>> getIncomingTransaction(@PathVariable long id) {
        return transactionService.getIncomingTransaction(id)
                .map(transactions -> ResponseEntity.status(HttpStatus.OK).body(transactions))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping(value = "/outgoing/{id}")
    public ResponseEntity<List<Transaction>> getOutgoingTransaction(@PathVariable long id) {
        return transactionService.getOutgoingTransaction(id)
                .map(transactions -> ResponseEntity.status(HttpStatus.OK).body(transactions))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
