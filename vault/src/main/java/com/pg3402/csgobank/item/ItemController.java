package com.pg3402.csgobank.item;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaultItem")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable long id){

        return itemService.findById(id)
                .map(item -> ResponseEntity.status(HttpStatus.OK).body(item))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping(value = "/{id}/vault")
    public ResponseEntity<Long> getVaultIdOfItem(@PathVariable long id){
        return itemService.getVaultIdOfItem(id)
                .map(account -> ResponseEntity.status(HttpStatus.OK).body(account))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<Item>> getAllAvailableItems(){
        log.info("Getting all available items");
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAvailableItems());
    }
}
