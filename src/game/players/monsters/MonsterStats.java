package game.players.monsters;

import game.players.PlayerStat;

public enum MonsterStats {

    KOBOLD;

    //PlayerStat: int hp, int skill, int speed, int strength, int magicResist, int damageResist, int luck, int level
    public static PlayerStat getMonsterStat(MonsterStats monster) {
        switch (monster) {
            case KOBOLD:
                return new PlayerStat(10, 5, 5, 5, 2, 2, 0, 5);
        }
        return null;
    }
}
