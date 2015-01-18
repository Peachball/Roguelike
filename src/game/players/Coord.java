package game.players;

public class Coord {

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord newCoord(Coord x, int direction) {
        Coord buffer;
        switch (direction) {
            case 1:
                return buffer = new Coord(x.x, x.y - 1);
            case 2:
                return buffer = new Coord(x.x + 1, x.y);
            case 3:
                return buffer = new Coord(x.x, x.y + 1);
            case 4:
                return buffer = new Coord(x.x - 1, x.y - 1);
        }
        return null;
    }
}
