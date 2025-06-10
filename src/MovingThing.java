import java.awt.Color;

/**
 * A Thing that can be animated.
 * @author Pavarasan Karunainathan
 */
public class MovingThing extends Thing{
    private double initialX;
    private double initialY;
    private boolean isMoving;
    private Position toMoveto;

    /**
     * The constructor of the MovingThing.
     * @param position The position of the MovingThing.
     * @param sizeX The horizontal size of the MovingThing.
     * @param sizeY The vertical size of the MovingThing.
     * @param colour The colour of the MovingThing.
     */
    public MovingThing(Position position, double sizeX, double sizeY, Color colour){
        super(position, sizeX, sizeY, colour);
        this.initialX = position.getX();
        this.initialY = position.getY();
        this.isMoving = false;
        this.toMoveto = null;
    }

    /**
     * Updates the MovingThing. Is called every game tick.
     * If the MovingThing is currently moving, will move the MovingThing closer to it's target by distance/TICKS_PER_SECOND.
     */
    public void update(){
        if(this.isMoving){
            double deltaX = this.toMoveto.getX() - this.position.getX();
            double deltaY = this.toMoveto.getY() - this.position.getY();
            this.position.moveX(deltaX/Game.TICKS_PER_SECOND);
            this.position.moveY(deltaY/Game.TICKS_PER_SECOND);
            if(Math.abs(deltaX) < 0.01 && Math.abs(deltaY) < 0.01){
                this.isMoving = false;
                this.toMoveto = null;
            }
        }
    }

    /**
     * Assigns the MovingThing to move to a Position.
     * @param position The Position to move to.
     */
    public void moveTo(Position position){
        if(!this.isMoving){
            this.isMoving = true;
            this.toMoveto = position;
        }
    }

    /**
     * Resets the position of the MovingThing.
     */
    public void reset(){
        this.position.setX(this.initialX);
        this.position.setY(this.initialY);
        this.isMoving = false;
        this.toMoveto = null;
    }
}