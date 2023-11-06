package strategy.shuffling;

import cards.Card;
import java.util.Stack;

public class NoShufflingStrategy implements ShuffleStrategy {
    public Stack<Card> shuffle(Stack<Card> cards) {
        return cards;
    }
}
