package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CardController {
    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    @GetMapping("/cards/byColor/{color}")
    public List<Card> getAllCardsByColor(@PathVariable String color){
        return cardRepository.findByColor(color);
    }

    @PostMapping("/cards")
    public Card saveCard(@RequestBody Card card) {
        try {
            CardValidation.validateCard(card);
            cardRepository.save(card);
            return card;
        } catch (CardException ex) {
            log.error("Error saving card: {}", ex.getMessage());
            throw ex;
        }
    }

    @PutMapping("/cards")
    public Card updateCard(@RequestBody long id, @RequestBody Card card) {
        try {
            card.setId(id);
            return cardRepository.update(card);
        } catch (CardException ex) {
            log.error("Error updating burger: {}", ex.getMessage());
            throw ex;
        }
    }

    @DeleteMapping("/cards/{id}")
    public void deleteCard(@PathVariable long id) {
        cardRepository.remove(id);
    }

    @GetMapping("/cards/byValue/{value}")
    public List<Card> getAllCardsByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/cards/byType/{type}")
    public List<Card> getAllCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }
}
