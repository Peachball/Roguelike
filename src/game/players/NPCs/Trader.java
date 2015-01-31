package game.players.NPCs;

import game.players.PlayerStat;
import game.world.World;
import java.awt.Color;

public class Trader extends NPC {

    public Trader(PlayerStat startStat, char a, Color foreground, World world) {
        super(startStat, a, foreground, world);
    }

    public Trader(World world) {
        this(new PlayerStat(10, 0, 0, 0, 0, 0), 'T', Color.GREEN, world);
    }

    
    //Really don't wanna do this...
    @Override
    public void action() {
        
    }

}
