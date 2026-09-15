package com.example.BorrowBuddy.Controller;

import com.example.BorrowBuddy.Model.Item;
import com.example.BorrowBuddy.Repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        Item post=itemRepository.save(item);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Item>>  getItem(@RequestParam String Keyword){
        System.out.println("The search engine just received keyword: " + Keyword);
        List<Item> found=itemRepository.findByTitleContainingIgnoreCaseAndIsAvailableTrue(Keyword);
        System.out.println("Items found in database: " + found.size());
        return ResponseEntity.ok(found);
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Item>> getItemByOwnerId(@PathVariable Integer OwnerId){
        List<Item> id=itemRepository.findByOwnerId(OwnerId);
        return ResponseEntity.ok(id);
    }
}
