package game.defaults;

import game.items.Item;
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

    private static int accuracy(Player attacker, Item attackWeapon, Player defender, Item defendWeapon, Tile defenderTile, boolean triangle) {
        if (!triangle) {
            return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile);
        }
        return hitRate(attacker, attackWeapon) - evadeRate(defender, defendWeapon, defenderTile) + Defaults.TRIANGLE_BONUS;
    }
    
    private static int attackPower(Player attacker,Item weapon,boolean triangle){
        if(!triangle){
            
        }
        return attacker.stats.strength + ((weapon.stats.damage+Defaults.TRIANGLE_BONUS)*Defaults.WEAPON_EFFECTIVNESS);
    }
}
