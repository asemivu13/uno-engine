package middleware;

import cards.Card;

public class ColorMiddleware extends VerificationMiddleware {

    @Override
    public boolean check(Card card, Card onTopCard) {
        if (card.getColor() == onTopCard.getColor()) {
            return true;
        }
        return checkNext(card, onTopCard);
    }
}
