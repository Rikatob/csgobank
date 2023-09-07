package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.WearCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vault")
public class VaultController {

    @GetMapping
    Vault getVault(@RequestParam(name = "id", defaultValue = "1") String id) {

        Item testItem = new Item(1, "AK", "Fireserpent", 233, 1000, WearCategory.MINIMAL_WEAR);
        Item testItem2 = new Item(2, "M4", "Howl", 111, 2222, WearCategory.FACTORY_NEW);

        List<Item> testList = new ArrayList<>();
        testList.add(testItem);
        testList.add(testItem2);

        Vault testVault = new Vault(Long.parseLong(id), "Freddy", testList, testItem.getPrice() + testItem2.getPrice());

        return testVault;
    }

    public String test(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
