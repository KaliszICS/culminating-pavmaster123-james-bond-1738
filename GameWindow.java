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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener{
    private Level level;
    private MainMenu menu;
    private BufferedImage buffer;
    
    public GameWindow(Level level){
        this.level = level;
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
        addKeyListener(this);
        addMouseWheelListener(this);
        addMouseListener(this);
        buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        this.menu = new MainMenu();
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
        //this.level.render(bufferG, getWidth(), getHeight());
        this.menu.render(bufferG, getWidth(), getHeight());
        bufferG.dispose();
        g.drawImage(buffer, 0, 0, this);
    }

    public void setFeedback(String feedback) {
        this.level.setFeedback(feedback);
    }

    public void keyPressed(KeyEvent e){
        this.level.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        this.level.keyReleased(e);
    }
    
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
        this.menu.mouseMoved(e);
    }
    public void mouseWheelMoved(MouseWheelEvent e){}
}
