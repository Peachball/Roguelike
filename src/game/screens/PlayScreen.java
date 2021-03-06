package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.items.Item;
import game.items.ItemSpawner;
import game.players.Coord;
import game.players.Human;
import game.players.Player;
import game.players.monsters.FOV;
import game.players.monsters.MonsterSpawner;
import game.world.Message;
import game.world.World;
import game.world.WorldGenerator;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

public class PlayScreen implements Screen {

    private World world;
    private Human human;
    private ArrayList<Message> log;
    private HUD hud;

    public PlayScreen() {
        world = WorldGenerator.generateBlankWorld(Defaults.CHUNK_SIZE * 3, Defaults.CHUNK_SIZE * 3);
        ItemSpawner.generateItems(world, Defaults.RARITY_CONSTANT);
        human = new Human(new Coord(Defaults.CHUNK_SIZE + 1, Defaults.CHUNK_SIZE + 1), world);
        world.player1 = human;
        MonsterSpawner.spawnMonsters(world, 0);

        //Whatever happens to human in this class will also affect what happens to the player
        //in the World object right?
        WorldGenerator.generateChunks(world);
        human.name = "RIGHTEOUS LORD BRENNAN";
        //human2.name = "BRENNAN'S EVIL COUSIN"
        log = world.log;
        world.updateWorld();

    }

    public PlayScreen(String name) {
        this();
        human.name = name;
    }

    public PlayScreen(World world, Human human) {
        this();
        if (world != null && human != null) {
            this.world = world;
            this.human = human;
        }
    }

    @Override
    public void display(AsciiPanel output) {
//        boolean checkerboard = true;
//        //Checkerboard, to count tiles...
        hud = new HUD(output, world, human);

//        for (int y = 0; y < output.getHeightInCharacters(); y++) {
//            for (int x = 0; x < output.getWidthInCharacters(); x++) {
//                if (checkerboard) {
//                    checkerboard = false;
//                    output.write(' ', x, y, Color.BLACK, Color.BLACK);
//                } else {
//                    checkerboard = true;
//                    output.write(' ', x, y, Color.WHITE, Color.WHITE);
//                }
//            }
//        }
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

        //Display the creatures
        for (Player buffer : world.players) {

            int buffery = buffer.location.y - human.location.y + (Defaults.GAMESCREEN_SIZEY / 2);
            int bufferx = buffer.location.x - human.location.x + (Defaults.GAMESCREEN_SIZEX / 2);
            Color background = buffer.background;
            Color foreground = buffer.foreground;
            if (background == null) {
                background = world.get(buffer.location).currentBackground;
            }

            if (bufferx < 0 || bufferx >= Defaults.GAMESCREEN_SIZEX) {
                continue;
            }
            if (buffery < 0 || buffery >= Defaults.GAMESCREEN_SIZEY) {
                continue;
            }
            if (!playervision.isSeen(buffer.location)) {
                continue;
            }
            output.write(buffer.representer, bufferx, buffery, foreground, background);
        }

        //Display the player
        output.write('@', (Defaults.GAMESCREEN_SIZEX / 2),
                (Defaults.GAMESCREEN_SIZEY / 2), Color.BLACK, world.get(human.location).background);

        //Stat screen
        //Should this be hidden away in another method?
        hud.displayPlayerStats(Defaults.GAMESCREEN_SIZEX, 0);

        //Event log
        if (!log.isEmpty()) {
            for (int counter = 0; counter < Defaults.LOG_SIZE; counter++) {
                output.write(log.get(log.size() - counter).message, 0, output.getHeightInCharacters() - counter, log.get(log.size() - counter).foreground,
                        log.get(log.size() - counter).background);
            }
            while (log.size() > Defaults.LOG_SIZE * 2) {
                log.remove(0);
            }
        }

        //Item on ground
        if (!world.get(human.location).items.isEmpty()) {
            Item buffer = world.get(human.location).items.get(0);
            hud.displayItemStats(Defaults.GAMESCREEN_SIZEX, 8, buffer);

            Item buffer2 = null;
            switch (buffer.type) {
                case Defaults.AxeID:
                    buffer2 = human.items[Defaults.rHand];
                    break;
                case Defaults.LanceID:
                    buffer2 = human.items[Defaults.rHand];
                    break;
                case Defaults.SwordID:
                    buffer2 = human.items[Defaults.rHand];
                    break;
                case Defaults.ArmorID:
                    buffer2 = human.items[Defaults.shirt];
                    break;
                case Defaults.BootsID:
                    buffer2 = human.items[Defaults.boots];
                    break;
                case Defaults.GloveID:
                    buffer2 = human.items[Defaults.glove];
                    break;
            }
            if (buffer2 != null) {
                hud.displayItemStats(Defaults.GAMESCREEN_SIZEX, 13, buffer2);
                output.write("YOUR ITEM: " + buffer2.name, Defaults.GAMESCREEN_SIZEX, 13);
            }
        }
    }

    @Override
    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_H:
                return new HelpScreen(world, human);
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
            case Defaults.Start:
                return new InventoryScreen(world, human);
            case Defaults.B:
                human.pickup(world);
                human.equip();
                break;
            default:
                break;

        }
        WorldGenerator.generateChunks(world);
        world.updateWorld();
        if (human.stats.hp <= 0) {
            return new DeathScreen();
        }
        return this;
    }

}
