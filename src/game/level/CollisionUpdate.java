package game.level;
/**
 * The CollisionUpdate class. 
 * Used to define collisions between a moving object and a non-moving object.
 */
public class CollisionUpdate{
    private double collisionTimeX;
    private double collisionTimeY;
    private int toUpdate;
    public static final int UPDATE_BOTH = 0;
    public static final int UPDATE_X = 1;
    public static final int UPDATE_Y = 2;

    /**
     * Constructor of the CollisionUpdate class. 
     * @param collisionTime The time it takes for the moving object to collide with the other object.
     * @param toUpdate Determines whether to update the moving object on an X-axis, a Y-axis or both.
     */
    public CollisionUpdate(double collisionTime, int toUpdate){
        if(toUpdate > 2){
            this.toUpdate = 0;
        }else{
            this.toUpdate = toUpdate;
        }
        if(this.toUpdate == UPDATE_BOTH){
            this.collisionTimeX = collisionTime;
            this.collisionTimeY = collisionTime;
        }else if(this.toUpdate == UPDATE_X){
            this.collisionTimeX = collisionTime;
        }else if(this.toUpdate == UPDATE_Y){
            this.collisionTimeY = collisionTime;
        }
    }

    /**
     * Gets the time for a collision to occur.
     * @return The collision time.
     *     If the axis to update is the X-axis,
     *     returns the collision time on the X-axis.
     *     If the axis to update is the Y-axis,
     *     return the collision time on the Y-axis.
     *     If both axises are to be updated, 
     *     returns the collision time on the X-axis.
     */
    public double getCollisionTime(){
        if(toUpdate == UPDATE_BOTH || toUpdate == UPDATE_X){
            return this.collisionTimeX;
        }else{
            return this.collisionTimeY;
        }
    }

    /**
     * Gets the time for a collision to occur on the X-axis.
     * @return The collision time for the X-axis.
     */
    public double getCollisionTimeX(){
        return this.collisionTimeX;
    }

    /**
     * Gets the time for a collision to occur on the Y-axis.
     * @return The collision time for the Y-axis.
     */
    public double getCollisionTimeY(){
        return this.collisionTimeY;
    }

    /**
     * Gets the type of the update.
     * @return The type of the update.
     */
    public int getUpdateType(){
        return this.toUpdate;
    }

    /**
     * Combines the CollisionUpdate with another one, 
     * combining the collisions together.
     * If the CollisionUpdates have opposing sides to update (UPDATE_X, UPDATE_Y)
     * will combine both collisions into (UPDATE_BOTH).
     * Otherwise, will take the CollisionUpdate with the least collision time, 
     * since the first-occuring collision must be handled first.
     * @param other The other CollisionUpdate to combine with.
     */
    public void combine(CollisionUpdate other){
        if(this.toUpdate != UPDATE_BOTH && other.toUpdate != UPDATE_BOTH){
            if(this.toUpdate != other.toUpdate){
                this.collisionTimeX = Math.min(this.collisionTimeX, other.collisionTimeX);
                this.collisionTimeY = Math.min(this.collisionTimeY, other.collisionTimeY); 
                this.toUpdate = UPDATE_BOTH;
            }else{
                if(other.collisionTimeX < this.collisionTimeX){
                    this.collisionTimeX = other.collisionTimeX;
                }

                if(other.collisionTimeY < this.collisionTimeY){
                    this.collisionTimeY = other.collisionTimeY;
                }
            }
        }else{
            if(other.collisionTimeX < this.collisionTimeX){
                this.collisionTimeX = other.collisionTimeX;
                this.toUpdate = other.toUpdate;
            }

            if(other.collisionTimeY < this.collisionTimeY){
                this.collisionTimeY = other.collisionTimeY;
                this.toUpdate = other.toUpdate;
            }
        }
    }
}