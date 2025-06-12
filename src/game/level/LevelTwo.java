package game.level;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;

import game.menu.Save;

/**
 * The second Level of the Game.
 * @author Levon Alexanian
 */
public class LevelTwo extends Level{
    private static final Color FLOOR_COLOUR = new Color(71, 184, 88);
    private final Thing floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);;

    private static final double STARTING_X = 0;
    private static final double STARTING_Y = 2;
    private static final double LEVEL_ZOOM = 4;
    private static final Color BACKGROUND_COLOUR = new Color(19, 152, 236);

    /**
     * The constructor of the second Level.
     */
    public LevelTwo(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
        initialiseLevel();
    }

    /**
     * Initializes level two.
     */
    @Override
    protected void initialiseLevel(){
        Save.load(this, "./levels/level2.txt");
    }

    /**
     * Detects when a button has been pressed.
     * @param buttonID The ID of the button pressed.
     *     If the ID is 1, kills the Player.
     *     If the ID is 2, ends the Level.
     */
    @Override
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // back to spawn
                restart();
                break;
            case 2: // end level
                endLevel();
                break;
            default:
                break;
        }
    }

    private void drawTriangle(Graphics2D g, Position pos, double size, int width, int height){
        int[] x = new int[]{
            width/2 + this.camera.relativeToCameraX(pos.getX() - size/2),
            width/2 + this.camera.relativeToCameraX(pos.getX()),
            width/2 + this.camera.relativeToCameraX(pos.getX() + size/2)
        };
        int[] y = new int[]{
            height/2 - this.camera.relativeToCameraY(pos.getY() - size/2),
            height/2 - this.camera.relativeToCameraY(pos.getY() + size/2),
            height/2 - this.camera.relativeToCameraY(pos.getY() - size/2)
        };
        g.fill(new Polygon(x, y, x.length));
    }

    /**
     * Renders the second Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    @Override
    public void render(Graphics2D g, int width, int height){
        super.render(g, width, height);
        drawThing(g, this.floor, width, height);
        g.setColor(Color.RED);
        drawTriangle(g, new Position(50, 9.5), 1, width, height);
        drawTriangle(g, new Position(51, 9.5), 1, width, height);
        drawTriangle(g, new Position(52, 9.5), 1, width, height);
        drawTriangle(g, new Position(53, 9.5), 1, width, height);
        drawTriangle(g, new Position(54, 9.5), 1, width, height);
    }
}
