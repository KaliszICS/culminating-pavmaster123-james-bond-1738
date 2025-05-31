package Datatype;

/**
 * An object that can be linearly interpolated.
 * 
 * @author Wayne Bai
 */
public interface Lerpable<T> {
    T lerp(T end, double alpha);
}