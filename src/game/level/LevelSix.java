package game.level;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import game.menu.Save;

/**
 * The sixth Level of the Game.
 * @author Pavarasan Karunainathan
 */
public class LevelSix extends Level{
    private Font font;

    private static final double STARTING_X = 0;
    private static final double STARTING_Y = 64;
    private static final double LEVEL_ZOOM = 2.5;
    private static final double VOID_LIMIT = 50.0; // the height that the player will die at if it goes under
    private static final int TEXT_OFFSET = 2; // The number of pixels from the bottom to offset the text by.
    
    private static final Color BACKGROUND_COLOUR = new Color(40, 2, 2);
    protected final Color SPIKE_COLOUR = new Color(234, 16, 16);

    /**
     * The constructor of the sixth Level.
     */
    public LevelSix(){
        super(STARTING_X, STARTING_Y, LEVEL_ZOOM, BACKGROUND_COLOUR);
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
    @Override
    protected void initialiseLevel(){
        Save.load(this, "./levels/level6.txt");
    }

    /**
     * Detects when a button has been pressed.
     * @param buttonID The ID of the button pressed.
     *     If the ID is 1, ends the level.
     */
    @Override
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
     * Updates the Level, called every game tick.
     */
    @Override
    public void update(){
        super.update();
        if(this.player.getPosition().getY() <= VOID_LIMIT){
            restart();
        }
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
    @Override
    public void render(Graphics2D g, int width, int height){
        super.render(g, width, height);
        int i = -1;
        String[] text = {"CRAZY?", "I", "WAS", "CRAZY", "ONCE.", "THEY", "LOCKED", "ME", "IN", "A", "ROOM.", "A", "RUBBER", "ROOM.", "A", "CRAZY", "ROOM?"};
        for(Thing thing : this.space.things){
            if(i >= 0 && text.length > i){
                drawTextOnThing(g, thing, width, height, text[i]);
            }
            i++;
        }
    }
}
