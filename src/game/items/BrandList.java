package game.items;

public enum BrandList {

    NONE, STEEL, IRON, SILVER, ELECTRIC;

    //The stats are nowhere close to final...
    //Add stuff later I guess...
    public static Brand getBrand(BrandList brand) {
        switch (brand) {
            case STEEL:
                return new Brand("Steel", new ItemStat(0, 0, 0, 0, 0, 0));
            case IRON:
                return new Brand("Iron", new ItemStat(0, 0, 0, 0, 0, 0));
            case SILVER:
                return new Brand("Silver", new ItemStat(0, 0, 0, 0, 0, 0));
            case ELECTRIC:
                return new Brand("Electric", new ItemStat(0, 0, 0, 0, 0, 0));
        }
        return new Brand("", new ItemStat(0, 0, 0, 0, 0, 0));
    }
}
