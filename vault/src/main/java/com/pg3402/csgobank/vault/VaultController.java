package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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



    @GetMapping("/transfer")
    public String transferItem() {
        vaultService.transferItem();
        log.info("OStbrød");
        return "hei";
    }

   /* @PostMapping("/new")
    public Vault createVault(VaultDto vaultDto) {
        Vault vault = new Vault();
        vault.setOwnerName(vaultDto.ownerName());
        vault.setTotalValue(vaultDto.totalValue());
        return vaultRepository.save(vault);
    }*/

}
