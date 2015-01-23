package game.players;

import game.defaults.Mechanics;
import game.items.Item;
import game.items.ItemList;
import static game.items.ItemList.STICK;
import game.world.World;
import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

public class Player {

    public String name;
    public PlayerStat stats;
    public char representer;
    public Color foreground;
    public Color background;
    public Coord location;
    public World world;
    Item currentWeapon;

    public Player(PlayerStat startStat, char a, Color foreground, Color background, World world) {
        stats = startStat;
        representer = a;
        this.foreground = foreground;
        this.background = background;
        this.world = world;
        currentWeapon = ItemList.getItem(STICK);
    }

    public Player(PlayerStat startStat, char a, Color foreground, World world) {
        this(startStat, a, foreground, null, world);
    }

    public Player(PlayerStat startStat, char a, Color foreground, World world, Coord startLocation) {
        this(startStat, a, foreground, null, world);
        this.location = startLocation;
    }

    public Player(Coord location) {
        this.location = location;
    }

    private boolean isEmpty(Coord location) {
        Collections.sort(world.players, new PlayerSorter());
        if (Collections.binarySearch(world.players, new Player(location), new PlayerSorter()) < 0) {
            return true;
        }
        return false;
    }

    public void moveUp() {
        if (!isEmpty(new Coord(location.x, location.y - 1))) {
            attack(new Coord(location.x, location.y - 1), currentWeapon);
            return;
        }
        location.y--;
    }

    public void moveDown() {
        if (!isEmpty(new Coord(location.x, location.y + 1))) {
            attack(new Coord(location.x, location.y + 1), currentWeapon);
            return;
        }
        location.y++;
    }

    public void moveRight() {
        if (!isEmpty(new Coord(location.x + 1, location.y))) {
            attack(new Coord(location.x + 1, location.y), currentWeapon);
            return;
        }
        location.x++;
    }

    public void moveLeft() {
        if (!isEmpty(new Coord(location.x - 1, location.y))) {
            attack(new Coord(location.x - 1, location.y - 1), currentWeapon);
            return;
        }
        location.x--;
    }

    public void attack(Coord location, Item weapon) {
        Player buffer = world.players.get(Collections.binarySearch(world.players, new Player(location), new PlayerSorter()));
        if (buffer == null) {
            return;
        }
        attack(buffer, weapon, false);
    }

    public void attack(Player player, Item weapon, boolean triangle) {
        if (player == null) {
            return;
        }
        player.stats.hp = player.stats.hp - Mechanics.damage(this, weapon, player, triangle);
    }

    public void attack(Player player2) {
        if (Item.isBetter(currentWeapon, player2.currentWeapon)) {
            attack(player2, currentWeapon, true);
        } else {
            attack(player2, currentWeapon, false);
        }
    }
}

class PlayerSorter implements Comparator<Player> {

    @Override
    public int compare(Player t, Player t1) {
        if (t.location.x != t1.location.x) {
            return t.location.x - t1.location.x;
        }
        return t.location.y - t1.location.y;
    }

}
