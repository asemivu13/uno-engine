package strategy.scoring;

import player.Player;
import player.PlayerIterator;

public class BasicScoringStrategy implements ScoringStrategy {
    private final PlayerIterator playerIterator;
    public BasicScoringStrategy(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
    }
    @Override
    public int calculateScore(Player player) {
        int score = 0;
        for (int i = 0; i < playerIterator.size(); i++) {
            Player currentPlayer = playerIterator.getCurrent();
            // create hand or player hand and put size in it
            for (int j = 0; j < currentPlayer.getCards().size(); j++) {
                score += (currentPlayer.getCards().get(j).getScore());
            }
            playerIterator.getNext();
        }
        return score;
    }
}
