package strategy.shuffling;

import cards.Card;
import java.util.Stack;

public interface ShuffleStrategy {
    Stack<Card> shuffle(Stack<Card> cards);
}
