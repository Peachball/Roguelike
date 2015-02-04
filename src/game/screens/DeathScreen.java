
package game.screens;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

public class DeathScreen implements Screen {

    @Override
    public void display(AsciiPanel output) {
        output.write("LOL YOU SUCK SO MUCH YOU SCRUB\n"
                + "AHAHAHAAHAHAHAHAHAHAHAHAAHAHAHAHAHAHAH\n"
                + "OMG YOU DIED SO QUICKLY TOOOO\n"
                + "WHY ARE YOU EVEN PLAYING THIS GAMEAHAHAHAHAHAAHHAHA");    }

    @Override
    public Screen response(KeyEvent key) {
return this;
    }
    
}
