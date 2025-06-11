import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

/**
 * The sixth Level of the Game.
 * @author Pavarasan Karunainathan
 */
public class LevelSix extends Level{
    private Font font;

    private static int STARTING_X = 0;
    private static int STARTING_Y = 64;
    private static double LEVEL_ZOOM = 2.5;
    private static double VOID_LIMIT = 50.0; // the height that the player will die at if it goes under
    private static int TEXT_OFFSET = 2; // The number of pixels from the bottom to offset the text by.
    
    private static final Color BACKGROUND_COLOUR = new Color(40, 2, 2);
    protected final Color SPIKE_COLOUR = new Color(234, 16, 16);

    /**
     * The constructor of the sixth Level.
     */
    public LevelSix(){
        super();
        this.player.getPosition().setX(STARTING_X);
        this.player.getPosition().setY(STARTING_Y);
        this.camera.setZoom(LEVEL_ZOOM);
        initialiseLevel();
        File file = new File("Aldrich.ttf");
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, file);
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            this.font = new Font("Aldrich", Font.PLAIN, 24);
        }catch(Exception e){
            e.printStackTrace();
            this.font = new Font("Arial", Font.PLAIN, 24);
        }
    }

    /**
     * Initializes level six.
     */
    protected void initialiseLevel(){
        Save.load(this, "./levels/level6.txt");
    }

    /**
     * Detects when a button has been pressed.
     * @param buttonID The ID of the button pressed.
     *     If the ID is 1, ends the level.
     */
    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // ends the level
                endLevel();
                break;
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
        if(this.player.getPosition().getY() <= VOID_LIMIT){
            restart();
        }
        for(Thing thing : this.spikes.search(this.player.getPosition(), 10)){
            if(this.player.collide(thing)){
                restart();
                break;
            }
        }
    }

    private void drawSpike(Graphics2D g, Position position, double sizeX, double sizeY, int width, int height){
        g.setColor(SPIKE_COLOUR);
        int[] x = new int[]{
            width/2 + this.camera.relativeToCameraX(position.getX() - sizeX/2), 
            width/2 + this.camera.relativeToCameraX(position.getX()), 
            width/2 + this.camera.relativeToCameraX(position.getX() + sizeX/2)
        };
        int[] y = new int[]{
            height/2 - this.camera.relativeToCameraY(position.getY() - sizeY/2), 
            height/2 - this.camera.relativeToCameraY(position.getY() + sizeY/2), 
            height/2 - this.camera.relativeToCameraY(position.getY() - sizeY/2)
        };
        g.fill(new Polygon(x, y, x.length));
        g.setColor(BACKGROUND_COLOUR);
    }

    private void drawSpike(Graphics2D g, Position position, int width, int height){
        drawSpike(g, position, 1, 1, width, height);
    }

    private void drawButton(Graphics2D g, GameButton button, int width, int height){
        drawThing(g, button.getButton(), width, height); // draw button first
        drawThing(g, button.getFoundation(), width, height);
    }

    private void drawTextOnThing(Graphics2D g, Thing thing, int width, int height, String textToDraw){
        Rectangle2D r = g.getFontMetrics(this.font).getStringBounds(textToDraw, g);
        BufferedImage image = new BufferedImage((int)r.getWidth(), (int)r.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        
        g2.setColor(Color.WHITE);
        g2.setFont(this.font);
        g2.drawString(textToDraw, 0, image.getHeight()-TEXT_OFFSET);
        g2.dispose();
        int imageWidth = this.camera.toScale(thing.getSizeX());
        int imageHeight = this.camera.toScale(thing.getSizeY());
        BufferedImage toRender = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        g2 = toRender.createGraphics();
        g2.drawImage(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_FAST), 0, 0, null);
        g2.dispose();
        g.drawImage(
            toRender,
            width/2 + this.camera.relativeToCameraX(thing.getPosition().getX() - thing.getSizeX()/2),
            height/2 - this.camera.relativeToCameraY(thing.getPosition().getY() + thing.getSizeY()/2), 
            null
        );
    }

    /**
     * Renders the sixth Level.
     * @param g The graphics object to paint the Level on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void render(Graphics2D g, int width, int height){
        double zoom = this.camera.getZoom();
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        int i = -1;
        String[] text = {"CRAZY?", "I", "WAS", "CRAZY", "ONCE.", "THEY", "LOCKED", "ME", "IN", "A", "ROOM.", "A", "RUBBER", "ROOM.", "A", "CRAZY", "ROOM?"};
        for(Thing thing : this.space.things){
            drawThing(g, thing, width, height);
            if(i >= 0 && text.length > i){
                drawTextOnThing(g, thing, width, height, text[i]);
            }
            i++;
        }
        for(Thing spike : this.space.spikeArray){
            drawSpike(g, spike.getPosition(), width, height);
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