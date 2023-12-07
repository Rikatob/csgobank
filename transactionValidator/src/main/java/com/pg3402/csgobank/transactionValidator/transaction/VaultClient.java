package com.pg3402.csgobank.transactionValidator.transaction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("vault")
public interface VaultClient {


    @RequestMapping(method = RequestMethod.GET,value = "/vaultItem/{itemId}/vault")
    ResponseEntity<Long> getVaultId(@PathVariable(name = "itemId") long itemId);


    @RequestMapping(method = RequestMethod.GET,value = "/vault/exists/{vaultId}")
    ResponseEntity<Boolean> checkIfVaultExists(@PathVariable(name = "vaultId") long vaultId);

}
