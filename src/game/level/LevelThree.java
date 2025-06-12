package game.level;
import java.awt.Graphics2D;
import java.awt.Color;

import game.menu.Save;

/**
 * The third Level of the Game.
 * @author Levon Alexanian
 */
public class LevelThree extends Level{
    private Thing floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);

    private static final double STARTING_X = 0;
    private static final double STARTING_Y = 2;
    private static final double LEVEL_ZOOM = 2.5;
    private static final Color BACKGROUND_COLOUR = new Color(19, 152, 236);
    private static final Color FLOOR_COLOUR = new Color(71, 184, 88);

    /**
     * The constructor of the third Level.
     */
    public LevelThree(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
        initialiseLevel();
    }

    /**
     * Initializes level three.
     */
    protected void initialiseLevel(){
        Save.load(this, "./levels/level3.txt");
    }

    /**
     * Detects when a button has been pressed.
     * @param buttonID The ID of the button pressed.
     *     If the ID is 1, ends the level.
     */
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // end the Level
                endLevel();
            default:
                break;
        }
    }

    /**
     * Renders the third Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void render(Graphics2D g, int width, int height){
        super.render(g, width, height);
        drawThing(g, this.floor, width, height);
    }
}