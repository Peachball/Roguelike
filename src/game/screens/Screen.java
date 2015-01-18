/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

/**
 *
 * @author Chen
 */
public interface Screen {
    public void display(AsciiPanel output);
    public Screen response(KeyEvent key);
}
