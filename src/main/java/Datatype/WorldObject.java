package Datatype;

/**
 * WorldObject is an object inspired by Roblox BasePart.
 * This object represents a simple cube in 3-space, a
 * fundamental part of the system.
 * 
 * @author Wayne Bai
 */
public class WorldObject {
    
    public Vector position = new Vector(0,0,0);
    public Vector oldPosition = position;
    public Vector velocity = new Vector(0,0,0);
    public Vector lookVector = new Vector(1,0,0);
    public Vector size = new Vector(1,1,1);
    public boolean anchored = false;
    public boolean canCollide = true;
    public boolean physicsActive = true;
    public double zeroVelocityDuration = 0;
    public double density = 1;
     
    public final Signal<WorldObject> touched = new Signal<WorldObject>();
    public final Signal<WorldObject> touchEnded = new Signal<WorldObject>();

    public double elasticity = 0.5;

    public WorldObject() {}
    public WorldObject(Vector position) {
        this.position = position;
    }
    public double getMass() {
        return size.x * size.y * size.z * density;
    }
    
    public Vector getOldPosition() {
        return oldPosition;
    }

}
