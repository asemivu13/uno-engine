package command;

import cards.Card;
import cards.DiscardPile;
import mediator.CardController;
import middleware.VerificationMiddleware;
import player.Player;
import player.PlayerIterator;
import template_variation.Game;

public class PlayCardCommand implements Command {
    private final Card card;
    private final Game game;

    public PlayCardCommand(Card card, Game game) {
        this.card = card;
        this.game = game;
    }

    @Override
    public boolean execute() {
        CardController cardController = game.getCardManager();
        VerificationMiddleware verificationMiddleware = game.getVerificationMiddleware();
        DiscardPile discardPile = cardController.getDiscardPile();
        PlayerIterator playerIterator = game.getPlayerIterator();
        Player player = playerIterator.getCurrent();
        if (verificationMiddleware.check(card, discardPile.peek())) {
            cardController.addToDiscard(card);
            player.removeCard(card);
            for (Command command : game.getCommandsByType(card.getType())) {
                command.execute();
            }
            return true;
        }
        return false;
    }
}
