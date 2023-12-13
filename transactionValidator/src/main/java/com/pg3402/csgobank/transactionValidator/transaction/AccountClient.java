package com.pg3402.csgobank.transactionValidator.transaction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET,value = "/account/credit/{id}")
    ResponseEntity<Integer> getAccountCredit(@PathVariable(name = "id") long accountId);
}
