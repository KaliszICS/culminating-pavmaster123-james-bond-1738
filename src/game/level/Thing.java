package game.level;
import java.awt.Color;

/**
 * A Thing, which has a position, size and colour.
 * @author Pavarasan Karunainathan
 */
public class Thing{
    protected Position position;
    protected double sizeX;
    protected double sizeY;
    protected Color colour;

    /**
     * Constructor of a Thing with a colour.
     * @param position The Position of the Thing.
     * @param sizeX The horizontal size of the Thing.
     * @param sizeY The vertical size of the Thing.
     * @param colour The colour of the Thing.
     */
    public Thing(Position position, double sizeX, double sizeY, Color colour){
        this.position = position;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.colour = colour;
    }

    /**
     * Constructor of a Thing without a colour.
     * @param position The Position of the Thing.
     * @param sizeX The horizontal size of the Thing.
     * @param sizeY The vertical size of the Thing.
     */
    public Thing(Position position, double sizeX, double sizeY){
        this.position = position;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.colour = null;
    }

    /**
     * Gets the Position of the Thing.
     * @return 
     */
    public Position getPosition(){
        return position;
    }

    /**
     * Gets the horizontal size of the Thing.
     * @return The horizontal size of the Thing.
     */
    public double getSizeX(){
        return sizeX;
    }

    /**
     * Gets the vertical size of the Thing.
     * @return The vertical size of the Thing.
     */
    public double getSizeY(){
        return sizeY;
    }

    /**
     * Gets the colour of the Thing.
     * @return The colour of the Thing.
     */
    public Color getColour(){
        return this.colour;
    }
}