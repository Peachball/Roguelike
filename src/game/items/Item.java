/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.items;

import game.defaults.Defaults;
import java.awt.Color;

/**
 *
 * @author Chen
 */
public class Item {

    public String name;
    public ItemStat stats;
    public char symbol;
    public Color foreground;
    public int type;

    public Item(String name, ItemStat stats, char symbol, int type) {
        this.name = name;
        this.stats = stats;
        this.symbol = symbol;
        foreground = Defaults.FOREGROUND_COLOR;
        this.type = type;
    }

    public Item(String name, ItemStat stats, char symbol, int type, Color color) {
        this(name, stats, symbol, type);
        foreground = color;

    }

}
