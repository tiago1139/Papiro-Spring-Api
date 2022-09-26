package com.tiagopinto.papiroapp.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    @Column(nullable = false)
    private String author;

    @JsonProperty("isbn")
    @Column(columnDefinition = "varchar(20) default '12345'")
    private String isbn;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "book-ratings")
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();



    @JsonProperty("rank")
    @Column
    private Double rank;

    @JsonProperty("cover")
    @Column(nullable = false)
    private String cover;
}
