package player;

import java.util.List;

public interface PeekableIterator extends PlayerIterator {
    List<Player> getPlayers();
    Player peek(int position);
}
