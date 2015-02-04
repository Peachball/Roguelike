package game.world;

import game.players.Coord;
import game.players.Human;
import game.players.Player;
import java.util.ArrayList;

public class World {

    //x,y coordinates (top left is origin)
    public ArrayList<ArrayList<Tile>> world;
    public ArrayList<Player> players;
    public Human player1;
    public ArrayList<Message> log;

    public World(Tile[][] world) {
        log = new ArrayList<Message>();
        this.world = new ArrayList<ArrayList<Tile>>();
        players = new ArrayList<Player>();
        for (int x = 0; x < world[0].length; x++) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < world.length; y++) {
                buffer.add(world[x][y]);
            }
            this.world.add(buffer);
        }
    }

    public void updateWorld() {
        for (int x = 0; x < getXSize(); x++) {
            for (int y = 0; y < getYSize(); y++) {
                get(new Coord(x, y)).update();
            }
        }
        for (int counter = 0;counter<players.size();counter++) {
            Player player = players.get(counter);
            player.update();//That's why you add comments... So you can determine your mental state at the time
            if (player.stats.hp <= 0) {
                players.remove(counter);
            }
        }
    }


    public boolean appendXEnd(Tile[][] append) {
        if (append[0].length != getYSize()) {
            return false;
        }
        for (Tile[] append1 : append) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < append[0].length; y++) {
                buffer.add(append1[y]);
            }
            world.add(buffer);
        }
        return true;
    }

    public boolean appendYEnd(Tile[][] append) {
        if (append.length != getXSize()) {
            return false;
        }
        for (int x = 0; x < append.length; x++) {
            for (int y = 0; y < append[0].length; y++) {
                world.get(x).add(append[x][y]);
            }
        }
        return true;
    }

    public boolean appendXStart(Tile[][] append) {
        if (append[0].length != getYSize()) {
            return false;
        }
        for (int x = 0; x < append.length; x++) {
            ArrayList<Tile> buffer = new ArrayList<Tile>();
            for (int y = 0; y < append[0].length; y++) {
                buffer.add(append[x][y]);
            }
            world.add(x, buffer);
        }
        return true;
    }

    public boolean appendYStart(Tile[][] append) {
        if (append.length != getXSize()) {
            return false;
        }
        for (int x = 0; x < append.length; x++) {
            for (int y = 0; y < append[0].length; y++) {
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

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

}
