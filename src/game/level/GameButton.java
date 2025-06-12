package game.level;
import java.awt.Color;

/**
 * A Button in a Level. If pressed, will call buttonPressed(id) in the Level.
 * Has a red top part (button) and a grey foundation.
 */
public class GameButton{
    private Thing button;
    private Thing foundation;
    private boolean pressed;
    private Level level;
    public int buttonID;
    public Position position;

    private static final double FOUNDATION_WIDTH = 1;
    private static final double FOUNDATION_HEIGHT = 0.3;
    private static final double BUTTON_WIDTH = 0.8;
    private static final double BUTTON_HEIGHT = 0.2;
    private static final double BUTTON_CLOSED_HEIGHT = 0.05;
    private static final Color FOUNDATION_COLOR = new Color(140, 140, 140);
    private static final Color BUTTON_COLOR = new Color(255, 0, 0);

    /**
     * The constructor of the GameButton.
     * @param position The Position of the GameButton.
     * @param level The Level that the GameButton is in.
     * @param buttonID The ID of the GameButton given to the Level.
     */
    public GameButton(Position position, Level level, int buttonID){
        double x = position.getX();
        double y = position.getY();
        this.position = position;
        this.foundation = new Thing(new Position(x, y - FOUNDATION_HEIGHT/2), FOUNDATION_WIDTH, FOUNDATION_HEIGHT, FOUNDATION_COLOR);
        this.button = new Thing(new Position(x, y), BUTTON_WIDTH, BUTTON_HEIGHT*2, BUTTON_COLOR);
        this.pressed = false;
        this.level = level;
        this.buttonID = buttonID;
    }

    /**
     * Gets the button (top part) of the GameButton.
     * @return The button part of the GameButton.
     */
    public Thing getButton(){
        return this.button;
    }

    /**
     * Gets the foundation of the GameButton.
     * @return The foundation of the GameButton.
     */
    public Thing getFoundation(){
        return this.foundation;
    }

    /**
     * If the button was pressed, updates the animation on the button 
     * to make it smaller, to indicate that it was pressed.
     */
    public void update(){
        if(this.pressed && this.button.sizeY > BUTTON_CLOSED_HEIGHT*2){
            this.button.sizeY -= 0.025;
        }
    }

    /**
     * Presses the button, and begins an animation of the button being pressed.
     * Calls Level.buttonPressed(buttonID) to realize that 
     * the button has been pressed.
     */
    public void press(){
        if(this.pressed == false){
            this.pressed = true;
            this.level.buttonPressed(this.buttonID);
        }
    }

    /**
     * Resets the button to it's standard position and stops any animation.
     */
    public void unpress(){
        this.button.sizeY = BUTTON_HEIGHT*2;
        this.pressed = false;
    }
}