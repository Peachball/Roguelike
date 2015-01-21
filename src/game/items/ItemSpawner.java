package game.items;

import static game.items.ItemList.STICK;
import static game.items.ItemList.getItem;
import game.players.Coord;
import game.world.Tile;
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
                for (int counter = 0;counter<buffer.size();counter++) {
                    //Test case:
                    if (Math.random()>0.99) {
                        Tile asdf = world.get(x,y);
                        asdf.addItem(getItem(STICK));
                        world.set(new Coord(x,y), asdf);
                        break;
                    }
                }
            }
        }
    }
}
