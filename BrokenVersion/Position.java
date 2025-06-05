public class Position{
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void move(double x, double y){
        this.x += x;
        this.y += y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("X: %.3f, Y: %.3f", this.x, this.y);
    }

    @Override
    public Position clone(){
        return new Position(this.x, this.y);
    }
}