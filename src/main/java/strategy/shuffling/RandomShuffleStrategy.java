package strategy.shuffling;

import cards.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
