package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository{
    private EntityManager entityManager;

    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public Card findById(long id) {
        return entityManager.find(Card.class, id);
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE card_color = :color", Card.class);
        query.setParameter("color", color);
        List<Card> result = query.getResultList();

        if (result.isEmpty()) {
            throw new CardException("No cards found with color: " + color, HttpStatus.NOT_FOUND);
        }

        return result;
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE value = :value", Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE card_type = :type", Card.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    @Transactional
    public Card remove(Long id) {
        Card removedCard = findById(id);
        entityManager.remove(removedCard);
        return removedCard;
    }
}
