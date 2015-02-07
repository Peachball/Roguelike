package game.players.monsters;

import static game.items.ItemList.SWORD;
import static game.items.ItemList.getItem;
import game.players.Coord;
import game.players.Player;
import static game.players.monsters.MonsterStats.KOBOLD;
import game.world.World;
import java.awt.Color;
import java.util.Random;

public class Kobold extends Player {

    private FOV fov;

    public Kobold(World world, Coord start) {
        super(MonsterStats.getMonsterStat(KOBOLD), 'm', Color.RED, world);
        currentWeapon = getItem(SWORD);
        this.world = world;
        location = new Coord(start.x, start.y);
        fov = new FOV(location, 5);
        fov.generateFOV(true);
    }

    @Override
    public boolean update() {
        move();
        return super.update();
    }

    public void move() {
        fov = new FOV(location, 5);
        fov.generateFOV(true);
        if (fov.isSeen(world.player1.location)) {

            int shortestDistance = Integer.MAX_VALUE;
            int direction = 1;
            //IDEALLY...Ben would be writing an AI for this guy
            //BUT...We live in a dark dark world
            if (shortestDistance > Coord.manhattanDistance(world.player1.up(), location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.up(), location);
                direction = 1;
            }
            if (shortestDistance > Coord.manhattanDistance(world.player1.right(), location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.right(), location);
                direction = 2;
            }
            if (shortestDistance > Coord.manhattanDistance(world.player1.down(), location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.down(), location);
                direction = 3;
            }
            if (shortestDistance > Coord.manhattanDistance(world.player1.left(), location)) {
                direction = 4;
            }
            switch (direction) {
                case 3:
                    moveUp();
                    break;
                case 4:
                    moveRight();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
            }
        } else {
            Random r = new Random();
            int toMove = r.nextInt(4);
            switch (toMove) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
                default:
                    System.out.println("Error. toMove was " + toMove);

            }

        }
    }

    @Override
    public boolean moveUp() {
        if (!super.moveUp()) {
            attack(up(), currentWeapon);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveDown() {
        if (!super.moveDown()) {
            attack(down(), currentWeapon);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveRight() {
        if (!super.moveRight()) {
            attack(right(), currentWeapon);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (!super.moveLeft()) {
            attack(left(), currentWeapon);
            return false;
        }
        return true;
    }
}
