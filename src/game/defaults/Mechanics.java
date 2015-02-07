package game.defaults;

import game.items.Item;
import game.items.PhysicalWeapon;
import game.players.Player;
import game.world.Tile;
import game.world.TileList;
import static game.world.TileList.GRASSLAND;

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
    
    private static int evadeRate(Player defender, Item weapon) {
        return (ASCalc(defender, weapon) * 2) + defender.stats.luck;
    }
    
    public static int accuracy(Player attacker, Item attackWeapon, Player defender, Item defendWeapon, Tile defenderTile, boolean triangle) {
        if (!triangle) {
            return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile);
        }
        return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile) + Defaults.TRIANGLE_BONUS;
    }
    
    public static int accuracy(Player attacker, Item attackWeapon, Player defender, Item defendWeapon, boolean triangle) {
        if (!triangle) {
            return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon);
        }
        return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon) + Defaults.TRIANGLE_BONUS;
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
    
    private static int pDefensePower(Player defender) {
        return defender.stats.damageResist;
    }
    
    private static int mDefensePower(Player defender) {
        return defender.stats.magicResist;
    }
    
    public static int damage(Player attacker, Item weapon, Player defender, Tile defendTile, boolean triangle) {
        if (weapon instanceof PhysicalWeapon) {
            if (attackPower(attacker, weapon, triangle) - pDefensePower(defender, defendTile) < 0) {
                return 0;
            }
            return attackPower(attacker, weapon, triangle) - pDefensePower(defender, defendTile);
        } else {
            return attackPower(attacker, weapon, triangle) - mDefensePower(defender, defendTile);
        }
    }
    
    public static int damage(Player attacker, Item weapon, Player defender, boolean triangle) {
        return damage(attacker, weapon, defender, TileList.generateTile(GRASSLAND), Item.isBetter(weapon, defender.currentWeapon));
    }
    
    private static int critRate(Player attacker, Item weapon) {
        return weapon.stats.critChance + (attacker.stats.skill / 2);
    }
    
    private static int critEvade(Player defender) {
        return defender.stats.luck;
    }
    
    public static int critChance(Player attacker, Item weapon, Player defendant) {
        return critRate(attacker, weapon) - critEvade(defendant);
    }
    
    public static int critDamage(Player attacker, Item weapon, Player defender, boolean triangle) {
        return damage(attacker, weapon, defender, triangle) * Defaults.CRIT_MULTIPLIER;
    }
    
    public static int attackTimes(Player attacker, Item weapon, Player defender, Item defendWeapon) {
        int diff = ASCalc(attacker, weapon) - ASCalc(defender, defendWeapon);
        if (diff > 8) {
            return 4;
        }
        if (diff > 4) {
            return 2;
        }
        return 1;
    }
}
