package cards;

import java.util.List;
import java.util.Stack;

public class DrawPile {
    private Stack<Card> cards = new Stack<>();

    public DrawPile(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void add(Card card) {
        cards.push(card);
    }
    public Card draw() {
        return cards.pop();
    }
    public int size() {
        return cards.size();
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }
    @Override
    public String toString() {
        return "DiscardPile{" +
                "size=" + size() +
                '}';
    }
}
