package com.pg3402.csgobank.item;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemClient itemClient;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemClient itemClient) {
        this.itemRepository = itemRepository;
        this.itemClient = itemClient;
    }


    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    public Optional<Long> getVaultIdOfItem(long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.map(item -> item.getVault().getId());
    }


    public Optional<Item> getItem(long itemId) {
        log.info("Retrieving item with id [" + itemId + "]");
        ResponseEntity<Item> itemResponseEntity = itemClient.getItem(itemId);
        return Optional.ofNullable(itemResponseEntity.getBody());
    }
}
