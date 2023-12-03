package com.pg3402.csgobank.account;

import com.pg3402.csgobank.vault.Vault;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/account")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (accountService.exists(account) || account.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));

    }

    // Get account
    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id) {
        Optional<Account> optionalAccount = accountService.findById(id);

        return optionalAccount
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Update account
    @PostMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        Optional<Account> optionalAccount = accountService.updateAccount(account);

        return optionalAccount
                .map(updatedAccount -> ResponseEntity.status(HttpStatus.OK).body(updatedAccount))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }


    // Get vaults
    @GetMapping("/{id}/vaults")
    public ResponseEntity<List<Vault>> getVaults(@PathVariable long id) {
        return accountService.findById(id)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account.getVaults()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
