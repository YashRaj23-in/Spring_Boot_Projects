package com.example.BorrowBuddy.Repository;

import com.example.BorrowBuddy.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByTitleContainingIgnoreCaseAndIsAvailableTrue(String Keyword);
    List<Item> findByOwnerId(Integer OwnerId);

}
