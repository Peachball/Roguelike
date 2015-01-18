package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.players.Coord;
import game.players.Human;
import game.world.Tile;
import game.world.World;
import game.world.WorldGenerator;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class PlayScreen implements Screen {

    private World world;
    private Human player;

    public PlayScreen() {

        world = WorldGenerator.generateBlankWorld(Defaults.CHUNK_SIZE * 3, Defaults.CHUNK_SIZE * 3);
        player = new Human(new Coord(Defaults.CHUNK_SIZE + 1, Defaults.CHUNK_SIZE + 1));
    }

    public void display(AsciiPanel output) {

        //Display the map with all of it's items
        for (int x = 0; x < Defaults.GAMESCREEN_SIZEX; x++) {
            for (int y = 0; y < Defaults.GAMESCREEN_SIZEY; y++) {
                //Print out map of where the player is on screen
                int bufferx = player.location.x + x - (Defaults.GAMESCREEN_SIZEX / 2);
                int buffery = player.location.y + y - (Defaults.GAMESCREEN_SIZEY / 2);
                output.write(world.get(bufferx, buffery).representer, x, y,
                        world.get(bufferx, buffery).foreground,
                        world.get(bufferx, buffery).background);
            }
        }

        //Display all the creatures
        //Display the player
        output.write('@', (Defaults.GAMESCREEN_SIZEX / 2),
                (Defaults.GAMESCREEN_SIZEY / 2), Color.WHITE, Color.BLACK);
        //Health Screen
        //Event log
        //Items
    }

    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen();
            case KeyEvent.VK_UP:
                player.location.y--; //The x y coord system starts from the top
                break;
            case KeyEvent.VK_DOWN:
                player.location.y++; //left, and goes to the bottom right
                break;
            case KeyEvent.VK_RIGHT:
                player.location.x++;
                break;
            case KeyEvent.VK_LEFT:
                player.location.x--;
                break;
        }
        if (player.location.x < Defaults.CHUNK_SIZE) {
            player.location.x += Defaults.CHUNK_SIZE;
            WorldGenerator.extendLeft(Defaults.CHUNK_SIZE, world);
        }
        if (world.getXSize() - player.location.x < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendRight(Defaults.CHUNK_SIZE, world);
        }
        if (player.location.y < Defaults.CHUNK_SIZE) {
            player.location.y += Defaults.CHUNK_SIZE;
            WorldGenerator.extendUp(Defaults.CHUNK_SIZE, world);
        }
        if (world.getYSize() - player.location.y < Defaults.CHUNK_SIZE) {
            WorldGenerator.extendDown(Defaults.CHUNK_SIZE, world);
        }
        return this;
    }

}
