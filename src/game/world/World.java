package game.world;

import game.players.Coord;
import java.util.ArrayList;

public class World {

    //x,y coordinates (top left is origin)
    public ArrayList<ArrayList<Tile>> world;

    public World(Tile[][] world) {
        this.world = new ArrayList<ArrayList<Tile>>();

        for (int x = 0; x < world[0].length; x++) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < world.length; y++) {
                buffer.add(world[x][y]);
            }
            this.world.add(buffer);
        }
    }

    public boolean appendXEnd(Tile[][] append) {
        if (append.length != world.get(0).size()) {
            return false;
        }
        for (int x = 0; x < append[0].length; x++) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < append.length; y++) {
                buffer.add(append[x][y]);
            }
            world.add(buffer);
        }
        return true;
    }

    public boolean appendYEnd(Tile[][] append) {
        if (append[0].length != world.size()) {
            return false;
        }
        for (int x = 0; x < append[0].length; x++) {
            for (int y = 0; y < append.length; y++) {
                world.get(x).add(append[x][y]);
            }
        }
        return true;
    }

    public boolean appendXStart(Tile[][] append) {
        if (append.length != world.get(0).size()) {
            return false;
        }
        for (int x = 0; x < append[0].length; x--) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < append.length; y++) {
                buffer.add(append[x][y]);
            }
            world.add(x, buffer);
        }
        return true;
    }

    public boolean appendYStart(Tile[][] append) {
        if (append[0].length != world.size()) {
            return false;
        }
        for (int x = 0; x < append[0].length; x++) {
            for (int y = 0; y < append.length; y++) {
                world.get(x).add(y, append[x][y]);
            }
        }
        return true;
    }

    public int getXSize() {
        return world.size();
    }

    public int getYSize() {
        return world.get(0).size();
    }

    public Tile get(int x, int y) {
        return world.get(x).get(y);
    }

    public Tile get(Coord x) {
        return get(x.x, x.y);
    }

    public boolean set(Coord location, Tile object) {
        ArrayList<Tile> buffer = world.get(location.x);
        buffer.set(location.y, object);
        world.set(location.x, buffer);
        return true;
    }
}
