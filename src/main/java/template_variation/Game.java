package template_variation;
import cards.Card;
import command.*;
import data.CardType;
import data.Leaderboard;
import mediator.CardController;
import mediator.PlayerController;
import middleware.VerificationMiddleware;
import command.observer.CommandExtender;
import player.NormalPlayerIterator;
import view.ConsoleView;
import view.View;
import player.Player;
import player.PlayerIterator;
import strategy.distribution.DistributionStrategy;
import strategy.draw.DrawStrategy;
import strategy.handleontop.HandleOnTopStrategy;
import strategy.penalization.PenalizedStrategy;
import strategy.scoring.ScoringStrategy;
import strategy.shuffling.ShuffleStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Game {
    private final PlayerController playerController;
    private final PlayerIterator playerIterator;
    private final CardController cardController;
    private final Leaderboard leaderboard;
    private ScoringStrategy scoringStrategy;
    private DrawStrategy drawStrategy;
    private PenalizedStrategy penalizationStrategy;
    private View view;
    private Scanner scanner;
    private final VerificationMiddleware verificationMiddleware;
    private final List<CommandExtender> observers = new ArrayList<>();
    private static final Pattern CARD_UNO_INPUT_PATTERN = Pattern.compile(
            "\\d+(?:\\s+\\buno\\b)?"
    );

    public Game() {
        scanner = new Scanner(System.in);
        view = new ConsoleView();
        playerIterator = new NormalPlayerIterator(createPlayers());
        playerController = new PlayerController(playerIterator);

        List<Card> cards = createCards();
        cardController = new CardController(cards);

        leaderboard = new Leaderboard();

        verificationMiddleware = verify();
    }
    public final void play() {
        initializeGame();
        initializeCardManager();
        view.initializeGame();

        while (isGameNotOver()) {
            Player player = playerIterator.getCurrent();
            view.displayOnTopCard(cardController);
            view.displayPlayerData(player);


            if (!player.isBlocked()) {
                String userInput = getDataFromInput();
                handleUserInput(player, userInput);
            } else {
                player.setBlocked(false);
                playerIterator.getNext();
            }
            handleSaidUNO(player);
            handleWinner(player);
        }
    }
    private void initializeGame() {
        cardController.setShuffleStrategy(specifyShuffleStrategy());
        cardController.setDistributionStrategy(specifyDistributionStrategy());
        cardController.setHandleOnTopStrategy(specifyHandleOnTopStrategy());
        scoringStrategy = specifyScoringStrategy();
        penalizationStrategy = specifyPenalizationStrategy();
        drawStrategy = specifyDrawStrategy();
    }
    private void initializeCardManager() {
        cardController.shuffle();
        cardController.distribute();
        cardController.handleOnTop();
    }
    public abstract List<Player> createPlayers();
    public abstract List<Card> createCards();
    public abstract HandleOnTopStrategy specifyHandleOnTopStrategy();
    public abstract DistributionStrategy specifyDistributionStrategy();
    public abstract ShuffleStrategy specifyShuffleStrategy();
    public abstract ScoringStrategy specifyScoringStrategy();
    public abstract PenalizedStrategy specifyPenalizationStrategy();
    public abstract DrawStrategy specifyDrawStrategy();
    public abstract boolean isGameNotOver();
    public void handleUserInput(Player player, String userInput) {
        if (userInput.equalsIgnoreCase("skip") && player.isDraw()) {
            Command command = new BlockCommand(playerIterator);
            command.execute();
            player.setDraw(false);
        } else if (userInput.equalsIgnoreCase("draw") && !player.isDraw()) {
            Command command = new DrawCommand(player, drawStrategy);
            command.execute();
        // matches <number> [uno] where [uno] is optional
        } else if (CARD_UNO_INPUT_PATTERN.matcher(userInput).matches()) {
            String[] words = userInput.split(" ");
            int cardNumber = Integer.parseInt(words[0]);
            if (cardNumber < player.getCards().size()) {
                Card card = player.getCards().get(cardNumber);
                handlePlayCard(player, words, card);
            } else {
                System.out.println("provide valid card number");
            }
        } else {
            if (!observers.isEmpty()) {
                notifyObservers(userInput);
            } else {
                System.out.println("input is invalid");
            }
        }
    }
    public final void handlePlayCard(Player player, String[] words, Card card) {
        Command command = new PlayCardCommand(card, this);
        boolean executed = command.execute();
        if (executed) {
            if (words.length == 2 && words[1].equalsIgnoreCase("uno")) {
                if (player.getCards().size() == 1) {
                    player.setSaidUno(true);
                }
            }
            player.setDraw(false);
        }
    }

    public abstract List<Command> getCommandsByType(CardType type);
    public final String getDataFromInput() {
        String value = handleInputMechanism();
        if (value.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        return value;
    }
    public abstract String handleInputMechanism();
    public abstract VerificationMiddleware verify();
    public final void handleSaidUNO(Player player) {
        if (player == null) {
            throw new NullPointerException("player can't be null");
        }
        if (penalizationStrategy == null) {
            throw new NullPointerException("penalized shouldn't be null");
        }
        // fix this
        if (player.getCards().size() == 1) {
            if (!player.isSaidUno()) {
                penalizationStrategy.penalize(player);
            }
            view.handleSaidUno(player);
        }
    }
    public final void handleWinner(Player player) {
        if (player == null) {
            throw new NullPointerException("player can't be null");
        }
        if (scoringStrategy == null) {
            throw new NullPointerException("scoring strategy must not be null");
        }
        if (player.getCards().size() == 0) {
            int score = scoringStrategy.calculateScore(player);
            leaderboard.add(player, score);
            view.handleWinner(player, score);
            resetGame();

            leaderboard.displayLeaderboard();
        }
    }
    private void resetGame() {
        List<Card> cards = new ArrayList<>();
        playerIterator.reset();
        for (int i = 0; i < playerIterator.size(); i++) {
            cards.addAll(playerIterator.getCurrent().getCards());
            playerIterator.getCurrent().resetCard();
        }
        cardController.moveFromDiscardToDraw();
        cardController.addToDrawPile(cards);
        initializeCardManager();
        view.resetGame();
    }
    public CardController getCardManager() {
        return cardController;
    }
    public PlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    public VerificationMiddleware getVerificationMiddleware() {
        return verificationMiddleware;
    }

    public CardController getCardController() {
        return cardController;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Scanner getScanner() {
        return scanner;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void registerObserver(CommandExtender observer) {
        observers.add(observer);
    }
    public void unregisterObserver(CommandExtender observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String userInput) {
        for (CommandExtender observer : observers) {
            observer.addExtraCommand(userInput);
        }
    }
}
