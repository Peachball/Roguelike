package game.screens;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

public class HelpScreen implements Screen{

    public void display(AsciiPanel output) {
        output.writeCenter("WELCOME TO THE HELP MENU",1);
        output.writeCenter("s key to go back to start menu", 2);
        output.writeCenter("enter key to start/resume game",3);
        output.writeCenter("WASD keys to move around", 4);
        output.writeCenter("? key to check inventory", 5);
    }

    public Screen response(KeyEvent key) {
        switch(key.getKeyCode()){
            case KeyEvent.VK_ENTER:
                return new PlayScreen();
            case KeyEvent.VK_S:
                return new StartScreen();
        }
        return this;
    }
    
}
