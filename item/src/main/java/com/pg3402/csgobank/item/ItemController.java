package com.pg3402.csgobank.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/item")
public class
ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Retrieve an item by its ID.
     *
     * @param id The ID of the item.
     * @return The HTTP response containing the item with the given ID, if it exists. Returns a bad request response otherwise.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable long id){
        log.info("Delivering item with id[" + id + "]");
        return itemService.findById(id)
                .map(item -> ResponseEntity.status(HttpStatus.OK).body(item))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Item>> getAllItems(){
        log.info("Delivering all items");
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }



    // item exists

    // verify item

}
