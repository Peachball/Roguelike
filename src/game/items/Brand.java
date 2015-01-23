package game.items;

/*
Morally feeling kinda bad about this class because
I got the idea from Shreyas...
Brand is the type (e.g., a "Steel" sword...0
 */
public class Brand {
    public boolean beginning;
    public String name;
    public ItemStat stats;// The stats are a buff or minus... (both are possible)
    public Brand(String name, ItemStat stats, boolean nameLocation){
        this.name = name;
        this.stats = stats;
        this.beginning = nameLocation;
    }
    public Brand(String name, ItemStat stats){
        this(name, stats,false);
    }
}
