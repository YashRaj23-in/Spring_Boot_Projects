package com.example.BorrowBuddy.Controller;

import com.example.BorrowBuddy.Model.BorrowRequest;
import com.example.BorrowBuddy.Model.Item;
import com.example.BorrowBuddy.Repository.BorrowRepository;
import com.example.BorrowBuddy.Repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class BorrowRequestController {
    private final BorrowRepository borrowRepository;
    private final ItemRepository itemRepository;

    public BorrowRequestController(BorrowRepository borrowRepository, ItemRepository itemRepository) {
        this.borrowRepository = borrowRepository;
        this.itemRepository = itemRepository;
    }
    @PostMapping
    public ResponseEntity<BorrowRequest> addRequest(@RequestBody BorrowRequest borrow){
        BorrowRequest request=borrowRepository.save(borrow);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
    @GetMapping("/borrower/{borrowId}")
    public ResponseEntity<List<BorrowRequest>> getrequestByid(@PathVariable Integer BorrowId){
        return ResponseEntity.ok(borrowRepository.findByBorrowerId(BorrowId));
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<BorrowRequest>> get(@PathVariable Integer ownerId){
        return ResponseEntity.ok(borrowRepository.findByItemOwnerId(ownerId));
    }
    @PatchMapping("/{requestId}/status")
    public ResponseEntity<BorrowRequest> updateRequest(@PathVariable Integer requestId,@RequestParam String status){
        return borrowRepository.findById(requestId)
                .map(request ->{
                    String cleanStatus=status.toUpperCase();
                    request.setStatus(cleanStatus);
                    if("APPROVED".equals(cleanStatus)){
                        Item connectedItem=request.getItem();
                        connectedItem.setAvailable(false);
                        itemRepository.save(connectedItem);
                    }
                    BorrowRequest request1=borrowRepository.save(request);
                    return ResponseEntity.ok(request1);
                })
                .orElse(ResponseEntity.notFound().build());

    }
}
