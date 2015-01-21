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

    public ItemStat(int accuracy, int damage, int weight, int defense, int magDefense, int rarity, int range) {
        this.accuracy = accuracy;
        this.damage = damage;
        this.weight = weight;
        damageDefense = defense;
        magicDefense = magDefense;
        this.rarity = rarity;
        this.range = range;
    }

    public ItemStat() {
        this(0, 0, 0, 0, 0, 0);
    }

    public ItemStat add(ItemStat stat, boolean low) {
        int lowRange = this.range;
        if (lowRange > stat.range && low) {
            lowRange = stat.range;
        }
        return new ItemStat(this.accuracy + stat.accuracy, this.damage + stat.damage,
                this.weight + stat.weight, this.damageDefense + stat.damageDefense,
                this.magicDefense + stat.magicDefense, this.rarity + stat.rarity, lowRange);
    }

    public ItemStat add(ItemStat stat) {
        return add(stat, true);
    }
}
