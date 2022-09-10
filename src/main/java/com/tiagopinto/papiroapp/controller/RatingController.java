package com.tiagopinto.papiroapp.controller;

import com.tiagopinto.papiroapp.exceptions.ResourceNotFoundException;
import com.tiagopinto.papiroapp.model.Book;
import com.tiagopinto.papiroapp.model.Rating;
import com.tiagopinto.papiroapp.repository.RatingRepository;
import com.tiagopinto.papiroapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins= "https://tiago1139.github.io")
public class RatingController {

    private RatingRepository ratingRepository;

    @GetMapping("/ratings")
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/ratings/book/{bookId}")
    public List<Rating> getRatingsByBook(@PathVariable Long bookId) {
        return ratingRepository.findByBook(bookId);
    }

    @GetMapping("/rating/user/{userId}/book/{bookId}")
    public Optional<Rating> getRatingByUserAndBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return ratingRepository.findByUserAndBook(userId, bookId);
    }

    @PostMapping(path="/ratings")
    public ResponseEntity<Rating> createRating(@Valid @RequestBody Rating rating) {
        System.out.println("Value: "+rating.getValue());
        System.out.println("Book: "+rating.getBook().getTitle());
        System.out.println("User: "+rating.getUser().getUsername());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ratingRepository.save(rating));
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ratingRepository.save(rating));
    }

    @DeleteMapping("/rating/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {

        ratingRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
}
