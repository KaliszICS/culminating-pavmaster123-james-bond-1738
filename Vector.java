package Datatype;
/**
 * A 3d Vector data type inspired by Roblox Vector3.
 * 
 * @author Wayne Bai
 */
public class Vector implements Lerpable<Vector> {
    
    public final double x;
    public final double y;
    public final double z;
    public final double magnitude;

    public static final Vector zero = new Vector();
    public static final Vector one = new Vector(1,1,1);
    public static final Vector xAxis = new Vector(1,0,0);
    public static final Vector yAxis = new Vector(0,1,0);
    public static final Vector zAxis = new Vector(0,0,1);

    private static final double FUZZY_DEFAULT = 0.01;

    private static double computeMagnitude(double x, double y, double z) {
        return Math.sqrt(
            Math.pow(x,2) +
            Math.pow(y,2) +
            Math.pow(z, 2)
            );
    }

    public Vector() {
        x = 0;
        y = 0;
        z = 0;
        magnitude = 0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        magnitude = computeMagnitude(x, y, z);
    }

    public Vector unit() {
        if (magnitude == 0) return zero;
        return new Vector(
            x/magnitude,
            y/magnitude,
            z/magnitude
        );
    }
    

    public Vector sub(Vector v) {
        return new Vector(
            x - v.x,
            y - v.y,
            z - v.z
        );
    }

    public Vector add(Vector v) {
        return new Vector(
            x + v.x,
            y + v.y,
            z + v.z
        );
    }

    public Vector mul(Vector v) {
        return new Vector(
            x * v.x,
            y * v.y,
            z * v.z
        );
    }

    public Vector mul(double n) {
        return new Vector(
            x*n,
            y*n,
            z*n
        );
    }

    public Vector div(Vector v) {
        return new Vector(
            x / v.x,
            y / v.y,
            z / v.z
        );
    }

    public Vector div(double n) {
        return new Vector(
            x/n,
            y/n,
            z/n
        );
    }

    public double dot(Vector v) {
        return 
            v.x * x +
            v.y * y +
            v.z * z;
    }

    public Vector cross(Vector v) {
        return new Vector(
            y*v.z-z*v.y,
            z*v.x-x*v.z,
            x*v.y-y*v.x
        );
    }

    public Vector lerp(Vector v, double a) {
        double _a = 1 - a;

        return new Vector(
            x*_a + v.x * a,
            y*_a + v.y * a,
            z*_a + v.z * a
        );
    }

    public Vector min(Vector v) {
        return new Vector(
            Math.min(v.x,x),
            Math.min(v.y,y),
            Math.min(v.z,z)
        );
    }

    public Vector max(Vector v) {
        return new Vector(
            Math.max(v.x,x),
            Math.max(v.y,y),
            Math.max(v.z,z)
        );
    }

    public boolean fEquals(Vector v) {
        return computeMagnitude(x-v.x,y-v.y,z-v.z) <= FUZZY_DEFAULT;
    }
    
    public boolean fEquals(Vector v, double fuzzy) {
        return computeMagnitude(x-v.x,y-v.y,z-v.z) <= fuzzy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector v = (Vector) o;
        return x == v.x && y == v.y && z == v.z;

    }
    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }
    
    @Override
    public int hashCode() {
        return 3548239 ^ Double.hashCode(x) ^ Double.hashCode(y) ^ Double.hashCode(z);
    }

}
 