package com.pg3402.csgobank.transactionValidator.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping
    public @ResponseBody boolean validateTransaction() {
        log.info("Validating transaction");
        return true;
    }
}
