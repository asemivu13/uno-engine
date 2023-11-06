package command;

import player.PlayerIterator;

public class BlockCommand implements Command {
    private final PlayerIterator playerIterator;
    public BlockCommand(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
    }
    @Override
    public boolean execute() {
        playerIterator.getNext();
        return true;
    }
}
