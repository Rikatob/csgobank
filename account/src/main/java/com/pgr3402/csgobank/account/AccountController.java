package com.pgr3402.csgobank.account;

import com.pgr3402.csgobank.transaction.Transaction;
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

    // Get all accounts.
    @GetMapping("/all")
    public ResponseEntity<Iterable<Account>> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }
    // Create new account.
    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (accountService.exists(account) || account.getId() != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));

    }

    // Delete account.
    @PostMapping(value = "/delete/{accountId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        Optional<Account> optionalAccount = accountService.findById(accountId);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        accountService.deleteAccount(optionalAccount.get());
        return ResponseEntity.status(HttpStatus.OK).build();
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

    // Verify that account exists.
    @GetMapping("verifyAccount/{accountId}")
    public ResponseEntity<Boolean> verifyAccount(@PathVariable Long accountId) {
        return accountService.findById(accountId)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
    }

    @GetMapping("credit/{id}")
    public ResponseEntity<Integer> getCreditsFromAccount(@PathVariable Integer id){
        return accountService.findById(id)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account.getCredit()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("transfer/credit")
    public ResponseEntity<Transaction> updateCredit(@RequestBody Transaction transaction){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.transferCredits(transaction));
    }

    @PostMapping("credit/{accountId}/deposit")
    public ResponseEntity<Account> depositCredit(@PathVariable long accountId, @RequestBody int amount){
        return accountService.depositCredit(accountId, amount).map( account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("credit/{accountId}/withdraw")
    public ResponseEntity<Account> withdrawCredit(@PathVariable long accountId, @RequestBody int amount){
        return accountService.withdrawCredit(accountId, amount).map( account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

}
