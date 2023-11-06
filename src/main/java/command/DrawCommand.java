package command;

import player.Player;
import strategy.draw.DrawStrategy;

public class DrawCommand implements Command {
    private final DrawStrategy drawStrategy;
    private final Player player;

    public DrawCommand(Player player, DrawStrategy drawStrategy) {
        this.player = player;
        this.drawStrategy = drawStrategy;
    }

    @Override
    public boolean execute() {
        drawStrategy.draw(player);
        player.setDraw(true);
        return true;
    }
}
