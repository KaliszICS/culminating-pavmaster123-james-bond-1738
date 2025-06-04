import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenu implements Displayable{
    private boolean hovered;
    private int boxWidth;
    private int boxHeight;
    public MainMenu(){
        this.hovered = false;
    }

    @Override
    public void render(Graphics2D g, int width, int height){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        if(this.hovered){
            g.setColor(Color.LIGHT_GRAY);
        }else{
            g.setColor(Color.DARK_GRAY);
        }
        this.boxWidth = width/5;
        this.boxHeight = height/5;
        g.fillRect(width/2-boxWidth/2, height/2-boxHeight/2, boxWidth, boxHeight);
        //drawThing(g, space.getPlayer(), cameraPosition, spaceScale, width, height);
        //for(Thing thing : space.things){
        //    drawThing(g, thing, cameraPosition, spaceScale, width, height);
        //}
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.WHITE);
        //g.drawString(space.getCamera().getLocation(), 50, 50);
        //g.drawString(space.getPlayer().getLocation(), 50, 100);
        //g.drawString(String.format("Zoom: %.1f", spaceScale), 50, 150);
        //g.drawString("Feedback: " + feedback, 50, 200);
    }

    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
        System.out.print(e.getX());
        System.out.print(" ");
        System.out.println(e.getY());
    }
}