package view;

import cards.Card;
import mediator.CardController;
import player.Player;

import java.util.List;

public class ConsoleView implements View {
    @Override
    public void initializeGame() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("[skip] to skip your turn (only allowed when you draw)");
        System.out.println("[draw] draw card from the draw pile");
        System.out.println("[<number>] specify card from the player hand");
        System.out.println("[<number> uno] when you have only one card");
        System.out.println("-----------------------------------------------------------");
    }

    @Override
    public void displayOnTopCard(CardController cardController) {
        System.out.println("ON TOP CARD => " + cardController.getDiscardPile().peek());
    }

    @Override
    public void displayPlayerData(Player player) {
        System.out.println("[ Player: " + player.getName() + " ]");
        List<Card> cards = player.getCards();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("[ " + i + " ] " + cards.get(i));
        }
    }

    @Override
    public void handleSaidUno(Player player) {
        if (!player.isSaidUno()) {
            System.out.println("You failed to said uno => you will get penalized");
        }
    }

    @Override
    public void resetGame() {
        System.out.println("THE GAME WILL RESET NOW (NEXT ROUND LOADING....");
    }

    @Override
    public void handleWinner(Player player, int score) {
        System.out.println("THE WINNER OF THIS ROUND IS => " + player.getName() + " WITH SCORE => " + score);
    }
}
