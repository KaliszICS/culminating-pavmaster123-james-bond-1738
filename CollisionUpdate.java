public class CollisionUpdate{
    private double collisionTimeX;
    private double collisionTimeY;
    private int toUpdate;
    public static final int UPDATE_BOTH = 0;
    public static final int UPDATE_X = 1;
    public static final int UPDATE_Y = 2;

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

    public double getCollisionTime(){
        if(toUpdate == UPDATE_BOTH || toUpdate == UPDATE_X){
            return this.collisionTimeX;
        }else{
            return this.collisionTimeY;
        }
    }

    public double getCollisionTimeX(){
        return this.collisionTimeX;
    }

    public double getCollisionTimeY(){
        return this.collisionTimeY;
    }

    public int getUpdateType(){
        return this.toUpdate;
    }

    public void combine(CollisionUpdate other){ // simplify dis
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