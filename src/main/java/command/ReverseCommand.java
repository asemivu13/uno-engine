package command;

import player.PlayerIterator;

public class ReverseCommand implements Command {
    private final PlayerIterator playerIterator;
    public ReverseCommand(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
    }
    @Override
    public boolean execute() {
        playerIterator.reverse();
        return true;
    }
}
