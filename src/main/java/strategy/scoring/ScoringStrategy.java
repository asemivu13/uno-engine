package strategy.scoring;

import player.Player;

public interface ScoringStrategy {
    int calculateScore(Player player);
}
