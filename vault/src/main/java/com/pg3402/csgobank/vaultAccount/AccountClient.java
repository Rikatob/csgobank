package com.pg3402.csgobank.vaultAccount;

import com.pg3402.csgobank.transaction.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.POST,value = "account/transfer/credit")
    ResponseEntity<Transaction> transferCredits(@RequestBody Transaction transaction);
}