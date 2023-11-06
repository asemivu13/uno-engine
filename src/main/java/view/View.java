package view;

import mediator.CardController;
import player.Player;

public interface View {
    void initializeGame();
    void displayOnTopCard(CardController cardController);
    void displayPlayerData(Player player);
    void handleSaidUno(Player player);
    void resetGame();
    void handleWinner(Player player, int score);
}
