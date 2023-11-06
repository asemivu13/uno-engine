package player;

import cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Player  {
    private final String name;
    private List<Card> cards = new ArrayList<>();
    private int score = 0;
    private boolean isDraw;
    private boolean blocked;
    private boolean isSaidUno;

    public Player() {
        this.name = "RANDOM_NAME" + UUID.randomUUID();
    }
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public void addCard(List<Card> cardList) {
        cards.addAll(cardList);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void resetCard() {
        cards = new ArrayList<>();
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isSaidUno() {
        return isSaidUno;
    }

    public void setSaidUno(boolean saidUno) {
        isSaidUno = saidUno;
    }
    public void reset() {
        isDraw = false;
        isSaidUno = false;
        blocked = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score &&
                isDraw == player.isDraw &&
                blocked == player.blocked &&
                isSaidUno == player.isSaidUno &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, isDraw, blocked, isSaidUno);
    }
}
