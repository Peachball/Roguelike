package game.players.monsters;

import game.players.Coord;
import java.util.ArrayList;
import java.util.Collections;

public class FOV {

    public int distance;
    public ArrayList<Coord> vision;
    public Coord start;

    public FOV(Coord start, int distance) {
        this.distance = distance;
        this.start = start;
        vision = new ArrayList<Coord>();
    }

    public void clearFOV() {
        vision = new ArrayList<Coord>();
    }

    public void generateFOV(boolean xray) {
        if (xray) {
            for (int x = 0; x <= distance * 2; x++) {
                for (int y = 0; y <= distance * 2; y++) {
                    Coord buffer = new Coord(start.x + distance - x, start.y + distance - y);
                    if (Coord.realDistance(buffer, start) < distance) {
                        vision.add(buffer);
                    }

                }
            }
        } //Not too sure how to implement this just yet...
        else {
        }
        Collections.sort(vision);
    }
    
    public boolean isSeen(Coord location){
        Collections.sort(vision);
        if(Collections.binarySearch(vision,location) < 0){
            return false;
        }
        else{
            return true;
        }
    }
    

}
