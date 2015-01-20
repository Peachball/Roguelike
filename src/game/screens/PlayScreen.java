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

    public PlayScreen() {

        world = WorldGenerator.generateBlankWorld(Defaults.CHUNK_SIZE * 3, Defaults.CHUNK_SIZE * 3);
        ItemSpawner.generateItems(world, Defaults.RARITY_CONSTANT);
        world.player1 = new Human(new Coord(Defaults.CHUNK_SIZE + 1, Defaults.CHUNK_SIZE + 1));
        world.updateWorld();
    }

    public void display(AsciiPanel output) {
        

        //Display the map with all of it's items + Players fov
        FOV playervision = new FOV(world.player1.location, 20);
        playervision.generateFOV(true);
        for (int x = 0; x < Defaults.GAMESCREEN_SIZEX; x++) {
            for (int y = 0; y < Defaults.GAMESCREEN_SIZEY; y++) {
                //Print out map of where the player is on screen
                int bufferx = world.player1.location.x + x - (Defaults.GAMESCREEN_SIZEX / 2);
                int buffery = world.player1.location.y + y - (Defaults.GAMESCREEN_SIZEY / 2);
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
                (Defaults.GAMESCREEN_SIZEY / 2), Color.BLACK, world.get(world.player1.location).background);
        //Health Screen
        //Event log
        //Items
    }

    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen();
            case KeyEvent.VK_UP:
                world.player1.location.y--; //The x y coord system starts from the top
                break;
            case KeyEvent.VK_DOWN:
                world.player1.location.y++; //left, and goes to the bottom right
                break;
            case KeyEvent.VK_RIGHT:
                world.player1.location.x++;
                break;
            case KeyEvent.VK_LEFT:
                world.player1.location.x--;
                break;
        }
        if (world.player1.location.x < Defaults.CHUNK_SIZE) {
            world.player1.location.x += Defaults.CHUNK_SIZE;
            WorldGenerator.extendLeft(Defaults.CHUNK_SIZE, world);
        }
        if (world.getXSize() - world.player1.location.x < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendRight(Defaults.CHUNK_SIZE, world);
        }
        if (world.player1.location.y < Defaults.CHUNK_SIZE) {
            world.player1.location.y += Defaults.CHUNK_SIZE;
            WorldGenerator.extendUp(Defaults.CHUNK_SIZE, world);
        }
        if (world.getYSize() - world.player1.location.y < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendDown(Defaults.CHUNK_SIZE, world);
        }
        return this;
    }

}
