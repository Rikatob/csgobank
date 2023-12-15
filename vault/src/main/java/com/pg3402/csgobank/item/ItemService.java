package com.pg3402.csgobank.item;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Item> getAvailableItems() {
        ResponseEntity<Iterable<Item>> itemsResponse = itemClient.getAllItems();

        Iterable<Item> allItems = itemsResponse.getBody();
        Iterable<Item> takenItems = itemRepository.findAll();

        if(allItems == null){
            log.error("Something went wrong getting all items from item service");
            return List.of();
        }

        //Convert Iterable to Lists
        List<Item> availableItems = new ArrayList<>();
        allItems.forEach(availableItems::add);

        List<Item> takenItemList = new ArrayList<>();
        takenItems.forEach(takenItemList::add);

        //Remove all taken items, only the available will remain
        availableItems.removeAll(takenItemList);
        log.info("Available Items: " + availableItems);

        return availableItems;
    }
}
