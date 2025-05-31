package Datatype;

/**
 * Extension of the WorldObject which kills a player.
 */
public class KillObject extends WorldObject {
    
    public KillObject() {

        super.touched.addListener( (WorldObject o) -> {
            Character plr;
            
            if (o instanceof Character) {
                plr = (Character) o;
                plr.reset();
            }
        });

    }
    


}
