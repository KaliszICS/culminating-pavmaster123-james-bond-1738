package Services;
import java.util.ArrayList;

import Datatype.Callback;
import Datatype.Lerpable;
import Datatype.RespondingCallback;

/**
 * This class allows for the non-linear interpolation between two objects.
 */
public class Tween<T extends Lerpable<T>> {
    
    private static ArrayList<Tween<?>> tweens = new ArrayList<>();

    public static void step() {
        for (int i = 0; i < tweens.size(); i++) {
            tweens.get(i).update();
        }
    }

    

    private double startTime = System.nanoTime();
    private final double speed;
    public final T start;
    public final T end;
    public final Callback<T> callback;
    private final RespondingCallback<Double, Double> ease;
    private double pauseTime = 0;

    public Tween(T start, T end, double duration, Callback<T> callback, RespondingCallback<Double, Double> ease) {
        this.start = start;
        this.end = end;
        // add the nanoTime factor at the speed attribute so that getProgress can save one mul. operation
        this.speed = 1e-9 / duration;
        this.callback = callback;
        this.ease = ease;
    }

    public double getProgress() {
        return (System.nanoTime() - startTime) * speed;
    }

    public void update() {

        double alpha = getProgress();
        if (alpha >= 1) {
            tweens.remove(this);
        }

        callback.invoke(start.lerp(end, ease.invoke(alpha)));
    }

    public void play() {
        startTime = pauseTime + System.nanoTime();
        tweens.add(this);
    }

    public void stop() {
        tweens.remove(this);
        pauseTime = 0;
    }

    public void pause() {
        tweens.remove(this);
        pauseTime = startTime - System.nanoTime();
    }
}
