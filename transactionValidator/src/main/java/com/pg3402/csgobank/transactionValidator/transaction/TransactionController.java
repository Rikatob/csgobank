package com.pg3402.csgobank.transactionValidator.transaction;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping
    public @ResponseBody boolean validateTransaction() {
        return true;
    }
}
