package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card findByNumber(Long number) {
        return cardRepository.findByNumber(number);
    }

    public boolean existsById(Long id) {
        return cardRepository.existsById(id);
    }

    public  Card findById(long id) {
        return cardRepository.findById(id);
    }

    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Transactional
    public Card update(Card card) {
        return cardRepository.save(card);
    }

    @Transactional
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
