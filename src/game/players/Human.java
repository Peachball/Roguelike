package game.players;

public class Human extends Player {
    public Coord location;
    public Human(Coord location) {
        super(new PlayerStat(10, 0, 0, 0, 0), '@');
        this.location = location;
    }
}
