package com.pg3402.csgobank.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Optional<Item> findById(long id){
        return itemRepository.findById(id);
    }

    public Optional<Long> getVaultIdOfItem(long id){
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.map(item -> item.getVault().getId());
    }



}
