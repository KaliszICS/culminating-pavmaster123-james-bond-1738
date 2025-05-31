package Util;

/**
 * Utility class that does math related to kinematics.
 * 
 * @author Wayne Bai
 */
public class ProjectileMath {
    
    private ProjectileMath() {}

    /**
     * Gets the speed required to gain a certain height against an amount of gravity
     * @param height positive height
     * @param gravity magnitude of gravity
     * @return
     */
    public static double getSpeedForFinalHeight(double height, double gravity) {
        // vf^2 = vi^2 + 2(-a)d
        // -vi^2 = 2(-a)d
        // vi^2 = 2ad
        // sqrt(2ad) = vi
        return Math.sqrt(2 * gravity * height);
        
    }

    /**
     * Gets the maximum vertical displacement from an initial velocity against an amount of gravity
     * @param speed speed against gravity
     * @param gravity magnitude of gravity
     * @return
     */
    public static double getFinalHeightFromInitialSpeed(double speed, double gravity) {
        // vf^2 = vi^2 + 2(-a)d
        // -vi^2 = 2(-a)d
        // vi^2 = 2ad
        // vi^2 / 2a = d
        return speed * speed * 0.5 / gravity;

    }
}
