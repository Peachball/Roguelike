package game.players;

public class Buff extends PlayerStat {

    private int constant;

    public Buff(PlayerStat stat, int multiply) {
        super(stat.hp, stat.skill, stat.speed, stat.strength, stat.magicResist, stat.damageResist, stat.luck, stat.level);

        constant = multiply;
    }

    public void applyBuff(PlayerStat stat) {
        stat.damageResist += damageResist * constant;
        stat.maxHp += maxHp * constant;
        stat.luck += luck * constant;
        stat.level += level * constant;
        stat.magicResist += magicResist * constant;
        stat.skill += skill * constant;
        stat.speed += speed * constant;
        stat.strength += strength * constant;
    }
}
