import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Level implements Displayable{
    private int pixelsPerUnit = 20;
    private String feedback = "null";
    protected Space space;
    protected Player player;
    private Camera camera;

    public Level(){
        this.player = new Player(new Position(0, 0), 1, 1);
        this.camera = new Camera(this.player);
        this.space = new Space(this.player, this.camera);
    }

    protected abstract void initialiseLevel();
    /*{
        for(int i = 0; i < 500; i++){
            Thing thing = new Thing(new Position(20 + ((double) i)  * (0.5 + Math.random()), ((double) i) * 0.5+1),2, 0.1);
            space.things.add(thing);
        }
    }*/

    public void update(){
        this.space.update();
    }
    
    private void drawThing(Graphics2D g, Thing thing, Position cameraPosition, double scale, int width, int height){
        g.drawRect(
            (int)Math.round(width/2 + (thing.getPosition().getX() - thing.getSizeX()/2 - cameraPosition.getX()) * scale * pixelsPerUnit),
            (int)Math.round(height/2 - (thing.getPosition().getY() + thing.getSizeY()/2 - cameraPosition.getY()) * scale * pixelsPerUnit),
            (int)Math.round(thing.getSizeX() * scale * pixelsPerUnit),
            (int)Math.round(thing.getSizeY() * scale * pixelsPerUnit)
        );
    }

    @Override
    public void render(Graphics2D g, int width, int height){
        double zoom = this.camera.getZoom();
        Position cameraPosition = this.camera.getPosition();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        drawThing(g, this.player, cameraPosition, zoom, width, height);
        for(Thing thing : this.space.things){
            drawThing(g, thing, cameraPosition, zoom, width, height);
        }
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.WHITE);
        g.drawString(this.camera.getLocation(), 50, 50);
        g.drawString(this.player.getLocation(), 50, 100);
        g.drawString(String.format("Zoom: %.1f", zoom), 50, 150);
        g.drawString("Feedback: " + feedback, 50, 200);
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                this.player.jump();
                break;
            case KeyEvent.VK_A:
                this.player.goLeft();
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                this.player.goRight();
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                this.player.stopLeft();
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                this.player.stopRight();
                break;
        }
    }
    
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
}