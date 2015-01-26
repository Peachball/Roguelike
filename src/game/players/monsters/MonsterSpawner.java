package game.players.monsters;

import game.defaults.Defaults;
import game.players.Coord;
import game.world.World;

public class MonsterSpawner {

    //Please add more here later...
    public static void spawnMonsters(World world, int type) {
        switch (type) {
            case Defaults.LOW_LEVEL_MONSTERS:
                spawnLowLevel(world);
                break;
        }
    }

    public void spawnMonsters(World world) {
        spawnMonsters(world, 0);
    }

    private static void spawnLowLevel(World world) {
        for (int x = 0; x < world.getXSize(); x++) {
            for (int y = 0; y < world.getYSize(); y++) {
                // OK, I have no idea how to spawn monsters, as you can tell by the following code:
                if (Math.random() < .01) {
                    world.players.add(new Kobold(world, new Coord(x, y)));
                }
            }
        }
    }

}
