package cards;
import data.Color;

import java.util.Stack;

public class DiscardPile {
    private final Stack<Card> cards = new Stack<>();
    public void add(Card card) {
        cards.push(card);
    }
    public Card remove() {
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

    public void setColor(Color color) {
        Card card = cards.peek();
        card.setColor(color);
    }
    public Card peek() {
        return cards.peek();
    }

    @Override
    public String toString() {
        return "DiscardPile{" +
                "onTop=" + peek() +
                ", size=" + size() +
                '}';
    }
}
