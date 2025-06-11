import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Font;

/**
 * The third Level of the Game.
 * @author Levon Alexanian
 */
public class LevelThree extends Level{
    private Thing floor;

    private static int STARTING_X = 0;
    private static int STARTING_Y = 2;
    private static double LEVEL_ZOOM = 2.5;
    private static final Color BACKGROUND_COLOUR = new Color(19, 152, 236);
    private static final Color FLOOR_COLOUR = new Color(71, 184, 88);

    /**
     * The constructor of the third Level.
     */
    public LevelThree(){
        super();
        this.player.getPosition().setX(STARTING_X);
        this.player.getPosition().setY(STARTING_Y);
        this.camera.setZoom(LEVEL_ZOOM);
        initialiseLevel();
    }

    /**
     * Initializes level three.
     */
    protected void initialiseLevel(){
        floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);
        Save.load(this, "./src/levels/level3.txt");

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
     * Restarts the level, usually called on the player's loss.
     */
    public void restart(){
        this.player.getPosition().setX(STARTING_X);
        this.player.getPosition().setY(STARTING_Y);
        this.player.respawn();
        for(GameButton button : this.space.buttons){
            button.unpress();
        }
        this.camera.moveToPlayer();
    }

    /**
     * Updates the Level, called every game tick.
     */
    public void update(){
        this.space.update();
        for(Thing thing : this.spikes.search(this.player.getPosition(), 10)){
            if(this.player.collide(thing)){
                restart();
                break;
            }
        }
    }

    private void drawSpike(Graphics2D g, Thing thing, double sizeX, double sizeY, int width, int height){
        g.setColor(thing.getColour());
        int[] x = new int[]{
            width/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - sizeX/2), 
            width/2 + this.camera.relativeToCameraX(thing.getPosition().getX()), 
            width/2 + this.camera.relativeToCameraX(thing.getPosition().getX() + sizeX/2)
        };
        int[] y = new int[]{
            height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() - sizeY/2), 
            height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + sizeY/2), 
            height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() - sizeY/2)
        };
        g.fill(new Polygon(x, y, 3));
        g.setColor(BACKGROUND_COLOUR);
    }

    private void drawSpike(Graphics2D g, Thing thing, int width, int height){
        drawSpike(g, thing, 1, 1, width, height);
    }

    private void drawButton(Graphics2D g, GameButton button, int width, int height){
        drawThing(g, button.getButton(), width, height); // draw button first
        drawThing(g, button.getFoundation(), width, height);
    }

    /**
     * Renders the third Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void render(Graphics2D g, int width, int height){
        double zoom = this.camera.getZoom();
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        drawThing(g, this.floor, width, height);
        for(Thing thing : this.space.things){
            drawThing(g, thing, width, height);
        }
        for(Thing thing : this.space.spikeArray){
            drawSpike(g, thing, width, height);
        }
        for(GameButton button : this.space.buttons){
            drawButton(g, button, width, height);
        }
        drawThing(g, this.player, width, height);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.WHITE);
        g.drawString(this.camera.getLocation(), 50, 50);
        g.drawString(this.player.getLocation(), 50, 100);
        g.drawString(String.format("Zoom: %.1f", zoom), 50, 150);
        g.drawString("Feedback: " + feedback, 50, 200);
    }
}