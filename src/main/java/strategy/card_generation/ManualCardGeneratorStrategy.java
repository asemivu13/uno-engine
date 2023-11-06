package strategy.card_generation;

import cards.Card;
import data.CardType;
import data.Color;
import cards.factory.CardFactory;

import java.util.ArrayList;
import java.util.List;

public class ManualCardGeneratorStrategy extends CardGeneratorStrategy {
    public ManualCardGeneratorStrategy(CardFactory cardFactory) {
        super(cardFactory);
    }

    @Override
    public List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        // number 1-9 x2 4 colors
        Color[] colors = Color.values();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k < 10; k++) {
                    cards.add(cardFactory.createCard(CardType.NUMBER, colors[j], String.valueOf(k), k));
                }
            }
        }
        // 0 -> 4 colors
        for (int i = 0; i < 4; i++) {
            cards.add(cardFactory.createCard(CardType.NUMBER, colors[i], "0", 0));
        }

        // action cards BLOCK
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(cardFactory.createCard(CardType.BLOCK, colors[j], "BLOCK", 20));
            }
        }
        // action cards REVERSE
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(cardFactory.createCard(CardType.REVERSE, colors[j], "REVERSE", 20));
            }
        }
        // action cards ADD_TWO_CARD
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(cardFactory.createCard(CardType.ADD_TWO, colors[j], "ADD_TWO", 20));
            }
        }
        // WILD CARD
        for (int i = 0; i < 4; i++) {
            cards.add(cardFactory.createCard(CardType.WILD_CARD, Color.NO_COLOR, "WILD_CARD", 50));
        }
        // WILD FOUR CARD
        for (int i = 0; i < 4; i++) {
            cards.add(cardFactory.createCard(CardType.WILD_CARD_FOUR, Color.NO_COLOR, "WILD_CARD_FOUR", 50));
        }
        return cards;
    }
}
