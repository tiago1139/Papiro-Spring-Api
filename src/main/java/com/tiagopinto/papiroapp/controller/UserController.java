package com.tiagopinto.papiroapp.controller;

import com.tiagopinto.papiroapp.exceptions.ResourceNotFoundException;
import com.tiagopinto.papiroapp.model.Book;
import com.tiagopinto.papiroapp.model.Rating;
import com.tiagopinto.papiroapp.model.User;
import com.tiagopinto.papiroapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins= "https://tiago1139.github.io")
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/users/name/{name}")
    public Optional<User> getUserByName (@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @GetMapping("/users/id/{id}")
    public Optional<User> getUserById (@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers (){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser( @RequestBody User user) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRepository.save(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userRepository.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
}
