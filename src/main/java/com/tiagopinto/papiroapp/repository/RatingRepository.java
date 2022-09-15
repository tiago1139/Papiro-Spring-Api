package com.tiagopinto.papiroapp.repository;

import com.tiagopinto.papiroapp.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.book.id = ?1")
    List<Rating> findByBook(Long bookId);

    @Query("SELECT r FROM Rating r WHERE r.user.id = ?1 AND r.book.id = ?2")
    Optional<Rating> findByUserAndBook(Long userId, Long bookId);
}
