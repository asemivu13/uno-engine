package command;

import cards.Card;
import cards.DrawPile;
import player.Player;
import player.PlayerIterator;

import java.util.ArrayList;
import java.util.List;

public class AddFourCommand implements Command {
    private final PlayerIterator playerIterator;
    private final DrawPile drawPile;
    public AddFourCommand(PlayerIterator playerIterator, DrawPile drawPile) {
        this.playerIterator = playerIterator;
        this.drawPile = drawPile;
    }
    @Override
    public boolean execute() {
        Player player = playerIterator.getNext();
        List<Card> cardList = new ArrayList<>();
        int minSize = Math.min(4, drawPile.size());
        for (int i = 0; i < minSize; i++) {
            cardList.add(drawPile.draw());
        }
        player.addCard(cardList);
        player.setBlocked(true);
        return true;
    }
}
