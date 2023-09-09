package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import com.pg3402.csgobank.item.WearCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vault")
public class VaultController {
    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/items")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

  /*  @GetMapping
    Vault getVault(@RequestParam(name = "id", defaultValue = "1") String id) {

        Item testItem = new Item(1, "AK", "Fireserpent", 233, 1000, WearCategory.MINIMAL_WEAR);
        Item testItem2 = new Item(2, "M4", "Howl", 111, 2222, WearCategory.FACTORY_NEW);

        List<Item> testList = new ArrayList<>();
        testList.add(testItem);
        testList.add(testItem2);

        Vault testVault = new Vault(Long.parseLong(id), "Freddy", testList, testItem.getPrice() + testItem2.getPrice());

        return testVault;
    }
*/
}
