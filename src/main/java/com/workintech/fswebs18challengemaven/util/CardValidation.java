package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validateCard(Card card) {
        if (card == null) {
            throw new CardException("Card cannot be null.", HttpStatus.BAD_REQUEST);
        }

        if (card.getCardType() == null && card.getValue() == null) {
            throw new CardException("Card must have either a type or a value.", HttpStatus.BAD_REQUEST);
        }

        if (card.getCardType() != null && card.getValue() != null) {
            throw new CardException("Card cannot have both a type and a value.", HttpStatus.BAD_REQUEST);
        }

        if (card.getCardType() == Type.JOKER) {
            if (card.getColor() != null) {
                throw new CardException("JOKER cards must not have a value or color.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            if (card.getValue() == null && card.getCardType() == null) {
                throw new CardException("Non-JOKER cards must have a value or type.", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
