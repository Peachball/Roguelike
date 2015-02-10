package game.players;

import game.defaults.Mechanics;
import game.items.Item;
import game.items.ItemList;
import static game.items.ItemList.STICK;
import game.world.World;
import java.awt.Color;
import java.util.Collections;

public class Player implements Comparable<Player> {

    public String name;
    public PlayerStat stats;
    public char representer;
    public Color foreground;
    public Color background;
    public Coord location;
    public World world;
    public Item currentWeapon;
    public int experience;
    protected Player lastAttacker;

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
        return world.isEmpty(new Coord(location.x + 1, location.y));
    }

    public boolean canLeft() {
        return world.isEmpty(new Coord(location.x - 1, location.y));
    }

    public boolean canUp() {
        return world.isEmpty(new Coord(location.x, location.y - 1));
    }

    public boolean canDown() {
        return world.isEmpty(new Coord(location.x, location.y + 1));
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
        int position = Collections.binarySearch(world.players, new Player(location));
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
            if (Math.random() * 100 < Mechanics.critChance(this, weapon, player)) {
                player.stats.hp -= 3 * Mechanics.damage(this, weapon, player, triangle) * Mechanics.attackTimes(this, currentWeapon, player, weapon);
            } else {
                player.stats.hp = player.stats.hp - Mechanics.damage(this, weapon, player, triangle) * Mechanics.attackTimes(this, currentWeapon, player, weapon);
            }
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

    public boolean update() {
        return stats.hp <= 0;
    }

    @Override
    public int compareTo(Player t) {
        if (location.x != t.location.x) {
            return location.x - t.location.x;
        }
        return location.y - t.location.y;
    }

}
