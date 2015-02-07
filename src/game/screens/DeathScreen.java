package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import java.awt.event.KeyEvent;

public class DeathScreen implements Screen {

    @Override
    public void display(AsciiPanel output) {
        output.writeCenter("YOU TOTALLY JUST DIED...", 0);
        output.writeCenter("*sigh...............", 1);
        output.writeCenter("Anyways, Press H to go to the help screen", 2);
        output.writeCenter("and press A to go back to the start screen", 3);
    }

    @Override
    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen();
            case KeyEvent.VK_A:
                return new StartScreen();
            case Defaults.A:
                return new StartScreen();
        }
        return this;
    }

}
