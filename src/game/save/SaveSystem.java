package game.save;

import game.world.World;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveSystem {

    public PrintWriter out;
    public BufferedReader in;

    public SaveSystem(String filename) {
        try {
            File file = new File(filename);
            file.createNewFile();
            out = new PrintWriter(new FileWriter(filename));
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveSystem.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(SaveSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //I'm not sure how you planned to write this ben...
    //HOW DO YOU SAVE PARTS OF WORLD INTO A FILE???
}
