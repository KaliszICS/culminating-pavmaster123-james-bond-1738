import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Level1 implements Displayable{
    private int pixelsPerUnit = 20;
    private String feedback = "null";
    private Space space;

    public Level1(Space space){
        this.space = space;
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
        double spaceScale = space.getCamera().getScale();
        Position cameraPosition = space.getCamera().getPosition();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        drawThing(g, space.getPlayer(), cameraPosition, spaceScale, width, height);
        for(Thing thing : space.things){
            drawThing(g, thing, cameraPosition, spaceScale, width, height);
        }
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.WHITE);
        g.drawString(space.getCamera().getLocation(), 50, 50);
        g.drawString(space.getPlayer().getLocation(), 50, 100);
        g.drawString(String.format("Zoom: %.1f", spaceScale), 50, 150);
        g.drawString("Feedback: " + feedback, 50, 200);
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                this.space.getPlayer().jump();
                break;
            case KeyEvent.VK_A:
                this.space.getPlayer().goLeft();
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                this.space.getPlayer().goRight();
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                this.space.getPlayer().stopLeft();
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                this.space.getPlayer().stopRight();
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