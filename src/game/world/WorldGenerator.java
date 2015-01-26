package game.world;

import game.defaults.Defaults;
import game.players.Coord;
import game.players.Player;
import static game.world.TileList.*;

public class WorldGenerator {

    public static int worldType;

    public static void setSettings(int specifications) {
        worldType = specifications;
    }

    static {
        worldType = 0;
    }

    public static World generateBlankWorld(int sizex, int sizey) {
        Tile[][] buffer = new Tile[sizex][sizey];
        for (int x = 0; x < sizex; x++) {
            for (int y = 0; y < sizey; y++) {
                if (Math.random() > .5) {
                    buffer[x][y] = generateTile(GRASSLAND);
                } else {
                    buffer[x][y] = generateTile(MOUNTAIN);
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
                    buffer[x][y] = generateTile(MOUNTAIN);
                } else {
                    buffer[x][y] = generateTile(GRASSLAND);
                }
            }
        }
        return buffer;
    }

    //Add actual chunk generator in these methods later...
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

    public static World generateChunks(World world) {
        for (int counter = 0; counter < world.players.size(); counter++) {
            if (world.players.get(counter).location.x < Defaults.CHUNK_SIZE) {
                WorldGenerator.extendLeft(Defaults.CHUNK_SIZE, world);
                move(world, new Coord(Defaults.CHUNK_SIZE, 0));
            } else if (world.players.get(counter).location.x > world.getXSize() - Defaults.CHUNK_SIZE) {
                WorldGenerator.extendRight(Defaults.CHUNK_SIZE, world);
            } else if (world.players.get(counter).location.y < Defaults.CHUNK_SIZE) {
                WorldGenerator.extendUp(Defaults.CHUNK_SIZE, world);
                move(world, new Coord(0, Defaults.CHUNK_SIZE));
            } else if (world.players.get(counter).location.y > world.getYSize() - Defaults.CHUNK_SIZE) {
                WorldGenerator.extendDown(Defaults.CHUNK_SIZE, world);
            }
        }
        if (world.player1 != null) {
            if (world.player1.location.x < Defaults.CHUNK_SIZE) {
                WorldGenerator.extendLeft(Defaults.CHUNK_SIZE, world);
                move(world, new Coord(Defaults.CHUNK_SIZE, 0));
            } else if (world.player1.location.x > world.getXSize() - Defaults.CHUNK_SIZE) {
                WorldGenerator.extendRight(Defaults.CHUNK_SIZE, world);
            } else if (world.player1.location.y < Defaults.CHUNK_SIZE) {
                WorldGenerator.extendUp(Defaults.CHUNK_SIZE, world);
                move(world, new Coord(0, Defaults.CHUNK_SIZE));
            } else if (world.player1.location.y > world.getYSize() - Defaults.CHUNK_SIZE) {
                WorldGenerator.extendDown(Defaults.CHUNK_SIZE, world);
            }
        }
        return world;
    }

    private static void move(World world, Coord coord) {
        for (Player player : world.players) {
            player.location = new Coord(player.location.x + coord.x, player.location.y + coord.y);
        }
        world.player1.location = new Coord(world.player1.location.x + coord.x, world.player1.location.y + coord.y);
    }
}
