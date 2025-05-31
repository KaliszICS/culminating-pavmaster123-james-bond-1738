package Datatype;

/**
 * An object that holds all relevant data in a Raycast operation.
 * 
 * @author Wayne Bai
 */
public class RaycastResult {
    public final WorldObject object;
    public final Vector point;
    public final Vector normal;
    public final double distance;

    public RaycastResult(WorldObject object, Vector point, Vector normal, double distance) {
        this.object = object;
        this.point = point;
        this.normal = normal;
        this.distance = distance;
    }
}
