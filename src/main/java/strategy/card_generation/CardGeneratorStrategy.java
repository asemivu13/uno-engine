package strategy.card_generation;

import cards.Card;
import cards.factory.CardFactory;

import java.util.List;

public abstract class CardGeneratorStrategy {
    CardFactory cardFactory;

    public CardGeneratorStrategy(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public CardFactory getCardFactory() {
        return cardFactory;
    }

    public void setCardFactory(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public abstract List<Card> createCards();
}
