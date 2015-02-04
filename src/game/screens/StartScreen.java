/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.players.Coord;
import game.players.Human;
import game.world.WorldGenerator;
import java.awt.event.KeyEvent;

/**
 *
 * @author Chen
 */
public class StartScreen implements Screen {

    public int selection;
    public String play;
    public String help;
    public int distanceFromTop;
    public int maxSelection;

    public StartScreen() {
        play = "Play Game";
        help = "Help Screen";
        distanceFromTop = Defaults.STARTSCREEN_Y_PARAMATAR;
        selection = 0;
        maxSelection = 1;
    }

    public void display(AsciiPanel output) {
        output.writeCenter("This is an infinitely better game than Berserkers", 1);
        output.writeCenter(play, distanceFromTop);
        output.writeCenter(help, distanceFromTop + 1);
        switch (selection) {
            case 0:
                output.write('>', (output.getWidthInCharacters() - play.length()) / 2 - 2, distanceFromTop + selection);
                break;
            case 1:
                output.write('>', (output.getWidthInCharacters() - play.length()) / 2 - 2, distanceFromTop + selection);
                break;
        }
    }

    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case Defaults.A:
                switch (selection) {
                    case 0:
                        return new PlayScreen();
                    case 1:
                        return new HelpScreen();
                }
                break;
            case Defaults.up:
                selection--;
                if (selection < 0) {
                    selection = maxSelection;
                }
                break;
            case Defaults.down:
                selection++;
                if (selection > maxSelection) {
                    selection = 0;
                }
                break;
        }
        return this;
    }
}
