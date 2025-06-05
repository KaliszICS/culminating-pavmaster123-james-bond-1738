/**
 * @author Pavarasan Karunainathan
 * The Position class, has an x- and y- coordinate.
 */
public class Position{
    private double x;
    private double y;

    /**
     * The constructor of the Position class.
     * @param x The x coordinate of the Position.
     * @param y The y coordinate of the Position.
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the position by the given x and y values.
     * @param x The x-value to move the x-coordinate by.
     * @param y The y-value to move the y-coordinate by.
     */
    public void move(double x, double y){
        this.x += x;
        this.y += y;
    }

    /**
     * Gets the x-coordinate of the Position.
     * @return The x-coordinate of the Position.
     */
    public double getX(){
        return this.x;
    }

    /**
     * Gets the y-coordinate of the Position.
     * @return The y-coordinate of the Position.
     */
    public double getY(){
        return this.y;
    }
    
    /**
     * Sets the x-coordinate of the Position, to a given value.
     * @param x The x-coordinate to set the Position to.
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the Position, to a given value.
     * @param y The y-coordinate to set the Position to.
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * An Override of the toString() method.
     * Returns the Position in the format "X: <X>, Y: <Y>".
     */
    @Override
    public String toString(){
        return String.format("X: %.3f, Y: %.3f", this.x, this.y);
    }

    /**
     * An Override of the clone() method.
     * @return A copy of the Position.
     */
    @Override
    public Position clone(){
        return new Position(this.x, this.y);
    }
}