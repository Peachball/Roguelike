package game.screens;

import asciiPanel.AsciiPanel;
import game.players.Human;
import game.world.World;
import java.awt.event.KeyEvent;

public class HelpScreen implements Screen {

    private World currentWorld;
    private Human currentPlayer;

    public HelpScreen(World world, Human player) {
        currentWorld = world;
        currentPlayer = player;
    }
    public HelpScreen(){
        currentWorld = null
                ;currentPlayer = null;
    }

    public void display(AsciiPanel output) {
        output.writeCenter("WELCOME TO THE HELP MENU", 1);
        output.writeCenter("s key to go back to start menu", 2);
        output.writeCenter("enter key to start/resume game", 3);
        output.writeCenter("WASD keys to move around", 4);
        output.writeCenter("? key to check inventory", 5);
    }

    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                return new PlayScreen(currentWorld, currentPlayer);
            case KeyEvent.VK_S:
                return new StartScreen();
        }
        return this;
    }

}
