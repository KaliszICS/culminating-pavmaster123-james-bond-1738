package Services;

import Datatype.Vector;
import Datatype.WorldObject;

enum Axis {
    x,y
}

/**
 * The physics engine of a 2D world.
 * Due to complexity of physics engines, this is an extremely simple physics engine,
 * which does not consider complex shapes or rotation. This engine relies solely on
 * the bounding box of WorldObjects.
 * 
 * @author Wayne Bai
 */
public class Physics {

    private static final double DEFLECTION_SIZE = 1.5;
    private World world;
    /**
     * Applies concepts of elastic collisions from SPH4U.
     * Since this physics solver does not support complex shapes,
     * surface normals are ignored.
     * 
     * @param one the first WorldObject that participates in the collision
     * @param two the second WorldObject that participates in the collision
     */
    private static void collision(WorldObject one, WorldObject two, Axis axis) {
        
        double mass1 = one.getMass();
        double mass2 = two.getMass();

        // Ek = 1/2mv^2 -> (vf^2 = ((elasticity) * vi^2)) -> vf = sqrt(elasticity) * vi
        double elasticity = (one.elasticity + two.elasticity) / 2;
        double conservedVelocityFactor = Math.sqrt(elasticity);
        
        // use law of conservation of energy
        // use law of conservation of momentum

        // finalVelocityA = initialVelocityA - ((2 massB) / (massA + massB)) (initialVelocityA - initialVelocityB) * direction

        double initialVelocity1 = axis == Axis.x ?  one.velocity.x : one.velocity.y;
        double initialVelocity2 = axis == Axis.x ?  two.velocity.x : two.velocity.y;

        double finalVelocity1 = one.anchored ? initialVelocity1 : (conservedVelocityFactor * (initialVelocity1 - ((mass2 + mass2) / (mass1 + mass2)) * (initialVelocity1 - initialVelocity2)));
        double finalVelocity2 = two.anchored ? initialVelocity2 : (conservedVelocityFactor * (initialVelocity2 - ((mass1 + mass1) / (mass2 + mass1)) * (initialVelocity2 - initialVelocity1)));

        if (axis == Axis.y) {
            one.velocity = new Vector(one.velocity.x, finalVelocity1, one.velocity.z);
            two.velocity = new Vector(two.velocity.x, finalVelocity2, two.velocity.z);
        } else {
            one.velocity = new Vector(finalVelocity1, one.velocity.y, one.velocity.z);
            two.velocity = new Vector(finalVelocity2, two.velocity.y, two.velocity.z);
        }
    }
    
    private static void checkCollide(WorldObject one, WorldObject two, double dt) {
        if (one.anchored && two.anchored) { return; }
        if (!(one.physicsActive || two.physicsActive)) { return; }
        
        
        double minimumHorizontalDistance = (one.size.x + two.size.x) / 2;
        double minimumVerticalDistance = (one.size.y + two.size.y) / 2;
        
        Vector relativePosition = one.position.sub(two.position);
        Vector oldRelativePosition = one.oldPosition.sub(two.oldPosition);
        
        
        
        // minimum distances are always positive because the components of size are presumed to be non-negative and non-zero
        double xClip = Math.abs(relativePosition.x) - minimumHorizontalDistance;
        double yClip = Math.abs(relativePosition.y) - minimumVerticalDistance;
        
        // whichever axis that wasn't already clipping is the axis that the collision occurs in
        boolean oldXClipping = Math.abs(oldRelativePosition.x) <= minimumHorizontalDistance;
        boolean oldYClipping = Math.abs(oldRelativePosition.y) <= minimumVerticalDistance;
        
        if (xClip <= 0 && yClip <= 0) {
            
            if (xClip > yClip) {
                // x
                double axisTotalVelocity = Math.abs(one.velocity.x) + Math.abs(two.velocity.x);
                double stepBackOnePercent = Math.abs(one.velocity.x) / axisTotalVelocity;
                double stepBackTwoPercent = Math.abs(two.velocity.x) / axisTotalVelocity;

                double sign = (one.position.x > two.position.x) ? DEFLECTION_SIZE : -DEFLECTION_SIZE;

                if (axisTotalVelocity != 0) {   
                    if (!one.anchored) {
                        one.position = new Vector(one.position.x + xClip * -sign * stepBackOnePercent, one.position.y, one.position.z);
                    }
                    if (!two.anchored) {
                        two.position = new Vector(two.position.x + xClip * sign * stepBackTwoPercent, two.position.y, two.position.z);
                    }
                }
            } else {
                // y
                double axisTotalVelocity = Math.abs(one.velocity.y) + Math.abs(two.velocity.y);
                double stepBackOnePercent = Math.abs(one.velocity.y) / axisTotalVelocity;
                double stepBackTwoPercent = Math.abs(two.velocity.y) / axisTotalVelocity;

                double sign = (one.position.y > two.position.y) ? DEFLECTION_SIZE : -DEFLECTION_SIZE;

                if (axisTotalVelocity != 0) {
                    if (!one.anchored) {
                        one.position = new Vector(one.position.x, one.position.y + yClip * -sign * stepBackOnePercent, one.position.z);
                    }
                    if (!two.anchored) {
                        two.position = new Vector(two.position.x, two.position.y + yClip * sign * stepBackTwoPercent, one.position.z);
                    }
                }
                
            }

            // each axis is independent so collide independently
            if (oldXClipping) {
                collision(one, two, Axis.y);
            }
            if (oldYClipping) {
                collision(one, two, Axis.x);
            }

            // if (!(oldXClipping && oldYClipping)) {
            //     one.position = one.oldPosition;
            //     two.position = two.oldPosition;
            // }


            one.touched.fire(two);
            two.touched.fire(one);
            
            one.physicsActive = true;
            two.physicsActive = true;
        }
    }
    
    public void step(double dt) {
        int size = world.instances.size();
        Vector accel = world.gravity.mul(dt); 
        for (int i = 0; i < size; i++) {
            WorldObject instance = world.instances.get(i);
            if (!instance.anchored && instance.physicsActive) {

                if (instance.velocity.fEquals(Vector.zero, 0.1)) {
                    instance.zeroVelocityDuration+= dt;
                } else {
                    instance.zeroVelocityDuration = 0;
                }
                
                if (instance.zeroVelocityDuration > 1) {
                    instance.physicsActive = false;
                    instance.velocity = Vector.zero;
                    instance.zeroVelocityDuration = 0;
                }

                instance.velocity = instance.velocity.add(accel);
                instance.oldPosition = instance.position;
                instance.position = instance.position.add(instance.velocity.mul(dt));
                if (instance.position.y < world.fallenPartsDestroyHeight) {
                    world.instances.remove(instance);
                    size -= 1;
                }
            }
            if (instance.canCollide) {
                for (int j = i + 1; j < size; j++) {
                    WorldObject instance2 = world.instances.get(j);
                    if (instance2.canCollide) {
                        checkCollide(instance, instance2, dt);
                    }
                }
            }
        }
    }

    public Physics(World world) {
        this.world = world;
    }
}
