package com.pg3402.csgobank.transactionValidator.transaction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("vault")
public interface VaultClient {


    // Which vault has item.
    // Item --> vaultiD = vaultID --> true

    @RequestMapping(method = RequestMethod.GET,value = "/item/{id}/vault")
    ResponseEntity<Long> getVaultId(@PathVariable Long id);
}
