package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.transaction.TransactionState;
import com.pg3402.csgobank.transaction.TransactionType;
import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vault")
@Slf4j
public class VaultController {

    private final VaultService vaultService;

    @Autowired
    public VaultController(VaultService vaultService) {
        this.vaultService = vaultService;
    }


    // Get a vault by ID.
    @GetMapping("/{vaultId}")
    public @ResponseBody ResponseEntity<Vault> getVault(@PathVariable long vaultId) {
        Optional<Vault> optionalVault = vaultService.findById(vaultId);

        return optionalVault
                .map(Vault -> ResponseEntity.status(HttpStatus.OK).body(Vault))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Check if vault exists with vaultId.
    @GetMapping("/exists/{vaultId}")
    public ResponseEntity<Boolean> checkIfVaultExists(@PathVariable long vaultId) {
        return vaultService.findById(vaultId)
                .map(vault -> ResponseEntity.status(HttpStatus.OK).body(true))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.OK).body(false));
    }


    // Get all items in a vault with vaultId.
    @GetMapping("/{vaultId}/items")
    public ResponseEntity<Iterable<Item>> getAllItems(@PathVariable long vaultId) {
        if (!vaultService.exists(vaultId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(vaultService.getAllItems(vaultId));
    }


    // Create new vault with AccountId.
    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Vault> createVault(@RequestParam Long accountId) {

        return vaultService.createVault(accountId)
                .map(vault -> ResponseEntity.status(HttpStatus.CREATED).body(vault))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }


    // Transferring item with a transaction.
    @GetMapping(value = "/transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> transferItem(@RequestBody Transaction transaction) {
        log.info("Transferring item " + transaction.getItemId() + " from " + transaction.getFromVaultId() + " to " + transaction.getToVaultId());
        transaction.setType(TransactionType.TRANSFER);
        transaction = vaultService.transferItem(transaction);

        if (transaction.getState().equals(TransactionState.COMPLETE)) {
            log.info("Transaction complete");
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } else {
            log.info("Transaction failed");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(transaction);
        }

    }


    @PostMapping(value = "/offer")
    public ResponseEntity<Transaction> createTradeOffer(@RequestBody Transaction transaction){
        log.info("Creating trade offer " + transaction);

        //Only TRADE is accepted
        if(!transaction.getType().equals(TransactionType.TRADE)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        transaction = vaultService.createTradeOffer(transaction);

        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    // Get owner of item with itemId.
    @GetMapping(value = "/item/{id}")
    public ResponseEntity<VaultAccount> getOwnerOfItem(@PathVariable long id) {
        return vaultService.getOwnerOfItem(id)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    // Deposit item to vault with vaultId and itemId.
    @PostMapping(value = "{vaultId}/item/deposit/{itemId}")
    public ResponseEntity<Item> depositItemToVault(@PathVariable long vaultId, @PathVariable long itemId) {
        return vaultService.depositItem(vaultId, itemId)
                .map(item -> ResponseEntity.status(HttpStatus.OK).body(item))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    // Withdraw item from vault with vaultId and itemId.
    @GetMapping(value = "{vaultId}/item/withdraw/{itemId}")
    public ResponseEntity<Item> withdrawItem(@PathVariable long vaultId,@PathVariable long itemId) {
        return vaultService.withdrawItem(vaultId,itemId)
                .map(item -> ResponseEntity.status(HttpStatus.OK).body(item))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

}
