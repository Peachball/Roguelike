package game.items;

import game.defaults.Defaults;
import game.world.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ItemSpawner {

    
    //IDK what I'm doing here...
    //need ideas...
    public static void generateItems(World world, int rarityConstant) {
        for (int x = 0; x < world.getXSize(); x++) {
            for (int y = 0; y < world.getYSize(); y++) {
                ArrayList<ItemList> buffer = new ArrayList<ItemList>(Arrays.asList(ItemList.values()));
                Collections.shuffle(buffer);
                for (ItemList item : buffer) {
                    if (Math.random() * 1000 < Defaults.MAX_RARITY - (Defaults.RARITY_CONSTANT * item.item.stats.rarity)) {
                        world.get(x, y).addItem(item.item);
                        break;
                    }
                }
            }
        }
    }
}
