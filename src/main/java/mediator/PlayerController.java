package mediator;

import player.PlayerIterator;

public class PlayerController {
    private PlayerIterator playerIterator;

    public PlayerController(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
    }

    public void setPlayerIterator(PlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
    }

    public PlayerIterator getPlayerIterator() {
        return playerIterator;
    }
}
