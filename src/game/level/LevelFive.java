package game.level;
import java.awt.Graphics2D;
import java.awt.Color;

import game.menu.Save;

/**
 * The fifth Level of the Game.
 * @author Pavarasan Karunainathan
 */
public class LevelFive extends Level{
    private static final Color FLOOR_COLOUR = new Color(128, 128, 128);
    private static final Color DOOR_COLOUR = new Color(92, 64, 51);
    private Thing floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);
    private MovingThing door = new MovingThing(new Position(62.35, 2), 0.3, 4, DOOR_COLOUR);
    private MovingThing doorTwo = new MovingThing(new Position(78.35, 2), 0.3, 4, DOOR_COLOUR);

    private static final double STARTING_X = -2;
    private static final double STARTING_Y = 2;
    private static final double LEVEL_ZOOM = 2.5;

    private static final Color BACKGROUND_COLOUR = new Color(64, 60, 60);

    /**
     * The constructor of the fifth Level.
     */
    public LevelFive(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
        initialiseLevel();
    }

    /**
     * Initializes Level five.
     */
    @Override
    protected void initialiseLevel(){
        Save.load(this, "./levels/level5.txt");
        this.space.things.add(this.door);
        this.space.things.add(this.doorTwo);
    }

    /**
     * Updates the Level, called every game tick.
     */
    @Override
    public void update(){
        super.update();
        this.door.update();
        this.doorTwo.update();
    }

    /**
     * Handles the press of a GameButton.
     * @param buttonID The ID of the GameButton pressed.
     *     If the ID is 1, moves a door up.
     *     If the ID is 2, moves another door up.
     *     If the ID is 3, ends the Level.
     */
    @Override
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // move door up
                System.out.println("AAA");
                this.door.moveTo(new Position(this.door.getPosition().getX(), this.door.getPosition().getY()+3));
                break;
            case 2: // move doorTwo up
                this.doorTwo.moveTo(new Position(this.doorTwo.getPosition().getX(), this.doorTwo.getPosition().getY()+3));
                break;
            case 3: // end level
                endLevel();
            default:
                break;
        }
    }

    /**
     * Renders the fifth Level.
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
