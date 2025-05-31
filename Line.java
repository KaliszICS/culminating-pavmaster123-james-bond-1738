package Datatype;

/**
 * A Line object represents the shortest path between two Vector points.
 * It is used to perform spatial query operations, more commonly in 3 dimensions.
 * 
 * @author Wayne Bai
 */
public class Line {
    
    public final Vector p0;
    public final Vector p1;

    /**
     * Creates a new Line from point 1 to point 2.
     * @param p0 the first Vector point.
     * @param p1 the second Vector point.
     */
    public Line(Vector p0, Vector p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    /**
     * Does vector math to determine the intersection point of this Line and the other Line.
     * If no intersection point exists, the method will return null.
     * @param other the other Line
     * @return 
     */
    public Vector getIntersectionPoint(Line other) {
        Vector d1 = p1.sub(p0); // Direction of this segment
        Vector d2 = other.p1.sub(other.p0); // Direction of other segment
        Vector r = other.p0.sub(p0);
    
        Vector d1xd2 = d1.cross(d2);
        double denom = d1xd2.magnitude;
        double epsilon = 1e-8;
    
        if (denom < epsilon) {
            // Parallel or colinear
            return null;
        }
    
        double t = (r.cross(d2)).dot(d1xd2) / (denom * denom);
        double s = (r.cross(d1)).dot(d1xd2) / (denom * denom);
    
        // Check if intersection point is within both segments
        if (t < -epsilon || t > 1 + epsilon || s < -epsilon || s > 1 + epsilon) {
            return null;
        }
    
        Vector pointOnThis = p0.add(d1.mul(t));
        Vector pointOnOther = other.p0.add(d2.mul(s));
    
        if (pointOnThis.sub(pointOnOther).magnitude < epsilon) {
            return pointOnThis; // or pointOnOther, they are the same
        } else {
            return null;
        }
    }

    /**
     * Gets the normal of a line.
     * This method likely won't be used as the game
     * is based on cubes.
     * @return
     */
    public Vector getNormal() {
        // for 2D, there is no second vector, so we use the z axis
        return p1.sub(p0).cross(p0.sub(Vector.zAxis));
    }

    
}
