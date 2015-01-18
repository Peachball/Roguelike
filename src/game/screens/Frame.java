package game.screens;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Frame extends JFrame implements KeyListener {

    private AsciiPanel panel;
    private Screen screen;

    public Frame(AsciiPanel panel, Screen screen) {
        super();
        add(panel);
        pack();
        this.panel = panel;
        this.screen = screen;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        repaint();
    }
    
    public Frame(AsciiPanel panel){
        this(panel, new StartScreen());
    }

    @Override
    public void repaint() {
        panel.clear();
        screen.display(panel);
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.response(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
