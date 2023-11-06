import cards.Card;
import cards.factory.CardFactory;
import cards.factory.ClassicCardFactory;
import command.*;
import data.CardType;
import mediator.CardController;
import middleware.*;
import command.observer.CommandExtender;
import player.Player;
import player.PlayerIterator;
import strategy.card_generation.CardGeneratorStrategy;
import strategy.card_generation.ManualCardGeneratorStrategy;
import strategy.distribution.DistributionStrategy;
import strategy.distribution.WholeDistributionStrategy;
import strategy.draw.DrawOnceStrategy;
import strategy.draw.DrawStrategy;
import strategy.draw.DrawUntilFind;
import strategy.handleontop.DefaultHandleOnTop;
import strategy.handleontop.HandleOnTopStrategy;
import strategy.penalization.DefaultPenalized;
import strategy.penalization.PenalizedStrategy;
import strategy.scoring.BasicScoringStrategy;
import strategy.scoring.ScoringStrategy;
import strategy.shuffling.RandomShuffleStrategy;
import strategy.shuffling.ShuffleStrategy;
import template_variation.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsemVariation extends Game implements CommandExtender {
    public AsemVariation() {
        registerObserver(this);
    }
    public HashMap<CardType, List<Command>> commands = new HashMap<>();
    @Override
    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        System.out.println("Enter number of players");
        int playerNumber = Integer.parseInt(getScanner().nextLine());
        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Enter player name");
            String playerName = getScanner().nextLine();
            players.add(new Player(playerName));
        }
        return players;
    }

    @Override
    public List<Card> createCards() {
        CardFactory cardFactory = new ClassicCardFactory();
        CardGeneratorStrategy manualCardGeneratorStrategy = new ManualCardGeneratorStrategy(cardFactory);
        return manualCardGeneratorStrategy.createCards();
    }

    @Override
    public HandleOnTopStrategy specifyHandleOnTopStrategy() {
        return new DefaultHandleOnTop(getCardController());
    }

    @Override
    public DistributionStrategy specifyDistributionStrategy() {
        System.out.println("Enter the number of card you want to distribute to the player");
        int numberOfCards = Integer.parseInt(getScanner().nextLine());
        return new WholeDistributionStrategy(numberOfCards, getPlayerIterator());
    }

    @Override
    public ShuffleStrategy specifyShuffleStrategy() {
        return new RandomShuffleStrategy();
    }

    @Override
    public ScoringStrategy specifyScoringStrategy() {
        return new BasicScoringStrategy(getPlayerIterator());
    }

    @Override
    public PenalizedStrategy specifyPenalizationStrategy() {
        return new DefaultPenalized(this);
    }

    @Override
    public DrawStrategy specifyDrawStrategy() {
        return new DrawOnceStrategy(this);
    }

    @Override
    public boolean isGameNotOver() {
        return getLeaderboard().getMaxScore() <= 500;
    }

    @Override
    public List<Command> getCommandsByType(CardType type) {
        if (commands.containsKey(type)) {
            return commands.get(type);
        }
        PlayerIterator playerIterator = getPlayerIterator();
        CardController cardController = getCardController();
        List<Command> typeCommands = new ArrayList<>();
        switch (type) {
            case BLOCK:
                typeCommands.add(new BlockCommand(getPlayerIterator()));
                typeCommands.add(new BlockCommand(playerIterator));
                commands.put(CardType.BLOCK, typeCommands);
                break;
            case ADD_TWO:
                typeCommands.add(new AddTwoCommand(playerIterator, cardController.getDrawPile()));
                break;
            case REVERSE:
                typeCommands.add(new ReverseCommand(playerIterator));
                typeCommands.add(new BlockCommand(playerIterator));
                break;
            case NUMBER:
                typeCommands.add(new BlockCommand(playerIterator));
                break;
            case WILD_CARD_FOUR:
                typeCommands.add(new ChangeColorCommand(cardController.getDiscardPile()));
                typeCommands.add(new AddFourCommand(playerIterator, cardController.getDrawPile()));
                break;
            case WILD_CARD:
                typeCommands.add(new ChangeColorCommand(cardController.getDiscardPile()));
                typeCommands.add(new BlockCommand(playerIterator));
                break;
        }
        return typeCommands;
    }

    @Override
    public String handleInputMechanism() {
        return getScanner().nextLine();
    }

    @Override
    public VerificationMiddleware verify() {
        return VerificationMiddleware.link(
                new ColorMiddleware(),
                new ValueMiddleware(),
                new WildCardMiddleware(),
                new WildFourCardMiddleware(getPlayerIterator())
        );
    }

    @Override
    public void addExtraCommand(String userInput) {

    }
}
