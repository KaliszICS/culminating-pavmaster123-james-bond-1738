/**
 * The Camera class, focuses on the Player and moves around the screen.
 * @author Pavarasan Karunainathan
 */

public class Camera{
    private Player subject;
    private double zoom;
    private Position position;
    private static final int PIXELS_PER_UNIT = 20;

    /**
     * The constructor of the Camera class.
     * @param subject The Player to focus on.
     */
    public Camera(Player subject){
        this.subject = subject;
        this.position = subject.getPosition().clone();
        this.zoom = 1;
    }

    /**
     * Updates the Camera Position.
     */
    public void update(){
        double deltaX = this.subject.getPosition().getX() - this.position.getX();
        this.position.moveX(deltaX*0.1);
        this.position.setY(this.subject.getPosition().getY());
    }

    /**
     * Moves the Camera to a designated Position.
     * @param position The Position to move the Camera to.
     */
    public void setPosition(Position position){
        this.position = position.clone();
    }

    /**
     * Sets the Camera's Position to be that of the Player's.
     */
    public void moveToPlayer(){
        setPosition(this.subject.getPosition());
    }

    /**
     * Gets the zoom of the Camera.
     * @return The zoom of the Camera.
     */
    public double getZoom(){
        return this.zoom;
    }

    /**
     * Sets the zoom of the Camera.
     * @param zoom The zoom to set the Camera to.
     */
    public void setZoom(double zoom){
        this.zoom = zoom;
    }

    /**
     * Gets the Position of the Camera.
     * @return The Position of the Camera.
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * Scales a decimal coordinate to an integer pixel coordinate to be used on the screen.
     * @param x The decimal coordinate to scale.
     * @return The coordinate in integer form, scaled to the Camera.
     */
    public int toScale(double x){
        return (int)Math.round(x * PIXELS_PER_UNIT * this.zoom);
    }

    /**
     * Gets the integer pixel location of a x-coordinate relative to the Camera.
     * @param x The x-coordinate to find the pixel location of.
     * @return An integer location on the screen.
     */
    public int relativeToCameraX(double x){
        return toScale(x - this.position.getX());
    }

    /**
     * Gets the integer pixel location of a y-coordinate relative to the Camera.
     * @param y The y-coordinate to find the pixel location of.
     * @return An integer location on the screen.
     */
    public int relativeToCameraY(double y){
        return toScale(y - this.position.getY());
    }

    /**
     * @return The Camera in String format.
     * is in the form "Camera - X: <X>, Y: <Y>".
     */
    public String getLocation(){
        return String.format("Camera - X: %.3f, Y: %.3f", getPosition().getX(), getPosition().getY());
    }
}