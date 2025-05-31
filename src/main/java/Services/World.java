package Services;
import java.util.ArrayList;
import java.util.Arrays;

import Datatype.Camera;
import Datatype.Vector;
import Datatype.WorldObject;

/**
 * A World object represents a group of WorldObject objects, which are then rendered with the Renderer service object.
 * 
 * @author Wayne Bai
 */
public class World {

    public ArrayList<WorldObject> instances;
    public Vector gravity = new Vector(0,-10,0);
    public double fallenPartsDestroyHeight = -500;
    public Vector spawnPosition = new Vector(0,10,0);
    public final Camera camera;

    public World(WorldObject[] things) {
        instances = new ArrayList<WorldObject>(Arrays.asList(things));
        camera = new Camera();
    }

}
