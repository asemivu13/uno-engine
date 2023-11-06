package cards;

import data.CardType;
import data.Color;

public class NumberCard extends Card {
    private final String value;
    public NumberCard(String value, Color color, int score) {
        super(CardType.NUMBER, color, score);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NumberCard{" +
                "color='" + getColor() + '\'' +
                " value='" + value + '\'' +
                '}';
    }
}
