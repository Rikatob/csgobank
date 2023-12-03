package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.account.Account;
import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import com.pg3402.csgobank.transaction.Transaction;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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


    // Get all items in a vault.
    @GetMapping("/{vaultId}/items")
    public ResponseEntity<Iterable<Item>> getAllItems(@PathVariable long vaultId) {
        if (!vaultService.exists(vaultId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(vaultService.getAllItems(vaultId));
    }


    // Create new vault.
    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Vault> createVault(@RequestParam Long accountId) {

        return vaultService.createVault(accountId)
                .map(vault -> ResponseEntity.status(HttpStatus.CREATED).body(vault))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }


    // TODO log info on endpoints ???


    @GetMapping("/transfer")
    public ResponseEntity<Transaction> transferItem(Transaction transaction) {
        log.info("Transferring item " + transaction.getItemID() + " from " + transaction.getSellerID() + " to " + transaction.getBuyerID());
        vaultService.transferItem(transaction);
        log.info("OStbrød");
        return "hei";
    }


}
