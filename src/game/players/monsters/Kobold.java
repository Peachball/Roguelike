/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.players.monsters;

import game.players.Coord;
import game.players.Human;
import game.players.Player;
import game.world.World;

/**
 *
 * @author Chen
 */
public class Kobold extends Player implements AI {

    private World world;

    public Kobold(World world, Human human) {
        this.world = world;
    }

    @Override
    public Coord moveNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
