package game.players;

public class Player {

    public String name;
    public PlayerStat stats;
    public Coord location;

    public Player(PlayerStat startStat, char a) {
        stats = startStat;
    }

    public Player() {
        stats = new PlayerStat(0, 0, 0, 0, 0,0);
    }
}
