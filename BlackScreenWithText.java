import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * This is a Displayable that is a Black Screen with Text on it.
 * @author Pavarasan Karunainathan
 */
public class BlackScreenWithText implements Displayable{
    private String text;
    private Font font;

    /**
     * The constructor of the BlackScreenWithText Displayable.
     * @param text The text to be displayed.
     */
    public BlackScreenWithText(String text){
        this.text = text;
        File file = new File("Aldrich.ttf");
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, file);
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(font);
            this.font = new Font("Aldrich", Font.PLAIN, 24);
        }catch(Exception e){
            System.out.println("ASHIGDAYUSYUD");
            this.font = new Font("Arial", Font.PLAIN, 24);
        }
    }

    /**
     * Renders the BlackScreenWithText.
     * @param g The buffer to display the BlackScreenWithText on.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void render(Graphics2D g, int width, int height){
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        Rectangle2D r = g.getFontMetrics(this.font).getStringBounds(this.text, g);
        g.drawString(this.text, (int)(width/2 - r.getWidth()/2), (int)(height/2 - r.getHeight()/2));
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void keyPressed(KeyEvent e){}

    /**
     * Once a key is released, stops displaying the BlackScreenWithText.
     * @param e The key pressed.
     */
    public void keyReleased(KeyEvent e){
        GameState.setCurrentScreen(GameState.Screen.LEVEL_SELECT);
    }
    
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
}