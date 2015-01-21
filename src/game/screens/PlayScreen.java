package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.items.ItemSpawner;
import game.players.Coord;
import game.players.Human;
import game.players.monsters.FOV;
import static game.world.TileList.*;
import game.world.World;
import game.world.WorldGenerator;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Collections;

public class PlayScreen implements Screen {

    private World world;
    private Human human;

    public PlayScreen() {

        world = WorldGenerator.generateBlankWorld(Defaults.CHUNK_SIZE * 3, Defaults.CHUNK_SIZE * 3);
        ItemSpawner.generateItems(world, Defaults.RARITY_CONSTANT);
        human = new Human(new Coord(Defaults.CHUNK_SIZE + 1, Defaults.CHUNK_SIZE + 1));
        world.updateWorld();
    }

    public void display(AsciiPanel output) {
        

        //Display the map with all of it's items + Players fov
        FOV playervision = new FOV(human.location, 20);
        playervision.generateFOV(true);
        for (int x = 0; x < Defaults.GAMESCREEN_SIZEX; x++) {
            for (int y = 0; y < Defaults.GAMESCREEN_SIZEY; y++) {
                //Print out map of where the player is on screen
                int bufferx = human.location.x + x - (Defaults.GAMESCREEN_SIZEX / 2);
                int buffery = human.location.y + y - (Defaults.GAMESCREEN_SIZEY / 2);
                Color foreground = world.get(bufferx, buffery).currentForeground;
                Color background = world.get(bufferx, buffery).currentBackground;

                //(You can't see the things outside of your fov...
                //I'll add a list of places you've seen later...
                if (!(Collections.binarySearch(playervision.vision, new Coord(bufferx, buffery)) >= 0)) {
                    foreground = Color.black;
                    background = Color.black;
                }
                output.write(world.get(bufferx, buffery).representer, x, y,
                        foreground, background);
            }
        }

        //Display all the creatures
        //Display the player
        output.write('@', (Defaults.GAMESCREEN_SIZEX / 2),
                (Defaults.GAMESCREEN_SIZEY / 2), Color.BLACK, world.get(human.location).background);
        //Health Screen
        //Event log
        //Items
    }

    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen();
            case KeyEvent.VK_UP:
                human.moveUp(); //The x y coord system starts from the top
                break;
            case KeyEvent.VK_DOWN:
                human.moveDown(); //left, and goes to the bottom right
                break;
            case KeyEvent.VK_RIGHT:
                human.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                human.moveLeft();
                break;
            case KeyEvent.VK_ENTER:
                human.pickup(world);
                break;
        }
        if (human.location.x < Defaults.CHUNK_SIZE) {
            human.location.x += Defaults.CHUNK_SIZE;
            WorldGenerator.extendLeft(Defaults.CHUNK_SIZE, world);
        }
        if (world.getXSize() - human.location.x < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendRight(Defaults.CHUNK_SIZE, world);
        }
        if (human.location.y < Defaults.CHUNK_SIZE) {
            human.location.y += Defaults.CHUNK_SIZE;
            WorldGenerator.extendUp(Defaults.CHUNK_SIZE, world);
        }
        if (world.getYSize() - human.location.y < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendDown(Defaults.CHUNK_SIZE, world);
        }
        return this;
    }

}
