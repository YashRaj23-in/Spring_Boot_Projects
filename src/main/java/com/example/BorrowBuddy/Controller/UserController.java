package com.example.BorrowBuddy.Controller;

import com.example.BorrowBuddy.Model.User;
import com.example.BorrowBuddy.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
//    constructor injection
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/{Userid}")
    public ResponseEntity<User> getUser(@PathVariable long Userid){
        return userRepository.findById(Userid).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User saved=userRepository.save(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
