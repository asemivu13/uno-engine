package strategy.draw;

import cards.Card;
import mediator.CardController;
import player.Player;
import template_variation.Game;

import java.util.ArrayList;
import java.util.List;

public class DrawOnceStrategy implements DrawStrategy {
    private final Game game;
    public DrawOnceStrategy(Game game) {
        this.game = game;
    }
    @Override
    public boolean draw(Player player) {
        List<Card> cards = new ArrayList<>();
        CardController cardController = game.getCardManager();
        Card card = cardController.drawCard();
        cards.add(card);
        player.addCard(cards);
        player.setDraw(true);
        return true;
    }
}
