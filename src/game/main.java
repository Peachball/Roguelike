package game;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.screens.Frame;
import game.screens.Screen;
import game.screens.StartScreen;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import multiplayer.RSA;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader in  = new BufferedReader(new FileReader("Settings.txt"));
        switch(Integer.parseInt(in.readLine())){
            case 0:
                RSA test = new RSA(20);
            case 1:
                break;
        }
        AsciiPanel currentPanel = new AsciiPanel(Defaults.SCREENX_SIZE, Defaults.SCREENY_SIZE);
        Screen currentScreen;
        Frame window = new Frame(currentPanel);

        window.setTitle("A Roguelike");
        currentScreen = new StartScreen();
        currentScreen.display(currentPanel);
        window.setVisible(true);

    }
}
