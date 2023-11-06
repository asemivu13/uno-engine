package player;

import java.util.List;

public class NormalPlayerIterator implements PlayerIterator {
    private final List<Player> players;
    private int currentPosition = 0;
    private boolean reverse = false;


    public NormalPlayerIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public Player getCurrent() {
        if (players == null) {
            throw new NullPointerException("The players must be provided");
        }
        return players.get(currentPosition);
    }

    @Override
    public Player getNext() {
        currentPosition = currentPosition + (reverse ? -1 : 1);
        if (currentPosition == -1) {
            currentPosition = size() - 1;
        }
        currentPosition = currentPosition % size();
        return players.get(currentPosition);
    }

    @Override
    public int size() {
        return players.size();
    }

    @Override
    public void reverse() {
        reverse = !reverse;
    }

    @Override
    public void reset() {
        currentPosition = 0;
        reverse = false;
        for (Player player : players) {
            player.reset();
        }
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

}
