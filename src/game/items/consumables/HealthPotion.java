package game.items.consumables;

import game.players.Player;
import java.awt.Color;

public class HealthPotion extends Consumable {

    public HealthPotion(int uses) {
        super(uses,new Color(255,54,53),'?',"Health Potion");
    }
    
    public HealthPotion(){
        this(3);
    }

    @Override
    public void effect(Player player) {
        player.stats.hp += 10;
        uses--;
    }

}
