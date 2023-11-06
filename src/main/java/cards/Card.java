package cards;
import data.CardType;
import data.Color;

import java.util.Objects;

public abstract class Card {
    private Color color;
    private CardType type;
    private int score;

    public Card(CardType type, Color color, int score) {
        this.color = color;
        this.type = type;
        this.score = score;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", type=" + type +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return score == card.score && color == card.color && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, score);
    }
}