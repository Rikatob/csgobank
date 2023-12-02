package com.pg3402.csgobank.account;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(account, HttpStatus.BAD_REQUEST);
        }
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    // Get account
    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id) {
        Optional<Account> optionalAccount = accountService.findById(id);

        return optionalAccount
                .map(Account -> ResponseEntity.status(HttpStatus.OK).body(Account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Update account


    // Get vaults


}
