/**
 * The Camera class, focuses on the Player and moves around the screen.
 * @author Pavarasan Karunainathan
 */

public class Camera{
    private Player subject;
    private double zoom;
    private Position position;

    /**
     * The constructor of the Camera class.
     * @param subject The Player to focus on.
     */
    public Camera(Player subject){
        this.subject = subject;
        moveTo(this.subject.getPosition());
        this.zoom = 1;
    }

    /**
     * Updates the Camera Position.
     */
    public void update(){
        moveTo(subject.getPosition());
    }

    /**
     * Moves the Camera to a designated Position.
     * @param position The Position to move the Camera to.
     */
    public void moveTo(Position position){
        this.position = position;
    }

    /**
     * Gets the zoom of the Camera.
     * @return The zoom of the Camera.
     */
    public double getZoom(){
        return this.zoom;
    }

    /**
     * Gets the Position of the Camera.
     * @return The Position of the Camera.
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * @return The Camera in String format.
     * is in the form "Camera - X: <X>, Y: <Y>".
     */
    public String getLocation(){
        return String.format("Camera - X: %.3f, Y: %.3f", getPosition().getX(), getPosition().getY());
    }
}