package middleware;

import cards.Card;
import data.CardType;
import data.Color;
import player.Player;
import player.PlayerIterator;

public class WildFourCardMiddleware extends VerificationMiddleware {
    private final PlayerIterator playerIterator;
    private final VerificationMiddleware verificationMiddleware;
    public WildFourCardMiddleware(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
        verificationMiddleware = VerificationMiddleware.link(
                new ColorMiddleware(),
                new ValueMiddleware(),
                new WildCardMiddleware()
        );
    }
    @Override
    public boolean check(Card card, Card onTopCard) {
        if (onTopCard.getType() == CardType.WILD_CARD_FOUR) {
            if (onTopCard.getColor() == Color.NO_COLOR) {
                return true;
            }
        }
        if (card.getType() != CardType.WILD_CARD_FOUR) {
            return false;
        }
        Player player = playerIterator.getCurrent();
        for (Card playerCard : player.getCards()) {
            if (playerCard == card) {
                continue;
            }
            if (verificationMiddleware.check(playerCard, onTopCard)) {
                return false;
            }
        }
        return true;
    }
}
