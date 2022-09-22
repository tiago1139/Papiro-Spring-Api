package com.tiagopinto.papiroapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagopinto.papiroapp.exceptions.ResourceNotFoundException;
import com.tiagopinto.papiroapp.model.Book;
import com.tiagopinto.papiroapp.model.Category;
import com.tiagopinto.papiroapp.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
//@CrossOrigin(origins= "https://tiago1139.github.io")
public class BookController {

    //private static String coverUrl = "https://papiro-spring-api.herokuapp.com/api/images/";
    private static String coverUrl = "http://localhost:8080/api/images/";

    private static String imagePath = Paths.get("src/main/resources/images").toAbsolutePath().toString();
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookRepository.findById(id);
    }



    @PostMapping(value = "/books", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> createBook(@RequestParam("cover") MultipartFile file,
                                           @RequestParam("book") String book) throws JsonProcessingException {

        Book b = new ObjectMapper().readValue(book, Book.class);

        Path test = Paths.get("src/main/resources/images").toAbsolutePath();
        System.out.println(test);

        System.out.println(b.getTitle());
        System.out.println(b.getAuthor());
        System.out.println(b.getIsbn());

        try {
            if(!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path currentDir = Paths.get(".");
                long rows = bookRepository.findLastId();
                System.out.println(rows);

                Path path = Paths.get(imagePath+File.separator
                        +String.valueOf(rows+1)+"_cover.jpg");


                Files.write(path, bytes);

                File f = new File(String.valueOf(path));

                String imageUrl = coverUrl+String.valueOf(rows+1)+"_cover.jpg";

                b.setCover(imageUrl);
                System.out.println(b.getCover());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRepository.save(b));
    }

    
    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) throws ResourceNotFoundException {

        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));



        return ResponseEntity.status(HttpStatus.OK)
                .body(bookRepository.save(book));
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        bookRepository.deleteById(id);
        //Delete book cover jpg
        try {
            Path path = Paths.get(imagePath+File.separator
                    +String.valueOf(id)+"_cover.jpg");
            File f = new File(String.valueOf(path));
            Files.deleteIfExists(f.toPath());
        }
        catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        }
        catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

        System.out.println("Deletion successful.");

        return ResponseEntity.noContent().build();

    }
}
