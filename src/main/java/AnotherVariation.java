import cards.Card;
import command.Command;
import data.CardType;
import middleware.VerificationMiddleware;
import player.Player;
import strategy.distribution.DistributionStrategy;
import strategy.draw.DrawStrategy;
import strategy.handleontop.HandleOnTopStrategy;
import strategy.penalization.PenalizedStrategy;
import strategy.scoring.ScoringStrategy;
import strategy.shuffling.ShuffleStrategy;
import template_variation.Game;

import java.util.List;

public class AnotherVariation extends Game {

    @Override
    public List<Player> createPlayers() {
        return null;
    }

    @Override
    public List<Card> createCards() {
        return null;
    }

    @Override
    public HandleOnTopStrategy specifyHandleOnTopStrategy() {
        return null;
    }

    @Override
    public DistributionStrategy specifyDistributionStrategy() {
        return null;
    }

    @Override
    public ShuffleStrategy specifyShuffleStrategy() {
        return null;
    }

    @Override
    public ScoringStrategy specifyScoringStrategy() {
        return null;
    }

    @Override
    public PenalizedStrategy specifyPenalizationStrategy() {
        return null;
    }

    @Override
    public DrawStrategy specifyDrawStrategy() {
        return null;
    }

    @Override
    public boolean isGameNotOver() {
        return false;
    }

    @Override
    public List<Command> getCommandsByType(CardType type) {
        return null;
    }

    @Override
    public String handleInputMechanism() {
        return null;
    }

    @Override
    public VerificationMiddleware verify() {
        return null;
    }
}
