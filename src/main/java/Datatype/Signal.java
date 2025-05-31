package Datatype;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
/**
 * A signal object inspired by the Roblox RBXScriptSignal,
 * which executes code when fired. To add code, use addListener and
 * add a lamba expression that takes exactly one parameter.
 * 
 * @author Wayne Bai
 */
public class Signal<T> {
    
    private ArrayList<Callback<T>> listeners = new ArrayList<Callback<T>>();

    public Signal() {}

    /**
     * Fires the Signal, which will cause all listeners to execute their code.
     */
    public void fire(T arg) {
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < listeners.size(); i++) {
                this.listeners.get(i).invoke(arg);
            }
        });
    }

    /**
     * Adds code to execute when the Signal is fired
     * @param thread the Runnable object to add
     */
    public void addListener(Callback<T> thread) {
        if (thread == null) { throw new NullPointerException(); }

        this.listeners.add(thread);
    }

    /**
     * Removes all occurrences of the Runnable object from the list.
     * If the Runnable object was connected more than once, those will also be removed.
     * @param thread
     * @return
     */
    public boolean removeListener(Callback<T> thread) {
        return this.listeners.remove(thread);
    }

}
