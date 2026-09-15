package com.example.BorrowBuddy.Repository;

import com.example.BorrowBuddy.Model.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowRequest,Integer> {
    List<BorrowRequest> findByBorrowerId(Integer borrowId);
    List<BorrowRequest> findByItemOwnerId(Integer OwnerId);
}
