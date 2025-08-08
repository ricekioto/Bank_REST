package com.example.bankcards.controller;

import com.example.bankcards.entity.Card;
import com.example.bankcards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // Получение карточки по номеру
    @GetMapping("/number/{number}")
    public ResponseEntity<Card> getCardByNumber(@PathVariable Long number) {
        Optional<Card> cardOptional = Optional.ofNullable(cardService.findByNumber(number));

        if (cardOptional.isPresent()) {
            return ResponseEntity.ok(cardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Получение карточки по ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        try {
            Card card = cardService.findById(id); // Важно: Может выбросить исключение
            return ResponseEntity.ok(card);
        } catch (Exception e) { // Перехватываем любое возможное исключение
            return ResponseEntity.notFound().build();
        }
    }

    // Создание новой карточки
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardService.save(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    // Обновление существующей карточки (замена) - важно, что мы не знаем какие поля нужно обновить
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        if (!cardService.existsById(card.getId())) {
            return ResponseEntity.notFound().build();
        }

        card.setId(card.getId()); // Устанавливаем ID, чтобы Hibernate обновил существующую запись
        Card updatedCard = cardService.update(card);  // Используем метод update из вашего сервиса
        return ResponseEntity.ok(updatedCard);
    }


    // Удаление карточки по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        try {
            cardService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Если deleteById выбросил исключение, значит, скорее всего, карточка не найдена
            return ResponseEntity.notFound().build();
        }
    }
}
