package com.pg3402.csgobank.transactionValidator.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public @ResponseBody boolean validateTransaction() {
        log.warn("Validating transaction. (Controller");
        boolean validated = transactionService.validateTransaction();
        log.warn("Done with validating transaction.(Controller)");
        return validated;
    }
}
