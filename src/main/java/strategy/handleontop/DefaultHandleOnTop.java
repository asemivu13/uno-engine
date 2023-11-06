package strategy.handleontop;

import cards.Card;
import cards.DrawPile;
import mediator.CardController;

public class DefaultHandleOnTop implements HandleOnTopStrategy {
    private final CardController cardController;
    public DefaultHandleOnTop(CardController cardController) {
        this.cardController = cardController;
    }
    @Override
    public void handleOnTop() {
        DrawPile drawPile = cardController.getDrawPile();
        Card drawOnTopCard = drawPile.draw();
        cardController.addToDiscard(drawOnTopCard);
    }
}
