package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(Long number);

    boolean existsById(Long number);

    Card findById(long id);

    Card save(Card card);

    Card update(Card card);

    void deleteById(Long id);

}
