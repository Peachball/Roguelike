package game.items;

import game.defaults.Defaults;
import static game.items.BrandList.NONE;
import java.awt.Color;

public class Item {

    public String name;
    public ItemStat stats;
    public char symbol;
    public Color foreground;
    public int type;
    public Brand brand;
    public boolean branded;

    
    //Once a brand has been assigned to an item, it is not removable...
    //Should this be changed? I don't think so, as you shouldn't be able to change
    // brands anyways...
    public Item(String name, ItemStat stats, char symbol, int type) {
        this.name = name;
        this.stats = stats;
        this.symbol = symbol;
        foreground = Defaults.FOREGROUND_COLOR;
        this.type = type;
        this.brand = BrandList.getBrand(NONE);
        branded = false;
    }

    public Item(String name, ItemStat stats, char symbol, int type, Color color) {
        this(name, stats, symbol, type);
        foreground = color;

    }
    
    //WARNING: ONCE THIS IS CALLED, THE ITEM WILL FOREVER HAVE THIS BRAND...
    public void setBrand(Brand brand){
        if(!branded){
            this.brand = brand;
            stats.add(brand.stats);
            name = brand.name + " " + name;
        }
        
        branded = true;
    }
    
    public void update(){
        //Possibly add a function to determine the color of a rare item?
        if(stats.rarity>Defaults.LOW_RARITY){
            foreground = Defaults.LOW_RARITY_COLOR;
        }
        if(stats.rarity>Defaults.MEDIUM_RARITY){
            foreground = Defaults.MEDIUM_RARITY_COLOR;
        }
        if(stats.rarity>Defaults.HIGH_RARITY){
            foreground = Defaults.HIGH_RARITY_COLOR;
        }
    }

}
