import java.awt.Color;

/**
 * The Player class, is a Thing that is able to move.
 * @author Pavarasan Karunainathan
 */
public class Player extends Thing{
    private static final double MAX_MOVEMENT_SPEED = 0.20;
    private static final double MIN_MOVEMENT_SPEED = 0.04;
    private static final double JUMP_SPEED = 0.17;
    private static final double GRAVITY = 0.007;
    private static final double MOVEMENT_ACCELERATION = 0.006;
    private static final Color DEFAULT_COLOUR = new Color(240, 240, 240);
    private static final Color DIE_COLOUR = new Color(240, 90, 90);
    private boolean canJump;
    private double speedX;
    private double speedY;
    private boolean goingLeft;
    private boolean goingRight;

    private class Collision{
        public static final int NO_COLLISION = 0;
        public static final int BOTH = 1;
        public static final int START = 2;
        public static final int END = 3;

        private double amount;
        private int type;

        public Collision(double amount, int type){
            this.amount = amount;
            this.type = type;
        }

        public void flipType(){
            if(this.type == START){
                this.type = END;
            }else if(this.type == END){
                this.type = START;
            }
        }

        public int getCollisionType(){
            return this.type;
        }
    }

    /**
     * Constructor of the Player class.
     * @param position The initial position of the Player.
     * @param sizeX The horizontal size of the Player.
     * @param sizeY The vertical size of the Player.
     */
    public Player(Position position, double sizeX, double sizeY){
        super(position, sizeX, sizeY);
        this.canJump = false;
        this.speedX = 0;
        this.speedY = 0;
        this.colour = new Color(240, 240, 240);
        this.goingLeft = false;
        this.goingRight = false;
    }
    
    /**
     * Respawns the Player. Begins a death animation.
     */
    public void respawn(){
        this.colour = DIE_COLOUR;
        this.canJump = false;
        this.speedX = 0;
        this.speedY = 0;
        this.goingLeft = false;
        this.goingRight = false;
    }

    /**
     * Updates the position and velocity of the Player.
     * Is called every game tick (1/TICKS_PER_SECOND).
     */
    public void update(){
        this.position.move(this.speedX, this.speedY);
        if(this.position.getY() < 0.5){ // ensure Y >= 0.5
            this.position.setY(0.5);
            this.canJump = true;
        }else{
            this.speedY -= GRAVITY;
        }

        if(this.goingRight && this.speedX < MAX_MOVEMENT_SPEED){
            this.speedX += MOVEMENT_ACCELERATION;
        }else if(this.goingLeft && this.speedX > -MAX_MOVEMENT_SPEED){
            this.speedX -= MOVEMENT_ACCELERATION;
        }

        if(this.colour.getGreen() != DEFAULT_COLOUR.getGreen()){
            this.colour = new Color(240, this.colour.getGreen()+2, this.colour.getBlue()+2);
        }
    }

    public void update(double timeX, double timeY){
        this.position.move(this.speedX*timeX, this.speedY*timeY);
        if(this.position.getY() < 0.5){ // ensure Y >= 0.5
            this.position.setY(0.5);
            this.canJump = true;
        }else{
            this.speedY -= GRAVITY;
        }

        if(this.goingRight && this.speedX < MAX_MOVEMENT_SPEED){
            this.speedX += MOVEMENT_ACCELERATION;
        }else if(this.goingLeft && this.speedX > -MAX_MOVEMENT_SPEED){
            this.speedX -= MOVEMENT_ACCELERATION;
        }

        if(this.colour.getGreen() != DEFAULT_COLOUR.getGreen()){
            this.colour = new Color(240, this.colour.getGreen()+2, this.colour.getBlue()+2);
        }
    }

    public void updateXOnTime(double time){
        this.position.moveX(this.speedX);
        this.position.moveY(this.speedY*time);
        this.speedY = 0;
        if(this.position.getY() < 0.5){ // ensure Y >= 0.5
            this.position.setY(0.5);
            this.canJump = true;
        }else{
            this.speedY -= GRAVITY;
        }

        if(this.goingRight && this.speedX < MAX_MOVEMENT_SPEED){
            this.speedX += MOVEMENT_ACCELERATION;
        }else if(this.goingLeft && this.speedX > -MAX_MOVEMENT_SPEED){
            this.speedX -= MOVEMENT_ACCELERATION;
        }

        if(this.colour.getGreen() != DEFAULT_COLOUR.getGreen()){
            this.colour = new Color(240, this.colour.getGreen()+2, this.colour.getBlue()+2);
        }
    }

