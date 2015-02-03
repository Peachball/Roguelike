package game.players.monsters;

import static game.items.ItemList.STICK;
import static game.items.ItemList.getItem;
import game.players.Coord;
import game.players.Player;
import static game.players.monsters.MonsterStats.KOBOLD;
import game.world.World;
import java.awt.Color;

public class Kobold extends Player {

    private World world;
    private FOV fov;

    public Kobold(World world, Coord start) {
        super(MonsterStats.getMonsterStat(KOBOLD), 'm', Color.RED, world);
        currentWeapon = getItem(STICK);
        this.world = world;
        location = start;
        fov = new FOV(location,5);
        fov.generateFOV(true);
    }

    @Override
    public void update(){
        move();
    }
    
    public void move() {
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
                shortestDistance = Coord.manhattanDistance(world.player1.left(), location);
                direction = 4;
            }
            switch (direction) {
                case 1:
                    moveUp();
                    break;
                case 2:
                    moveRight();
                    break;
                case 3:
                    moveDown();
                    break;
                case 4:
                    moveLeft();
                    break;
            }
        }
    }

}
