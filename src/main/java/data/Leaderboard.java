package data;

import player.Player;

import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
    private final HashMap<Player, Integer> scores = new HashMap<>();
    private int maxScore = 0;
    public void add(Player player, int score) {
        if (player == null) {
            throw new NullPointerException("You must insert a player");
        }
        if (score < 0) {
            throw new IllegalArgumentException("score can't be less than 0");
        }
        int playerOldScore = scores.getOrDefault(player, 0);
        int playerNewScore = playerOldScore + score;
        if (playerNewScore > maxScore) {
            maxScore = playerNewScore;
        }
        scores.put(player, playerNewScore);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void displayLeaderboard() {
        scores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "maxScore=" + maxScore +
                '}';
    }
}
