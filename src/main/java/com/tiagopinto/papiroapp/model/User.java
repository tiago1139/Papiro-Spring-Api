package com.tiagopinto.papiroapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-ratings")
    private List<Rating> ratings = new ArrayList<>();

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column
    private String email;

    @ManyToMany
    @JoinTable(name = "user_favorites",
        joinColumns = { @JoinColumn(name = "fk_user") },
        inverseJoinColumns = { @JoinColumn(name = "fk_book") })
    private Set<Book> favorites = new HashSet<Book>();
}
