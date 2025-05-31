package Services;
import Datatype.Line;
import Datatype.RaycastResult;
import Datatype.Vector;
import Datatype.WorldObject;
public class Raycast {

    private Raycast() {}

    public static Line[] getFaces(WorldObject object) {
        Line[] faces = new Line[4];

        double sx = object.size.x/2;
        double sy = object.size.y/2;
        double sz = object.size.z/2;

        Vector tr = object.position.add(new Vector(sx, sy,sz));
        Vector tl = object.position.add(new Vector(-sx, sy,sz));
        Vector br = object.position.add(new Vector(sx, -sy,sz));
        Vector bl = object.position.add(new Vector(-sx, -sy,sz));

        faces[0] = new Line(tl, tr);
        faces[1] = new Line(tr, br);
        faces[2] = new Line(br, bl);
        faces[3] = new Line(bl, tl);
        return faces;
    }

    public static RaycastResult cast(World world, Vector from, Vector direction) {

        Line ray = new Line(from, from.add(direction));

        for (WorldObject object : world.instances) {

            Line[] faces = getFaces(object);
            
            for (Line face : faces) {
                Vector intersectionPoint = face.getIntersectionPoint(ray);
                if (intersectionPoint != null) {
                    return new RaycastResult(object, intersectionPoint, face.getNormal(), intersectionPoint.sub(from).magnitude);
                }
            }
        }
        return null;
    }
    
}
