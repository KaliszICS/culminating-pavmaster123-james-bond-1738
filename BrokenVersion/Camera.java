public class Camera{
    public Player subject;
    public double scale;
    public Position position;

    public Camera(Player subject){
        this.subject = subject;
        moveTo(this.subject.getPosition());
        this.scale = 1;
    }

    public void update(){
        moveTo(subject.getPosition());
    }

    public void moveTo(Position position){
        this.position = position;
    }

    public double getScale(){
        return this.scale;
    }

    public Position getPosition(){
        return this.position;
    }

    public String getLocation(){
        return String.format("Camera - X: %.3f, Y: %.3f", getPosition().getX(), getPosition().getY());
    }
}