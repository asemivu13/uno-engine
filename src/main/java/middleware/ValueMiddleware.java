package middleware;

import cards.ActionCard;
import cards.Card;
import cards.NumberCard;

import java.util.Objects;

public class ValueMiddleware extends VerificationMiddleware {

    @Override
    public boolean check(Card card, Card onTopCard) {
        if (card instanceof NumberCard) {
            if (onTopCard instanceof NumberCard) {
                String numberCardValue = ((NumberCard) card).getValue();
                if (Objects.equals(((NumberCard) onTopCard).getValue(), numberCardValue)) {
                    return true;
                }
            }
        }
        if (card instanceof ActionCard) {
            if (onTopCard instanceof ActionCard) {
                if (card.getType() == onTopCard.getType()) {
                    return true;
                }
            }
        }
        return checkNext(card, onTopCard);
    }
}
