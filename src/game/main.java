package game;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.screens.Frame;
import game.screens.Screen;
import game.screens.StartScreen;

public class main {

    public static void main(String[] args) {

        AsciiPanel currentPanel = new AsciiPanel(Defaults.SCREENX_SIZE, Defaults.SCREENY_SIZE);
        Screen currentScreen;
        Frame window = new Frame(currentPanel);

        window.setTitle("A Roguelike");
        currentScreen = new StartScreen();
        currentScreen.display(currentPanel);
        window.setVisible(true);

    }
}
