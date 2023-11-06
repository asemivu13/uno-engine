package strategy.distribution;

import cards.Card;
import player.Player;
import player.PlayerIterator;

import java.util.ArrayList;
import java.util.List;

public class WholeDistributionStrategy implements DistributionStrategy {
    private final int numOfCards;
    private final PlayerIterator playerIterator;
    public WholeDistributionStrategy(int numOfCards, PlayerIterator playerIterator) {
        this.numOfCards = numOfCards;
        this.playerIterator = playerIterator;
    }
    @Override
    public void distribute(List<Card> cards) {
        for (int i = 0; i < playerIterator.size(); i++) {
            List<Card> tempCard = new ArrayList<>();
            Player player = playerIterator.getCurrent();
            for (int j = 0; j < numOfCards; j++) {
                tempCard.add(cards.remove(j));
            }
            player.setCards(tempCard);
            playerIterator.getNext();
        }
    }
}
