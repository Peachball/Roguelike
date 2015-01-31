package game.items.consumables;

import game.players.Player;

public class HealthPotion extends Consumable {

    public HealthPotion(int uses) {
        super(uses);
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
