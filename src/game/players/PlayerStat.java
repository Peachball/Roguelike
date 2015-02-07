package game.players;

public class PlayerStat {

    public int maxHp;
    public int hp;
    public int skill; //or known as dexterity
    public int speed;
    public int magicResist;
    public int damageResist;
    public int strength;
    public int luck;
    public int level;


    public PlayerStat(int hp, int skill, int speed, int strength, int magicResist, int damageResist) {
        this.maxHp = hp;
        this.skill = skill;
        this.speed = speed;
        this.magicResist = magicResist;
        this.damageResist = damageResist;
        this.hp = maxHp;
        this.strength = strength;
        checkStats();
    }

    public PlayerStat(int hp, int skill, int speed, int strength, int magicResist, int damageResist, int luck, int level) {
        this(hp, skill, speed, strength, magicResist, damageResist);
        this.luck = luck;
        this.level = level;
    }

    public void checkStats() {
        if (hp < 0) {
            hp = 0;
        }
        if (skill < 0) {
            skill = 0;
        }
        if (speed < 0) {
            speed = 0;
        }
        if (magicResist < 0) {
            magicResist = 0;
        }
        if (damageResist < 0) {
            damageResist = 0;
        }
    }
}
