package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // TODO: 9/9/2023  Should i make these endpoints return ResponseEntity instead because of Http status codes?? 
    @GetMapping("/items")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @GetMapping
    public @ResponseBody Optional<Vault> getVault(@RequestParam(name = "id", defaultValue = "1") String id) {
    return vaultRepository.findById(Long.parseLong(id));
    }

}
