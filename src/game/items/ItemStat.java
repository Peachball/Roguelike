package game.items;

public class ItemStat {

    public int damage;
    public int damageDefense;
    public int magicDefense;
    public int weight;
    public int accuracy;
    public int range;
    public int rarity;

    public ItemStat(int accuracy, int damage, int weight, int defense, int magDefense, int rarity) {
        this.accuracy = accuracy;
        this.damage = damage;
        this.weight = weight;
        damageDefense = defense;
        magicDefense = magDefense;
        this.rarity = rarity;
        range = 1;
    }

    public ItemStat() {
        this(0, 0, 0, 0, 0,0);
    }
}
