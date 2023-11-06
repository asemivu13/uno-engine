import cards.Card;
import cards.factory.CardFactory;
import cards.factory.ClassicCardFactory;
import command.*;
import command.factory.CommandSelector;
import command.factory.DefaultCommandSelector;
import data.CardType;
import middleware.*;
import command.observer.CommandExtender;
import player.Player;
import strategy.card_generation.CardGeneratorStrategy;
import strategy.card_generation.ManualCardGeneratorStrategy;
import strategy.distribution.DistributionStrategy;
import strategy.distribution.WholeDistributionStrategy;
import strategy.draw.DrawOnceStrategy;
import strategy.draw.DrawStrategy;
import strategy.handleontop.DefaultHandleOnTop;
import strategy.handleontop.HandleOnTopStrategy;
import strategy.penalization.DefaultPenalized;
import strategy.penalization.PenalizedStrategy;
import strategy.scoring.BasicScoringStrategy;
import strategy.scoring.ScoringStrategy;
import strategy.shuffling.NoShufflingStrategy;
import strategy.shuffling.ShuffleStrategy;
import template_variation.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


// handle using file

public class ABCVariation extends Game {
    private final Scanner fileScanner;
    public ABCVariation() {
        File file = new File("uno_game_test.txt");
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
        return new NoShufflingStrategy();
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
        CommandSelector commandSelector = new DefaultCommandSelector(getPlayerIterator(), getCardController());
        return commandSelector.selector(type);
    }

    @Override
    public String handleInputMechanism() {
        if (fileScanner.hasNextLine()) {
            return fileScanner.nextLine();
        } else {
            return "exit";
        }
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
}
