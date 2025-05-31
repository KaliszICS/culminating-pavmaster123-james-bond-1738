package Datatype;

/**
 * A responding callback that takes one argument and returns one value.
 * 
 * @author Wayne Bai
 */
public interface RespondingCallback<T,R> {
    public R invoke(T a);
}
