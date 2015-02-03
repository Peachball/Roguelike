package game.effects;

import game.players.Human;
import game.players.Player;
import game.world.World;

public abstract class Effect {

    public World world;
    public Human human;
    public Player target;

    public Effect(World world, Human human, Player target) {
        this.world = world;
        this.human = human;
        this.target = target;
    }

    public abstract void apply();
}
