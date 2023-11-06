package command.factory;

import command.*;
import data.CardType;
import mediator.CardController;
import player.PlayerIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultCommandSelector implements CommandSelector {
    private final HashMap<CardType, List<Command>> commands = new HashMap<>();
    private final PlayerIterator playerIterator;
    private final CardController cardController;
    public DefaultCommandSelector(PlayerIterator playerIterator, CardController cardController) {
        this.playerIterator = playerIterator;
        this.cardController = cardController;
    }
    @Override
    public List<Command> selector(CardType type) {
        if (commands.containsKey(type)) {
            return commands.get(type);
        }
        List<Command> typeCommands = new ArrayList<>();
        switch (type) {
            case BLOCK:
                typeCommands.add(new BlockCommand(playerIterator));
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
            default:
                throw new IllegalArgumentException("invalid type");
        }
        return typeCommands;
    }
}
