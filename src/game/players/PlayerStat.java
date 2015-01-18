/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.players;

/**
 *
 * @author Chen
 */
public class PlayerStat {

    public int hp;
    public int skill; //or known as dexterity
    public int speed;
    public int magicResist;
    public int damageResist;
    public int luck;

    public PlayerStat(int hp, int skill, int speed, int magicResist, int damageResist) {
        this.hp = hp;
        this.skill = skill;
        this.speed = speed;
        this.magicResist = magicResist;
        this.damageResist = damageResist;
        checkStats();
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
