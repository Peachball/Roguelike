package game.defaults;

import game.items.Item;
import game.items.PhysicalWeapon;
import game.players.Player;
import game.world.Tile;

public class Mechanics {

    private static int ASCalc(Player attacker, Item weapon) {
        if (attacker.stats.strength < weapon.stats.weight) {
            return attacker.stats.speed - (weapon.stats.weight - attacker.stats.strength);
        }
        return attacker.stats.speed;
    }

    private static int hitRate(Player attacker, Item weapon) {
        return weapon.stats.accuracy + (attacker.stats.skill * 2) + (attacker.stats.luck / 2);
    }

    private static int evadeRate(Player defender, Item weapon, Tile currentTile) {
        return (ASCalc(defender, weapon) * 2) + defender.stats.luck + currentTile.terrainBonus;
    }

    public static int accuracy(Player attacker, Item attackWeapon, Player defender, Item defendWeapon, Tile defenderTile, boolean triangle) {
        if (!triangle) {
            return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile);
        }
        return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile) + Defaults.TRIANGLE_BONUS;
    }

    private static int attackPower(Player attacker, Item weapon, boolean triangle) {
        if (!triangle) {
            return attacker.stats.strength + (weapon.stats.damage * Defaults.WEAPON_EFFECTIVNESS);
        }
        return attacker.stats.strength + ((weapon.stats.damage + Defaults.TRIANGLE_BONUS) * Defaults.WEAPON_EFFECTIVNESS);
    }

    private static int pDefensePower(Player defender, Tile defendTile) {
        return defendTile.terrainBonus + defender.stats.damageResist;
    }

    private static int mDefensePower(Player defender, Tile defendTile) {
        return defendTile.terrainBonus + defender.stats.magicResist;
    }

    public static int damage(Player attacker, Item weapon, Player defender, Tile defendTile, boolean triangle) {
        if (weapon instanceof PhysicalWeapon) {
            return attackPower(attacker, weapon, triangle) - pDefensePower(defender, defendTile);
        } else {
            return attackPower(attacker, weapon, triangle) - mDefensePower(defender, defendTile);
        }
    }

    private static int critRate(Player attacker, Item weapon) {
        return weapon.stats.critChance + (attacker.stats.skill / 2);
    }
    private static int critEvade (Player defender){
        return defender.stats.luck;
    }
    public static int critChance(Player attacker, Item weapon, Player defendant){
        return critRate(attacker, weapon) - critEvade(defendant);
    }
    
    public static int critDamage(Player attacker, Item weapon, Player defender, Tile defendTile, boolean triangle){
        return damage(attacker, weapon,defender,defendTile,triangle) * Defaults.CRIT_MULTIPLIER;
    }
}
