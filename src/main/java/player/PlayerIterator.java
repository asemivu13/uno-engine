package player;

import java.util.List;

public interface PlayerIterator {
    Player getCurrent();
    Player getNext();
    void reverse();
    int size();
    void reset();
    int getCurrentPosition();
}
