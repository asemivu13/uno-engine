package strategy.draw;

import cards.Card;
import command.PlayCardCommand;
import mediator.CardController;
import player.Player;
import template_variation.Game;

import java.util.ArrayList;
import java.util.List;

public class DrawUntilFind implements DrawStrategy {
    private final Game game;
    public DrawUntilFind(Game game) {
        this.game = game;
    }
    @Override
    public boolean draw(Player player) {
        CardController cardController = game.getCardManager();
        List<Card> cards = new ArrayList<>();
        boolean executed = true;
        while (executed) {
            Card card = cardController.drawCard();
            PlayCardCommand playCardCommand = new PlayCardCommand(card, game);
            executed = !playCardCommand.execute();
            if (executed) {
                cards.add(card);
            } else {
                System.out.println("THE CARD THAT GOT EXECUTED BY THE DRAW IS => " + card);
            }
        }
        player.addCard(cards);
        return true;
    }
}
