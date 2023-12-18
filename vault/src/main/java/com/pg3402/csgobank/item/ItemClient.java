package com.pg3402.csgobank.item;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("item")
public interface ItemClient {



    @RequestMapping(method = RequestMethod.GET, value = "/item/{itemId}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Item> getItem(@PathVariable long itemId);

    @RequestMapping(method = RequestMethod.GET, value = "/item/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Iterable<Item>> getAllItems();
}
