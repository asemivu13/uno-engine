package middleware;

import cards.Card;

public abstract class VerificationMiddleware {
    private VerificationMiddleware next;
    public static VerificationMiddleware link(VerificationMiddleware first, VerificationMiddleware... chain) {
        VerificationMiddleware head = first;
        for (VerificationMiddleware nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(Card card, Card onTopCard);

    public boolean checkNext(Card card, Card onTopCard) {
        if (next == null) {
            return false;
        }
        return next.check(card, onTopCard);
    }
}
