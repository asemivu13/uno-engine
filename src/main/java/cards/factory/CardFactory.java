package cards.factory;

import cards.Card;
import data.CardType;
import data.Color;

public interface CardFactory {
    Card createCard(CardType type, Color color, String value, int score);
}
