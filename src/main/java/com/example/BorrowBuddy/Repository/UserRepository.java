package com.example.BorrowBuddy.Repository;

import com.example.BorrowBuddy.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
