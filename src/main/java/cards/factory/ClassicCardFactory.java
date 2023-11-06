package cards.factory;

import cards.ActionCard;
import cards.Card;
import cards.NumberCard;
import data.CardType;
import data.Color;

import static data.CardType.NUMBER;

public class ClassicCardFactory implements CardFactory {
    @Override
    public Card createCard(CardType type, Color color, String value, int score) {
        if (type == NUMBER) {
            return new NumberCard(value, color, score);
        } else {
            return new ActionCard(type, color, score);
        }
    }
}
