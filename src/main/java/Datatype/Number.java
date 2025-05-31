package Datatype;

/**
 * A wrapper class for double values.
 * This class exists so that doubles can 
 * be tweened with the Tween Service.
 * 
 * @author Wayne Bai
 */
public class Number implements Lerpable<Number> {

    public final double v;
    
    /**
     * Creates a new Number object
     * @param v the value of the Number which is stored as a double
     */
    public Number(double v) {
        this.v = v;
    }

    /**
     * Returns a new Number that is a result of the linear
     * interpolation from this Number to the given Number.
     * @param n the goal Number
     * @param a the alpha for interpolation
     */
    public Number lerp(Number n, double a) {
        return new Number(v * (1 - a) + n.v * a);
    }
}
