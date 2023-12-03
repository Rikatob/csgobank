package com.pg3402.csgobank.transaction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Navn fra consul
@FeignClient("transactionValidator")
public interface TransactionValidationClient {

    /**
     *  Value = Pathen som vi skal nå
     *  method = GET/POST/PUT etc
     */
    @RequestMapping(method = RequestMethod.GET, value = "/validate")
    ResponseEntity<Boolean> validate();
}
