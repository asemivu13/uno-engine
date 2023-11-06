package cards;

import data.CardType;
import data.Color;

public class ActionCard extends Card {
    public ActionCard(CardType type, Color color, int score) {
        super(type, color, score);
    }

    @Override
    public String toString() {
        return "ActionCard{" +
                "color=" + getColor() +
                ", type=" + getType() +
                '}';
    }
}
