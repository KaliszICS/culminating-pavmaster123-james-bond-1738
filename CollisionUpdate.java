public class CollisionUpdate{
    private double collisionTime;
    private int toUpdate;
    public static final int UPDATE_BOTH = 0;
    public static final int UPDATE_X = 1;
    public static final int UPDATE_Y = 2;

    public CollisionUpdate(double collisionTime, int toUpdate){
        this.collisionTime = collisionTime;
        if(toUpdate > 2){
            this.toUpdate = 0;
        }else{
            this.toUpdate = toUpdate;
        }
    }

    public double getCollisionTime(){
        return this.collisionTime;
    }

    public int getUpdateType(){
        return this.toUpdate;
    }

    public void combine(CollisionUpdate other){ // simplify dis
        if(this.toUpdate != UPDATE_BOTH && other.toUpdate != UPDATE_BOTH){
            if(this.toUpdate != other.toUpdate){
                this.collisionTime = Math.min(this.collisionTime, other.collisionTime);
                this.toUpdate = UPDATE_BOTH;
            }else{
                if(other.collisionTime < this.collisionTime){
                    this.collisionTime = other.collisionTime;
                }
            }
        }else{
            if(other.collisionTime < this.collisionTime){
                this.collisionTime = other.collisionTime;
                this.toUpdate = other.toUpdate;
            }
        }
    }
}