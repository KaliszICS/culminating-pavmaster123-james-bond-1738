package game.level;
import java.awt.Graphics2D;
import java.awt.Color;

import game.menu.Save;

/**
 * The first Level of the Game.
 * @author Pavarasan Karunainathan
 */
public class LevelOne extends Level{
    private static final Color DOOR_COLOUR = new Color(92, 64, 51);
    private static final Color FLOOR_COLOUR = new Color(71, 184, 88);
    private Thing floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);
    private MovingThing door = new MovingThing(new Position(64, 2), 0.3, 4, DOOR_COLOUR);
    
    private static final double STARTING_X = 0;
    private static final double STARTING_Y = 2;
    private static final double LEVEL_ZOOM = 2.5;

    private static final Color BACKGROUND_COLOUR = new Color(19, 152, 236);

    /**
     * The constructor of the first Level.
     */
    public LevelOne(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
        initialiseLevel();
    }


    /**
     * Initializes Level one.
     */
    @Override
    protected void initialiseLevel(){
        Save.load(this, "./levels/level1.txt");
        this.space.things.add(this.door);
    }

    /**
     * Handles the press of a GameButton.
     * @param buttonID The ID of the GameButton pressed.
     *     If the ID is 1, moves the door up.
     *     If the ID is 2, ends the Level.
     */
    @Override
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // move door up
                this.door.moveTo(new Position(this.door.getPosition().getX(), this.door.getPosition().getY()+2));
                break;
            case 2: // end level
                endLevel();
                break;
            default:
                break;
        }
    }

    /**
     * Restarts the level, usually called on the player's loss.
     */
    @Override
    public void restart(){
        super.restart();
        this.door.reset();
    }

    /**
     * Updates the Level, called every game tick.
     */
    @Override
    public void update(){
        super.update();
        this.door.update();
    }

    /**
     * Renders the first Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    @Override
    public void render(Graphics2D g, int width, int height){
        super.render(g, width, height);
        drawThing(g, this.floor, width, height);
    }
}
