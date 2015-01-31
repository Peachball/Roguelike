package game.items;

public class ItemStat {
//I just realized that this only works for weapons/armor/things you wear and not
//consumables...

    public int damage;
    public int damageDefense;
    public int magicDefense;
    public int weight;
    public int accuracy;
    public int range;
    public int rarity;
    public int critChance;

    public ItemStat(int accuracy, int damage, int weight, int defense, int magDefense, int rarity) {
        this.accuracy = accuracy;
        this.damage = damage;
        this.weight = weight;
        damageDefense = defense;
        magicDefense = magDefense;
        this.rarity = rarity;
        range = 1;
        critChance = 0;
    }

    public ItemStat(int accuracy, int damage, int weight, int defense, int magDefense, int rarity, int range, int crit) {
        this.accuracy = accuracy;
        this.damage = damage;
        this.weight = weight;
        damageDefense = defense;
        magicDefense = magDefense;
        this.rarity = rarity;
        this.range = range;
        critChance = crit;
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
                this.magicDefense + stat.magicDefense, this.rarity + stat.rarity, lowRange,
                this.critChance + stat.critChance);
    }

    public ItemStat multiply(ItemStat stat, boolean low) {
        int lowRange = this.range;
        if (lowRange > stat.range && low) {
            lowRange = stat.range;
        }
        return new ItemStat(this.accuracy * stat.accuracy, this.damage * stat.damage,
                this.weight * stat.weight, this.damageDefense * stat.damageDefense,
                this.magicDefense * stat.magicDefense, this.rarity * stat.rarity, lowRange,
                this.critChance * stat.critChance);
    }

    public ItemStat add(ItemStat stat) {
        return add(stat, true);
    }

    public ItemStat multiply(ItemStat stat) {
        return multiply(stat, true);
    }
}
