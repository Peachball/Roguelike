/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.world;

import game.players.Coord;
import game.players.Human;
import static game.world.TileList.*;

/**
 *
 * @author Chen
 */
public class WorldGenerator {

    public static int worldType;

    public static void setSettings(int specifications) {
        worldType = specifications;
    }

    public static World generateBlankWorld(int sizex, int sizey) {
        Tile[][] buffer = new Tile[sizex][sizey];
        for (int x = 0; x < sizex; x++) {
            for (int y = 0; y < sizey; y++) {
                if (Math.random() > .5) {
                    buffer[x][y] = MOUNTAIN.id;
                } else {
                    buffer[x][y] = GRASSLAND.id;
                }
            }
        }
        return new World(buffer);
    }

    //Test for now...will add better chunk generator
    private static Tile[][] generateBlankChunk(int sizex, int sizey) {
        Tile[][] buffer = new Tile[sizex][sizey];
        for (int x = 0; x < sizex; x++) {
            for (int y = 0; y < sizey; y++) {
                if (Math.random() > .5) {
                    buffer[x][y] = MOUNTAIN.id;
                } else {
                    buffer[x][y] = GRASSLAND.id;
                }
            }
        }
        return buffer;
    }

    public static World extendRight(int sizex, World world) {
        Tile[][] buffer = generateBlankChunk(sizex, world.getYSize());
        world.appendXEnd(buffer);
        World bufferWorld = world;
        return bufferWorld;
    }

    public static World extendLeft(int sizex, World world) {
        Tile[][] buffer = generateBlankChunk(sizex, world.getYSize());
        world.appendXStart(buffer);
        World bufferWorld = world;
        return bufferWorld;
    }

    public static World extendUp(int sizey, World world) {
        Tile[][] buffer = generateBlankChunk(world.getXSize(), sizey);
        world.appendYStart(buffer);
        World bufferWorld = world;
        return bufferWorld;
    }

    public static World extendDown(int sizey, World world) {
        Tile[][] buffer = generateBlankChunk(world.getXSize(), sizey);
        world.appendYEnd(buffer);
        World bufferWorld = world;
        return bufferWorld;
    }

    public static Tile[][] generateChunk() {
        //Work on this later
        return new Tile[1][1];
    }
}
