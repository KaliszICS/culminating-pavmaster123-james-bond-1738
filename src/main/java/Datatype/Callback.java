package Datatype;
/**
 * A functional interface that allows for one argument.
 */
@FunctionalInterface
public interface Callback<T> {
    
    public void invoke(T value);

}
