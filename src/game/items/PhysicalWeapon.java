/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import java.awt.Color;

/**
 *
 * @author Chen
 */
public class PhysicalWeapon extends Item {
    public PhysicalWeapon(String name, ItemStat stats, char symbol, int type, Color color){
        super(name,stats,symbol,type,color);
    }
}
