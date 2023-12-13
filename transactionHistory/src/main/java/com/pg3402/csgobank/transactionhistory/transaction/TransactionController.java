package com.pg3402.csgobank.transactionhistory.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("history")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<List<Transaction>> getItemHistory(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(transactionRepository.findAllByItemId(id));
    }
}
