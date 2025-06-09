import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame implements KeyListener, MouseListener, MouseMotionListener{
    //private Level level;
    //private MainMenu menu;
    private Displayable currentDisplay;
    private BufferedImage buffer;
    
    public GameWindow(){
        setSize(1920, 1080);
        setResizable(true);
        setTitle("ASSESSMENT EVALUATION");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setBackground(Color.BLACK);
        setDisplay(new BlackScreenWithText("Loading..."));
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        setVisible(true);
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g){
        if(buffer == null || buffer.getWidth() != getSize().width || buffer.getHeight() != getSize().height){
            buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D bufferG = buffer.createGraphics();
        this.currentDisplay.render(bufferG, getWidth(), getHeight());
        bufferG.dispose();
        g.drawImage(buffer, 0, 0, this);
    }

    public void setDisplay(Displayable display){
        this.currentDisplay = display;
    }

    public void keyPressed(KeyEvent e){
        this.currentDisplay.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        this.currentDisplay.keyReleased(e);
    }
    
    public void keyTyped(KeyEvent e){
        this.currentDisplay.keyTyped(e);
    }
    
    public void mouseExited(MouseEvent e){
        this.currentDisplay.mouseExited(e);
    }
    
    public void mouseReleased(MouseEvent e){
        this.currentDisplay.mouseReleased(e);
    }

    public void mouseClicked(MouseEvent e){
        this.currentDisplay.mouseClicked(e);
    }

    public void mousePressed(MouseEvent e){
        this.currentDisplay.mousePressed(e);
    }
    
    public void mouseEntered(MouseEvent e){
        this.currentDisplay.mouseEntered(e);
    }

    public void mouseDragged(MouseEvent e){
        this.currentDisplay.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e){
        this.currentDisplay.mouseMoved(e);
    }
}
