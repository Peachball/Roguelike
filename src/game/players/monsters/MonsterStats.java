package game.players.monsters;

import game.players.PlayerStat;

public enum MonsterStats {

    KOBOLD;

    //PlayerStat: int hp, int skill, int speed, int strength, int magicResist, int damageResist, int luck, int level
    public static PlayerStat getMonsterStat(MonsterStats monster) {
        switch (monster) {
            case KOBOLD:
                return new PlayerStat(10, 3, 3, 3, 3, 3, 3, 3);
        }
        return null;
    }

    public static int getMonsterRarity(MonsterStats monster) {
        switch (monster) {
            case KOBOLD:
                return 0;
        }
        return 0;
    }
}
