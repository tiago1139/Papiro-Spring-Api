package com.tiagopinto.papiroapp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonProperty("book")
    @JsonBackReference(value = "book-ratings")
    private Book book;


    @Column(name = "`value`")
    private int value;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-ratings")
    @JsonProperty("user")
    private User user;


}

