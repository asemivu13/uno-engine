package middleware;

import cards.Card;
import data.CardType;
import data.Color;

public class WildCardMiddleware extends VerificationMiddleware {
    @Override
    public boolean check(Card card, Card onTopCard) {
        if (onTopCard.getType() == CardType.WILD_CARD) {
            if (onTopCard.getColor() == Color.NO_COLOR) {
                return true;
            }
        }
        if (card.getType() == CardType.WILD_CARD) {
            return true;
        }
        return checkNext(card, onTopCard);
    }
}
