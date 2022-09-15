package com.tiagopinto.papiroapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Entity
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
        joinColumns = { @JoinColumn(name = "fk_category") },
        inverseJoinColumns = { @JoinColumn(name = "fk_book") })
    private List<Book> books = new ArrayList<>();
}
