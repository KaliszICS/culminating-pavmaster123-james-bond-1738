
import java.util.ArrayList;

import Datatype.WorldObject;
import Services.World;

/**
 * This class is not currently finished.
 * This class holds different levels of the game.
 */
public class Worlds {
    
    private Worlds() {}
    private static ArrayList<World> worlds = new ArrayList<World>();
    private static boolean filesWereFetched = false;
    private static void fetchFiles() {
        if (filesWereFetched) { return; }
        filesWereFetched = true;

        // Intentions: to create world files that are readable

        worlds.add(new World(new WorldObject[]{}));

    } 

    public static World getLevel(int i) {

        fetchFiles();
        return worlds.get(i);
    }


}
