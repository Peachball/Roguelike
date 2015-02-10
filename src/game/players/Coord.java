package game.players;

public class Coord implements Comparable<Coord> {

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord newCoord(Coord x, int direction) {
        switch (direction) {
            case 1:
                return new Coord(x.x, x.y - 1);
            case 2:
                return  new Coord(x.x + 1, x.y);
            case 3:
                return  new Coord(x.x, x.y + 1);
            case 4:
                return  new Coord(x.x - 1, x.y - 1);
        }
        return null;
    }

    public static int manhattanDistance(Coord x, Coord y) {
        return Math.abs(x.x - y.x) + Math.abs(x.y - y.y);
    }

    public static int realDistance(Coord x, Coord y) {
        return (int) Math.round(Math.sqrt(Math.pow(x.x - y.x, 2) + Math.pow(x.y - y.y, 2))) - 1;
    }
    
    public boolean equals(Coord coord){
        return coord.x == x && coord.y == y;
    }

    @Override
    public int compareTo(Coord t) {
        if (x != t.x) {
            return x - t.x;
        } else {
            return y - t.y;
        }
    }
}
