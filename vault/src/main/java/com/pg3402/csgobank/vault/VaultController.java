package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/vault")
public class VaultController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VaultRepository vaultRepository;
    @Autowired
    private VaultService vaultService;

    // TODO: 9/9/2023  Should i make these endpoints return ResponseEntity instead because of Http status codes?? 
    @GetMapping("/items")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Vault> getVault(@RequestParam(name = "id", defaultValue = "1") long id) {
        Optional<Vault> optionalVault = vaultRepository.findById(id);

        return optionalVault
                .map(Vault -> ResponseEntity.status(HttpStatus.OK).body(Vault))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/transfer")
    public String transferItem() {
        vaultService.transferItem();
        return "hei";
    }

    @PostMapping("/new")
    public Vault createVault(VaultDto vaultDto) {
        Vault vault = new Vault();
        vault.setOwnerName(vaultDto.ownerName());
        vault.setTotalValue(vaultDto.totalValue());
        return vaultRepository.save(vault);
    }

}
