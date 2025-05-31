package Services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Deprecated
public class Scheduler {

    private final ScheduledExecutorService scheduler;

    public Scheduler(int threads) {
        this.scheduler = Executors.newScheduledThreadPool(threads);
    }

    public Scheduler() {
        this(2048);
    }

    /**
     * Runs a Runnable asynchronously
     * 
     *  
     */
    public CompletableFuture<?> spawn(Runnable task) {
        return CompletableFuture.runAsync(task);
    }
    
    /**
     * Runs a Runnable after a specified time
     * @param seconds a double for the amount of seconds to delay 
     * @param task the Runnable object
     * @return the future object
     */
    public ScheduledFuture<?> delay(double seconds, Runnable task) {
        return scheduler.schedule(task, (long)(seconds * 1000), TimeUnit.MILLISECONDS);
    }

    /**
     * Halts the current thread
     * @param seconds the amount to wait for
     * @return the number of actual seconds elapsed
     */
    public double sleep(double seconds) {
        double t0 = System.nanoTime() * 1e-9;
        try {
            Thread.sleep((long)(seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ((double)(System.nanoTime() * 1e-9 - t0)) / 1e9;
    }

    public double clock() {
        return System.nanoTime() * 1e-9 / 1e9;
    }

    /** Clean up */
    public void shutdown() {
        scheduler.shutdown();
    }
}