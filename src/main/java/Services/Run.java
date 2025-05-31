package Services;

import Datatype.Signal;
import Datatype.Vector;
import Datatype.WorldObject;


/**
 * The main game executor that combines most services.
 * 
 * @author Wayne Bai
 */
public class Run {
    
    public final Scheduler task = new Scheduler();
    public final UserInput userInputListener = new UserInput();
    public final World world;

    private boolean enabled = false;
    private double frameRate = 144;
    private double frameInterval = 1 / frameRate;

    public final Signal<Double> RenderStepped = new Signal<Double>();
    public final Signal<Double> PhysicsStepped = new Signal<Double>();

    private final Physics solver;
    private final Renderer renderer;

    public Run() {

        WorldObject o = new WorldObject();
        o.position = new Vector(2,3,2);
        o.size = new Vector(1,1,1);
        // o.elasticity = 1.001;

        WorldObject o2 = new WorldObject();
        o2.position = new Vector(2.3,5,2.4);
        o2.size = new Vector(0.5,1,1);
        // o2.elasticity = 1;
        
        WorldObject o3 = new WorldObject();
        o3.position = new Vector(5,50,5);
        o3.size = new Vector(30,5,10);
        o3.elasticity = 1.5;
        // o3.elasticity = 0.5;
        WorldObject floor = new WorldObject();
        // o.position = new Vector(0,0,0);
        floor.size = new Vector(1000,1,1000);
        floor.position = new Vector(0,1,0);
        // floor.elasticity = 1.1;
        floor.anchored = true;

        world = new World(new WorldObject[]{
            o,floor,o2,o3
        });
        for (int i = 0; i < 500; i++) {
            WorldObject obj = new WorldObject();
            obj.physicsActive = false;
            obj.anchored = true;
            obj.size = new Vector(2,0.1,0.1);
            obj.elasticity = 0;
            obj.position = new Vector(20 + ((double) i) * (0.5 + Math.random()),((double) i) * 0.5,4);
            world.instances.add(obj);
        }

        WorldObject o5 = new WorldObject();
        o5.size = new Vector(100, 4, 1);
        o5.position = new Vector(-50,9,9);
        

        solver = new Physics(world);
        renderer = new Renderer(world, userInputListener);
        
        
        // world.gravity = new Vector(0,-100,0);
    }
    /**
     * Runs the game.
     */
    public void init() {
        if (enabled) { return; }
        enabled = true;
        renderer.setVisible(true);

        double frameBegin = System.nanoTime() * 1e-9;
        while (true) {
            double dt = System.nanoTime() * 1e-9 - frameBegin;
            double begin = System.nanoTime();
            solver.step(dt);
            dt = System.nanoTime() * 1e-9 - frameBegin;
            PhysicsStepped.fire(dt);
            Tween.step();
            renderer.setFeedback("FPS: " + String.format("%.1f", 1 / dt) + " parts: " + world.instances.size());
            renderer.repaint();
            double now = System.nanoTime() * 1e-9;
            dt = now - frameBegin;
            RenderStepped.fire(dt);
            
            frameBegin = now;
            double pt = (System.nanoTime() - begin) * 1e-9;
            
            double sleepTime = frameInterval - pt;

            if (sleepTime > 0) {
                task.sleep(sleepTime);
            }


        }

    }

    public void setFrameRate(double fps) {
        frameRate = fps;
        frameInterval = 1 / fps;

    }

    public double getFrameRate() {
        return frameRate;
    }
}