    public void updateYOnTime(double time){
        this.position.moveX(this.speedX*time);
        if(this.goingRight){
            this.speedX = MIN_MOVEMENT_SPEED;
        }else if(this.goingLeft){
            this.speedX = -MIN_MOVEMENT_SPEED;
        }else{
            this.speedX = 0;
        }
        this.position.moveY(this.speedY);
        if(this.position.getY() < 0.5){ // ensure Y >= 0.5
            this.position.setY(0.5);
            this.canJump = true;
        }else{
            this.speedY -= GRAVITY;
        }

        if(this.goingRight && this.speedX < MAX_MOVEMENT_SPEED){
            this.speedX += MOVEMENT_ACCELERATION;
        }else if(this.goingLeft && this.speedX > -MAX_MOVEMENT_SPEED){
            this.speedX -= MOVEMENT_ACCELERATION;
        }

        if(this.colour.getGreen() != DEFAULT_COLOUR.getGreen()){
            this.colour = new Color(240, this.colour.getGreen()+2, this.colour.getBlue()+2);
        }
    }

    private Collision between(double aPos, double aSize, double bPos, double bSize){
        boolean bStart = aPos-aSize/2 <= bPos-bSize/2 && bPos-bSize/2 < aPos+aSize/2;
        boolean bEnd = aPos-aSize/2 < bPos+bSize/2 && bPos+bSize/2 <= aPos+aSize/2;
        if(bStart && bEnd){
            return new Collision(bSize, Collision.BOTH);
        }else if(bStart){
            return new Collision((aPos+aSize/2) - (bPos-bSize/2), Collision.START);
        }else if(bEnd){
            return new Collision((bPos+bSize/2) - (aPos-aSize/2), Collision.END);
        }else{
            return new Collision(0, Collision.NO_COLLISION);
        }
    }

    /**
     * Detects a collision with another Thing.
     * @param other The other Thing to collide with.
     */
    public boolean collide(Thing other){
        // check X
        Collision X = between(other.position.getX(), other.sizeX, this.position.getX(), this.sizeX);

        // check Y
        Collision Y = between(other.position.getY(), other.sizeY, this.position.getY(), this.sizeY);
        
        int collisionTypeX = X.getCollisionType();
        int collisionTypeY = Y.getCollisionType();
        if(collisionTypeX == Collision.NO_COLLISION || collisionTypeY == Collision.NO_COLLISION){
            X = between(this.position.getX(), this.sizeX, other.position.getX(), other.sizeX);
            Y = between(this.position.getY(), this.sizeY, other.position.getY(), other.sizeY);
            X.flipType();
            Y.flipType();
            collisionTypeX = X.getCollisionType();
            collisionTypeY = Y.getCollisionType();
            if(collisionTypeX == Collision.NO_COLLISION || collisionTypeY == Collision.NO_COLLISION){
                return false;
            }
        }
        return true;
    }

