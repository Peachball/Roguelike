/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.players.monsters;

import game.players.Coord;
import game.players.Human;
import game.players.Player;
import game.players.PlayerStat;
import static game.players.monsters.MonsterStats.KOBOLD;
import game.world.World;
import java.awt.Color;

/**
 *
 * @author Chen
 */
public class Kobold extends Player implements AI {

    private World world;
    private FOV fov;

    public Kobold(World world, Human human) {
        super(MonsterStats.getMonsterStat(KOBOLD), 'm', Color.RED,world);
        this.world = world;
        fov.generateFOV(true);
    }

    public void update() {
        fov.generateFOV(true);
    }

    @Override
    public void moveNext() {
        if (fov.isSeen(world.player1.location)) {

            int shortestDistance = Integer.MAX_VALUE;
            //IDEALLY...Ben would be writing an AI for this guy
            //BUT...We live in a dark dark world
            moveUp();
            if (shortestDistance > Coord.manhattanDistance(world.player1.location, location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.location, location);
            } else {
                moveDown();
            }
            moveRight();
            if (shortestDistance > Coord.manhattanDistance(world.player1.location, location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.location, location);
            } else {
                moveLeft();
            }
            moveDown();
            if (shortestDistance > Coord.manhattanDistance(world.player1.location, location)) {
                shortestDistance = Coord.manhattanDistance(world.player1.location, location);
            } else {
                moveUp();
            }
            moveLeft();
            if (shortestDistance > Coord.manhattanDistance(world.player1.location, location)) {
            } else {
                moveRight();
            }
        }
    }

}
