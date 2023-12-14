package com.pgr3402.csgobank.account;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("vault")
public interface VaultClient {


    @RequestMapping(method = RequestMethod.GET,value = "/vaultAccount/{id}/vaults", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<JsonNode>> getVault(@PathVariable(name = "id") long id);


}
