package com.pg3402.csgobank.transactionValidator.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    public boolean validateTransaction(){
        int random = (int)Math.round(Math.random());
        if(random == 0){
            log.warn("Transaction is not validated. (validateTransaction)");
            return false;
        }else {
            log.warn("Transaction is validated. (validateTransaction)");
            return true;
        }
    }
}
