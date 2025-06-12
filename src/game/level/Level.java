package game.level;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import game.Displayable;
import game.GameState;
import game.KeyBindings;

/**
 * The Level class. Has a Space, Player and Camera.
 */
public abstract class Level implements Displayable, KeyListener{
    protected ThingSearcher spikes;
    private double SPIKE_SEARCH_RADIUS = 10;
    protected String feedback = "null";
    protected Space space;
    protected Player player;
    protected Camera camera;

    protected double initialX;
    protected double initialY;
    protected double zoom;
    protected Color backgroundColour;

    /**
     * The default constructor of the Level class.
     */
    public Level(double initialX, double initialY, double zoom, Color backgroundColour){
        this.initialX = initialX;
        this.initialY = initialY;
        this.zoom = zoom;
        this.backgroundColour = backgroundColour; 
        this.player = new Player(new Position(initialX, initialY), 1, 1);
        this.camera = new Camera(this.player, zoom);
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
    public void loadThings(ArrayList<Thing> things){
        this.space.things = things;
    }

    /**
     * Loads spikes into Space.
     * In order to make certain Levels easier, the hitbox of the spike is not the same as the spike itself.
     * @param spikes The spikes to load.
     */
    public void loadSpikes(ArrayList<Thing> spikes){
        this.space.spikeArray = spikes;
        this.spikes = new ThingSearcher(this.space.spikeArray, true);
    }

    /**
     * Loads buttons into Space.
     * @param buttons The buttons to load.
     */
    public void loadButtons(ArrayList<GameButton> buttons){
        this.space.buttons = buttons;
    }

    /**
     * Updates the Level, called every game tick.
     */
    public void update(){
        this.space.update();
        for(Thing thing : this.spikes.search(this.player.getPosition(), SPIKE_SEARCH_RADIUS)){
            if(this.player.collide(thing)){
                restart();
                break;
            }
        }
    }

    /**
     * Restarts the level, usually called on the player's loss.
     */
    public void restart(){
        this.player.getPosition().setX(initialX);
        this.player.getPosition().setY(initialY);
        this.player.respawn();
        for(GameButton button : this.space.buttons){
            button.unpress();
        }
        this.camera.moveToPlayer();
    }

    /**
     * Draws a spike on the screen.
     * @param g The graphics object to draw the spike on.
     * @param thing The Thing that acts as the hitbox of the spike.
     * @param sizeX The width of the spike.
     * @param sizeY The height of the spike.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    protected void drawSpike(Graphics2D g, Thing thing, double sizeX, double sizeY, int screenWidth, int screenHeight){
        g.setColor(thing.getColour());
        int[] x = new int[]{
            screenWidth/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - sizeX/2), 
            screenWidth/2 + this.camera.relativeToCameraX(thing.getPosition().getX()), 
            screenWidth/2 + this.camera.relativeToCameraX(thing.getPosition().getX() + sizeX/2)
        };
        int[] y = new int[]{
            screenHeight/2 - this.camera.relativeToCameraY(thing.getPosition().getY() - sizeY/2), 
            screenHeight/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + sizeY/2), 
            screenHeight/2 - this.camera.relativeToCameraY(thing.getPosition().getY() - sizeY/2)
        };
        g.fill(new Polygon(x, y, x.length));
    }

    /**
     * Draws a spike on the screen, with width.
     * @param g The graphics object to draw the spike on.
     * @param thing The Thing that acts as the hitbox of the spike.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    protected void drawSpike(Graphics2D g, Thing thing, int screenWidth, int screenHeight){
        drawSpike(g, thing, 1, 1, screenWidth, screenHeight);
    }

    /**
     * Draws a GameButton onto the screen.
     * @param g The graphics object to draw the GameButton on.
     * @param button The GameButton to be drawn
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    protected void drawButton(Graphics2D g, GameButton button, int screenWidth, int screenHeight){
        drawThing(g, button.getButton(), screenWidth, screenHeight); // draw button first
        drawThing(g, button.getFoundation(), screenWidth, screenHeight);
    }

    /**
     * Draws a Thing on a buffer.
     * @param g The buffer to draw to.
     * @param thing The Thing to draw.
     * If the Thing does not have a colour, draws a black outline of the Thing.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    protected void drawThing(Graphics2D g, Thing thing, int screenWidth, int screenHeight){
        Color colour = thing.getColour();
        if(colour == null){
            g.setColor(Color.BLACK);
            g.drawRect(
                screenWidth/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - thing.getSizeX()/2),
                screenHeight/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + thing.getSizeY()/2),
                this.camera.toScale(thing.getSizeX()),
                this.camera.toScale(thing.getSizeY())
            );
        }else{
            g.setColor(colour);
            g.fillRect(
                screenWidth/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - thing.getSizeX()/2),
                screenHeight/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + thing.getSizeY()/2),
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
    public void render(Graphics2D g, int width, int height){
        g.setColor(this.backgroundColour);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
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
    }

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
    
    /**
     * Handles Player input when a key is typed.
     * Since presses and releases are already handled by keyPressed and keyReleased, does nothing.
     * @param e The KeyEvent to handle.
     */
    public void keyTyped(KeyEvent e){}
}