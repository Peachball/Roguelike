
package game.players.NPCs;

import game.players.Player;
import game.players.PlayerStat;
import game.world.World;
import java.awt.Color;

public abstract class NPC extends Player{

    public NPC(PlayerStat startStat, char a, Color foreground, World world) {
        super(startStat, a, foreground, world);
    }
    public abstract void action();
}
