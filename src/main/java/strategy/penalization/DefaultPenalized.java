package strategy.penalization;

import command.DrawCommand;
import player.Player;
import strategy.draw.DrawOnceStrategy;
import template_variation.Game;

public class DefaultPenalized implements PenalizedStrategy {
    private final Game game;

    public DefaultPenalized(Game game) {

        this.game = game;
    }
    @Override
    public void penalize(Player player) {
        DrawCommand drawCommand = new DrawCommand(
                player,
                new DrawOnceStrategy(game)
        );
        drawCommand.execute();
    }
}
