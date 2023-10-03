package com.pg3402.csgobank.transactionValidator.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @GetMapping
    public @ResponseBody boolean validateTransaction() {
        log.info("hei fredirk");
        return true;
    }
}
