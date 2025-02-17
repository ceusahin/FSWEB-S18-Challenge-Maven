package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Data
@Entity
@Table(name = "card", schema = "public")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type cardType;

    @Enumerated(EnumType.STRING)
    private Color cardColor;

    @Autowired
    public Card(long id, Integer value, Type cardType, Color cardColor) {
        if (cardType != null) {
            this.id = id;
            this.cardColor = cardColor;
            this.value = null;
        } else if (value != null) {
            this.id = id;
            this.cardColor = cardColor;
            this.cardType = null;
        }

        if (cardType == Type.JOKER) {
            this.id = id;
            this.value = null;
            this.cardColor = null;
        }
    }

    public Type getType() {
        return cardType;
    }

    public void setType(Type cardType) {
        this.cardType = cardType;
    }

    public Color getColor() {
        return cardColor;
    }

    public void setColor(Color cardColor) {
        this.cardColor = cardColor;
    }
}
