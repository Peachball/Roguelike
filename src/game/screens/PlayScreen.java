package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.items.ItemSpawner;
import game.players.Coord;
import game.players.Human;
import game.players.Player;
import game.players.monsters.FOV;
import game.world.World;
import game.world.WorldGenerator;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen {

    private World world;
    private Human human;

    public PlayScreen() {
        world = WorldGenerator.generateBlankWorld(Defaults.CHUNK_SIZE * 3, Defaults.CHUNK_SIZE * 3);
        ItemSpawner.generateItems(world, Defaults.RARITY_CONSTANT);
        human = new Human(new Coord(Defaults.CHUNK_SIZE + 1, Defaults.CHUNK_SIZE + 1));

        //Whatever happens to human in this class will also affect what happens to the player
        //in the World object right?
        world.addPlayer(human);
        world.updateWorld();
        human.name = "PEACHBALL THE GOD OF ALL MEN";
    }

    public PlayScreen(String name) {
        this();
        human.name = name;
    }
    
    public PlayScreen(World world, Human human, String name){
        this.world = world;
        this.human = human;
        human.name = name;
    }

    @Override
    public void display(AsciiPanel output) {
        boolean checkerboard = true;
        //Checkerboard, to count tiles...

        for (int y = 0; y < output.getHeightInCharacters(); y++) {
            for (int x = 0; x < output.getWidthInCharacters(); x++) {
                if (checkerboard) {
                    checkerboard = false;
                    output.write(' ', x, y, Color.BLACK, Color.BLACK);
                } else {
                    checkerboard = true;
                    output.write(' ', x, y, Color.WHITE, Color.WHITE);
                }
            }
        }
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

        //Display all the creatures:halp plox
        for (int counter = 0; counter < world.players.size(); counter++) {
            Player buffer = world.players.get(counter);
            int buffery = human.location.y - buffer.location.y + (Defaults.GAMESCREEN_SIZEY / 2);
            int bufferx = human.location.x - buffer.location.x + (Defaults.GAMESCREEN_SIZEX / 2);
            Color foreground = world.get(bufferx, buffery).currentBackground;
            Color background = world.get(bufferx, buffery).currentForeground;
            if (bufferx < 0 || bufferx > Defaults.GAMESCREEN_SIZEX) {
                continue;
            }
            if (buffery < 0 || buffery > Defaults.GAMESCREEN_SIZEY) {
                continue;
            }
            output.write(world.get(bufferx, buffery).representer, bufferx, buffery, foreground, background);
        }
        //Display the player
        output.write('@', (Defaults.GAMESCREEN_SIZEX / 2),
                (Defaults.GAMESCREEN_SIZEY / 2), Color.BLACK, world.get(human.location).background);
        //Stat screen
        output.write(human.name, Defaults.GAMESCREEN_SIZEX, 0);
        output.write("HP:", Defaults.GAMESCREEN_SIZEX, 1);
        output.write(Integer.toString(human.stats.maxHp), Defaults.GAMESCREEN_SIZEX + 4, 1);
        output.write("OTHER STAT?", Defaults.GAMESCREEN_SIZEX, 2);
        //Event log
        //Items
    }

    @Override
    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen();
            case Defaults.up:
                human.moveUp(); //The x y coord system starts from the top
                break;
            case Defaults.down:
                human.moveDown(); //left, and goes to the bottom right
                break;
            case Defaults.right:
                human.moveRight();
                break;
            case Defaults.left:
                human.moveLeft();
                break;
            case Defaults.A:
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
