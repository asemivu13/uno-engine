package mediator;

import cards.Card;
import cards.DiscardPile;
import cards.DrawPile;
import strategy.distribution.DistributionStrategy;
import strategy.handleontop.HandleOnTopStrategy;
import strategy.shuffling.ShuffleStrategy;

import java.util.List;
import java.util.Stack;

public class CardController {
    private final DrawPile drawPile;
    private final DiscardPile discardPile;
    private DistributionStrategy distributionStrategy;
    private ShuffleStrategy shuffleStrategy;
    private HandleOnTopStrategy handleOnTopStrategy;

    public CardController(List<Card> cards) {
        this.drawPile = new DrawPile(cards);
        this.discardPile = new DiscardPile();
    }
    public void moveFromDiscardToDraw() {
        while (!discardPile.isEmpty()) {
            drawPile.add(discardPile.remove());
        }
    }

    public void shuffle() {
        if (shuffleStrategy == null) {
            throw new NullPointerException("you must provide shuffle strategy");
        }
        Stack<Card> shuffledCards = shuffleStrategy.shuffle(drawPile.getCards());
        drawPile.setCards(shuffledCards);
    }
    public void distribute() {
        if (distributionStrategy == null) {
            throw new NullPointerException("You must provide a distribution strategy");
        }
        if (drawPile.isEmpty()) {
            throw new IndexOutOfBoundsException("You can't distribute no cards");
        }
        distributionStrategy.distribute(drawPile.getCards());
    }
    public void handleOnTop() {
        if (handleOnTopStrategy == null) {
            throw new NullPointerException("You must provide handle on strategy");
        }
        handleOnTopStrategy.handleOnTop();
    }
    public void addToDiscard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("you can't pass null cards");
        }
        discardPile.add(card);
    }
    public Card drawCard() {
        if (drawPile.size() == 0) {
            reshuffleDrawPileFromDiscard();
        }
        return drawPile.draw();
    }
    public void reshuffleDrawPileFromDiscard() {
        if (discardPile.isEmpty()) {
            throw new IndexOutOfBoundsException("discard pile is empty");
        }
        Card onTopCard = discardPile.remove();
        while (!discardPile.isEmpty()) {
            drawPile.add(discardPile.remove());
        }
        discardPile.add(onTopCard);
        shuffle();
    }
    public void setShuffleStrategy(ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }
    public void setDistributionStrategy(DistributionStrategy distributionStrategy) {
        this.distributionStrategy = distributionStrategy;
    }
    public void setHandleOnTopStrategy(HandleOnTopStrategy handleOnTopStrategy) {
        this.handleOnTopStrategy = handleOnTopStrategy;
    }
    public ShuffleStrategy getShuffleStrategy() {
        return shuffleStrategy;
    }
    public DistributionStrategy getDistributionStrategy() {
        return distributionStrategy;
    }
    public HandleOnTopStrategy getHandleOnTopStrategy() {
        return handleOnTopStrategy;
    }
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
    public DrawPile getDrawPile() {
        return drawPile;
    }
    public void addToDrawPile(List<Card> cards) {
        for (Card card : cards) {
            drawPile.add(card);
        }
    }
}
