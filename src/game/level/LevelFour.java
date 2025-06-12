package game.level;
import java.awt.Graphics2D;
import java.awt.Color;

import game.menu.Save;


/**
 * The fourth Level of the Game.
 * @author Pavarasan Karunainathan
 */
public class LevelFour extends Level{
    private static final Color FLOOR_COLOUR = new Color(128, 128, 128);
    private Thing floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);

    private static double STARTING_X = -2;
    private static double STARTING_Y = 2;
    private static double LEVEL_ZOOM = 2.5;
    private static final Color BACKGROUND_COLOUR = new Color(59, 98, 122);

    /**
     * The constructor of the fourth Level.
     */
    public LevelFour(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
        initialiseLevel();
    }

    /**
     * Initializes Level four.
     */
    @Override
    protected void initialiseLevel(){
        Save.load(this, "./levels/level4.txt");
    }

    /**
     * Handles the press of a GameButton.
     * @param buttonID The ID of the GameButton pressed.
     *     If the ID is 1, will end the Level.
     */
    @Override
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // end level
                endLevel();
                break;
            default:
                break;
        }
    }

    /**
     * Renders the fourth Level.
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
