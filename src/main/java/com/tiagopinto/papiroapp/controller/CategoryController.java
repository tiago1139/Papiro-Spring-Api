package com.tiagopinto.papiroapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagopinto.papiroapp.exceptions.ResourceNotFoundException;
import com.tiagopinto.papiroapp.model.Book;
import com.tiagopinto.papiroapp.model.Category;
import com.tiagopinto.papiroapp.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins= {"${origin}"})
public class CategoryController {
    @Autowired
    private CategoryRepository catRepository;

    @GetMapping("/categories")
    public List<Category> getAll() {
        return catRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> getBook(@PathVariable Long id) {
        return catRepository.findById(id);
    }
    
    @PostMapping(path="/categories")
    public ResponseEntity<Category> createRating(@Valid @RequestBody Category category) {
        System.out.println("Category id: "+category.getId());
        System.out.println("Category name: "+category.getName());
        

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(catRepository.save(category));
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) throws ResourceNotFoundException {

        System.out.println("Categoria: "+category.getName());
        Category c = catRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        for (Book b : category.getBooks()) {
            System.out.println("Book : "+b.getTitle());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(catRepository.save(category));
    }
}
