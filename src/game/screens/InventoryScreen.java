package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.players.Human;
import game.world.World;
import java.awt.event.KeyEvent;

public class InventoryScreen implements Screen {

    public World world;
    public Human human;
    public int marker;
    public int maxMarker;

    public InventoryScreen(World world, Human human) {
        this.world = world;
        this.human = human;
        marker = 0;
        maxMarker = human.inventory.size();
    }

    @Override
    public void display(AsciiPanel output) {
        if(maxMarker>Defaults.MAX_INVENTORY_PER_SCREEN){
            maxMarker = Defaults.MAX_INVENTORY_PER_SCREEN;
        }
        for(int counter = 0;counter<maxMarker;counter++){
            
        }
    }

    @Override
    public Screen response(KeyEvent key) {
        switch(key.getKeyCode()){
            case Defaults.up:
                moveUp();
                break;
            case Defaults.down:
                moveDown();
                break;
            case Defaults.A:
                
        }
        return this;
    }

    private void moveUp() {
        marker++;
        
        if (marker >= Defaults.MAX_INVENTORY_PER_SCREEN) {
            marker--;
        }
    }

    private void moveDown() {
        marker--;
        if (marker < 0) {
            marker = 0;
        }
    }
    
    private void pressButton(boolean aButton){
        
    }

}
