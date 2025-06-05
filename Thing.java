// idk about this name
public class Thing{
    protected Position position;
    protected double sizeX;
    protected double sizeY;

    public Thing(Position position, double sizeX, double sizeY){
        this.position = position;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Position getPosition(){
        return position;
    }

    public double getSizeX(){
        return sizeX;
    }

    public double getSizeY(){
        return sizeY;
    }
}