    public CollisionUpdate moveCollide(Thing other){
        if(speedX > 0){ // going to the right
            double thisRight = this.position.getX() + this.sizeX/2;
            double otherLeft = other.position.getX() - other.sizeX/2;
            double time = (otherLeft - thisRight)/this.speedX;
            if(time >= 0 && time < 1){
                double newY = this.position.getY() + time * this.speedY;
                Collision yCollision = between(newY, this.sizeY, other.position.getY(), other.sizeY);
                if(yCollision.getCollisionType() == Collision.NO_COLLISION){
                    yCollision = between(other.position.getY(), other.sizeY, newY, this.sizeY);
                }
                if(yCollision.getCollisionType() != Collision.NO_COLLISION){
                    return new CollisionUpdate(time, CollisionUpdate.UPDATE_Y);
                }
            }
        }else if(speedX < 0){ // going to the left
            double thisLeft = this.position.getX() - this.sizeX/2;
            double otherRight = other.position.getX() + other.sizeX/2;
            double time = (thisLeft - otherRight)/-this.speedX;
            if(time >= 0 && time < 1){
                double newY = this.position.getY() + time * this.speedY;
                Collision yCollision = between(newY, this.sizeY, other.position.getY(), other.sizeY);
                if(yCollision.getCollisionType() == Collision.NO_COLLISION){
                    yCollision = between(other.position.getY(), other.sizeY, newY, this.sizeY);
                }
                if(yCollision.getCollisionType() != Collision.NO_COLLISION){
                    return new CollisionUpdate(time, CollisionUpdate.UPDATE_Y);
                }
            }
        }

        if(speedY > 0){ // going up
            double thisUp = this.position.getY() + this.sizeY/2;
            double otherDown = other.position.getY() - other.sizeY/2;
            double time = (otherDown - thisUp)/this.speedY;
            if(time >= 0 && time < 1){
                double newX = this.position.getX() + time * this.speedX;
                Collision xCollision = between(newX, this.sizeX, other.position.getX(), other.sizeX);
                if(xCollision.getCollisionType() == Collision.NO_COLLISION){
                    xCollision = between(other.position.getX(), other.sizeX, newX, this.sizeX);
                }
                if(xCollision.getCollisionType() != Collision.NO_COLLISION){
                    return new CollisionUpdate(time, CollisionUpdate.UPDATE_X);
                }
            }
        }else if(speedY < 0){ // going down
            double thisDown = this.position.getY() - this.sizeY/2;
            double otherUp = other.position.getY() + other.sizeY/2;
            double time = (thisDown - otherUp)/-this.speedY;
            if(time >= 0 && time <= 1){
                double newX = this.position.getX() + time * this.speedX;
                Collision xCollision = between(newX, this.sizeX, other.position.getX(), other.sizeX);
                if(xCollision.getCollisionType() == Collision.NO_COLLISION){
                    xCollision = between(other.position.getX(), other.sizeX, newX, this.sizeX);
                }
                if(xCollision.getCollisionType() != Collision.NO_COLLISION){
                    this.canJump = true;
                    this.speedY = 0;
                    return new CollisionUpdate(time, CollisionUpdate.UPDATE_X);
                }else{
                    this.canJump = false;
                }
            }
        }
        return new CollisionUpdate(1, CollisionUpdate.UPDATE_BOTH);
    }

    /**
     * Sets the Player's horizontal velocity to go in the negative X-direction.
     */
    public void goLeft(){
        if(!this.goingLeft){
            this.speedX = -MIN_MOVEMENT_SPEED;
            this.goingLeft = true;
        }
    }

    /**
     * Sets the Player's horizontal velocity to go in the positive X-direction.
     */
    public void goRight(){
        if(!this.goingRight){
            this.speedX = MIN_MOVEMENT_SPEED;
            this.goingRight = true;
        }
    }

    /**
     * If the Player is currently moving left,
     * stops the Player from moving to the left
     */
    public void stopLeft(){
        if(this.goingLeft){
            this.speedX = this.goingRight ? MIN_MOVEMENT_SPEED : 0;
        }
        this.goingLeft = false;
    }

    /**
     * If the Player is currently moving right,
     * stops the Player from moving to the right
     */
    public void stopRight(){
        if(this.goingRight){
            this.speedX = this.goingLeft ? -MIN_MOVEMENT_SPEED : 0;
        }
        this.goingRight = false;
    }

    /**
     * Will make the Player "jump"
     * Will only jump if the Player is on a surface.
     */
    public void jump(){
        if(this.canJump){
            this.speedY = JUMP_SPEED;
            this.canJump = false;
        }
    }

    /**
     * Sets the Player's horizontal speed.
     * @param speedX The speed to set the horizontal speed at.
     */
    public void setSpeedX(double speedX){
        this.speedX = speedX;
    }

    /**
     * Sets the Player's vertical speed.
     * @param speedY The speed to set the vertical speed at.
     */
    public void setSpeedY(double speedY){
        this.speedY = speedY;
    }

    /**
     * Gets the Player's location, in the format "Player - X: <X>, Y: <Y>".
     * @return The Player's location, in String form.
     */
    public String getLocation(){
        return String.format("Player - X: %.3f, Y: %.3f, Xspeed: %.3f", getPosition().getX(), getPosition().getY(), this.speedX);
    }
}