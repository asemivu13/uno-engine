package command;

import cards.DiscardPile;
import data.Color;

import java.util.Scanner;

public class ChangeColorCommand implements Command {
    private final DiscardPile discardPile;
    public ChangeColorCommand(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }
    @Override
    public boolean execute() {
        Color color = chooseColor();
        discardPile.setColor(color);
        return true;
    }
    private Color chooseColor() {
        System.out.println();
        System.out.println("Choose the color you want the card to be changed to");
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println("[" + i + "] " + Color.values()[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int colorSelection = Integer.parseInt(scanner.nextLine());
        System.out.println();
        return Color.values()[colorSelection];
    }
}
