public class Player extends Thing{
    private static final double MOVEMENT_SPEED = 0.5;
    private static final double JUMP_SPEED = 0.35;
    private static final double GRAVITY = 0.01;
    private boolean canJump;
    
    public Player(Position position, double sizeX, double sizeY){
        super(position, sizeX, sizeY);
        this.canJump = false;
    }
    
    public void update(){
        this.position.move(this.speedX, this.speedY);
        if(this.position.getY() < 0){ // ensure Y >= 0
            this.position.setY(0);
            this.canJump = true;
        }else{
            this.speedY -= GRAVITY;
        }
    }

    public void goLeft(){
        this.speedX = -MOVEMENT_SPEED;
    }

    public void goRight(){
        this.speedX = MOVEMENT_SPEED;
    }

    public void stopLeft(){
        if(this.speedX == -MOVEMENT_SPEED){
            this.speedX = 0;
        }
    }

    public void stopRight(){
        if(this.speedX == MOVEMENT_SPEED){
            this.speedX = 0;
        }
    }

    public void jump(){
        if(this.canJump){
            this.speedY = JUMP_SPEED;
            this.canJump = false;
        }
    }

    public void setSpeedX(double speedX){
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY){
        this.speedY = speedY;
    }

    public String getLocation(){
        return String.format("Player - X: %.3f, Y: %.3f", getPosition().getX(), getPosition().getY());
    }
}