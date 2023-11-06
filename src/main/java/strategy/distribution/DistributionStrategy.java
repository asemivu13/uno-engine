package strategy.distribution;

import cards.Card;
import player.PlayerIterator;

import java.util.List;

public interface DistributionStrategy {
    void distribute(List<Card> cards);
}
