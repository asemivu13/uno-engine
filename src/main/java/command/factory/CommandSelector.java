package command.factory;

import command.Command;
import data.CardType;

import java.util.List;

public interface CommandSelector {
    List<Command> selector(CardType type);
}
