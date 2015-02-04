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
    public Item currentWeapon;
    public int experience;

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
        if (location.equals(world.player1.location) && !(this instanceof Human)) {
            return false;//false;
        }
        Collections.sort(world.players, new PlayerSorter());
        return Collections.binarySearch(world.players, new Player(location), new PlayerSorter()) < 0;
    }

    public boolean moveUp() {
        if (!canUp()) {
            return false;
        }
        location.y--;
        return true;
    }

    public boolean moveDown() {
        if (!canDown()) {
            return false;
        }
        location.y++;
        return true;
    }

    public boolean moveRight() {
        if (!canRight()) {
            return false;
        }
        location.x++;
        return true;
    }

    public boolean moveLeft() {
        if (!canLeft()) {
            return false;
        }
        location.x--;
        return true;
    }

    public boolean canRight() {
        return isEmpty(new Coord(location.x + 1, location.y));
    }

    public boolean canLeft() {
        return isEmpty(new Coord(location.x - 1, location.y));
    }

    public boolean canUp() {
        return isEmpty(new Coord(location.x, location.y - 1));
    }

    public boolean canDown() {
        return isEmpty(new Coord(location.x, location.y + 1));
    }

    public Coord up() {
        return new Coord(location.x, location.y - 1);
    }

    public Coord down() {
        return new Coord(location.x, location.y + 1);
    }

    public Coord right() {
        return new Coord(location.x + 1, location.y);
    }

    public Coord left() {
        return new Coord(location.x - 1, location.y);
    }

    public void attack(Coord location, Item weapon) {
        if (weapon == null) {
            return;
        }
        Player buffer;
        int position = Collections.binarySearch(world.players, new Player(location), new PlayerSorter());
        if (position < 0) {
            if (world.player1.location.equals(location)) {
                buffer = world.player1;
            } else {
                return;
            }
        } else {
            buffer = world.players.get(position);
        }
        if (buffer == null) {
            return;
        }
        if (Item.isBetter(currentWeapon, buffer.currentWeapon)) {
            attack(buffer, weapon, true);
        } else {
            attack(buffer, weapon, false);
        }
    }

    public void attack(Player player, Item weapon, boolean triangle) {
        if (player == null || weapon == null) {
            return;
        }
        if (Math.random() * 100 < Mechanics.accuracy(this, currentWeapon, player, weapon, triangle)) {
            player.stats.hp = player.stats.hp - Mechanics.damage(this, weapon, player, triangle) * Mechanics.attackTimes(this, currentWeapon, player, weapon);
        }
    }

    public void attack(Player player2) {
        if (currentWeapon == null || player2 == null) {
            return;
        }
        if (Item.isBetter(currentWeapon, player2.currentWeapon)) {
            attack(player2, currentWeapon, true);
        } else {
            attack(player2, currentWeapon, false);
        }
    }

    public void update() {
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
