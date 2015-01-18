package game.players;

public class Human extends Player {

    public Human(Coord location) {
        super(new PlayerStat(10, 0, 0, 0, 0), '@');
        this.location = location;
    }
}
