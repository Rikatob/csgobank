package com.pg3402.csgobank.vaultAccount;

import com.pg3402.csgobank.vault.Vault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vaultAccount")
public class VaultAccountController {


    private final VaultAccountService vaultAccountService;

    @Autowired
    public VaultAccountController(VaultAccountService vaultAccountService) {
        this.vaultAccountService = vaultAccountService;
    }



    // Get account
    @GetMapping(value = "/{id}")
    public ResponseEntity<VaultAccount> getAccount(@PathVariable long id) {
        Optional<VaultAccount> optionalAccount = vaultAccountService.findById(id);

        return optionalAccount
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Get vaults
    @GetMapping(value = "/{id}/vaults",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vault>> getVaults(@PathVariable long id) {
        return vaultAccountService.findById(id)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account.getVaults()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }






}
