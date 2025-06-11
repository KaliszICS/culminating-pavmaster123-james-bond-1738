import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The Level class. Has a Space, Player and Camera.
 */
public abstract class Level implements Displayable{
    protected ThingSearcher spikes;
    protected String feedback = "null";
    protected Space space;
    protected Player player;
    protected Camera camera;

    /**
     * The default constructor of the Level class.
     */
    public Level(){
        this.player = new Player(new Position(0, 0), 1, 1);
        this.camera = new Camera(this.player);
        this.space = new Space(this.player, this.camera);
    }

    /**
     * Initializes the level.
     */
    protected abstract void initialiseLevel();

    /**
     * Is called when a button is pressed.
     * @param buttonID The ID of the button pressed, to identify which button was pressed.
     */
    protected abstract void buttonPressed(int buttonID);

    /**
     * Ends the Level.
     */
    protected void endLevel(){
        GameState.levelComplete();
    }
    
    /**
     * Loads Things into Space.
     * @param things The Things to load.
     */
    protected void loadThings(ArrayList<Thing> things){
        this.space.things = things;
    }

    /**
     * Loads spikes into Space.
     * @param spikes The spikes to load.
     */
    protected void loadSpikes(ArrayList<Thing> spikes){
        this.space.spikeArray = spikes;
        this.spikes = new ThingSearcher(this.space.spikeArray, true);
    }

    /**
     * Loads buttons into Space.
     * @param buttons The buttons to load.
     */
    protected void loadButtons(ArrayList<GameButton> buttons){
        this.space.buttons = buttons;
    }

    /**
     * Updates the Space, called every game tick.
     */
    public void update(){
        this.space.update();
    }

    /**
     * Draws a Thing on a buffer.
     * @param g The buffer to draw to.
     * @param thing The Thing to draw.
     * If the Thing does not have a colour, draws a black outline of the Thing.
     * @param width The width of the thing.
     * @param height The height of the thing.
     */
    protected void drawThing(Graphics2D g, Thing thing, int width, int height){
        Color colour = thing.getColour();
        if(colour == null){
            g.setColor(Color.BLACK);
            g.drawRect(
                width/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - thing.getSizeX()/2),
                height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + thing.getSizeY()/2),
                this.camera.toScale(thing.getSizeX()),
                this.camera.toScale(thing.getSizeY())
            );
        }else{
            g.setColor(colour);
            g.fillRect(
                width/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - thing.getSizeX()/2),
                height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + thing.getSizeY()/2),
                this.camera.toScale(thing.getSizeX()),
                this.camera.toScale(thing.getSizeY())
            );
            g.setColor(Color.BLACK);
        }
    }

    /**
     * Renders the Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    @Override
    public abstract void render(Graphics2D g, int width, int height);

    /**
     * Handles Player input when a key is pressed.
     * @param e The KeyEvent to handle.
     */
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyBindings.getJumpKey()){
            this.player.jump();
        }else if(keyCode == KeyBindings.getLeftKey()){
            this.player.goLeft();
        }else if(keyCode == KeyBindings.getRightKey()){
            this.player.goRight();
        }else if(keyCode == KeyEvent.VK_ESCAPE){
            endLevel();
        }
    }

    /**
     * Handles Player input when a key is released.
     * @param e The KeyEvent to handle.
     */
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyBindings.getLeftKey()){
            this.player.stopLeft();
        }else if(keyCode == KeyBindings.getRightKey()){
            this.player.stopRight();
        }
    }
    
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
}