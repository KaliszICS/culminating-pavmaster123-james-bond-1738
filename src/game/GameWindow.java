package game;

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

import game.menu.MainMenu;

/**
 * The Game Window. Displays the current display set by GameState.
 * @author Wayne Bai
 * @author Pavarasan Karunainathan
 */
public class GameWindow extends Frame implements KeyListener, MouseListener, MouseMotionListener{
    private Displayable currentDisplay;
    private BufferedImage buffer;

    /**
     * The default constructor of the GameWindow.
     */
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
        setDisplay(new MainMenu());
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        setVisible(true);
    }

    /**
     * Override of the update(Graphics g) method of Frame, paints on the graphics.
     * @param g The Graphics object to paint on.
     */
    @Override
    public void update(Graphics g){
        paint(g);
    }

    /**
     * Override of the paint(Graphics g) method of Frame, 
     * displays the current display on a buffer.
     * @param g The Graphics object to paint on.
     */
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

    /**
     * Sets the current Displayable display.
     * @param display The display to change the current display to.
     */
    public void setDisplay(Displayable display){
        this.currentDisplay = display;
    }

    /**
     * Receives a key that is pressed in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void keyPressed(KeyEvent e){
        if(this.currentDisplay instanceof KeyListener){
            ((KeyListener)this.currentDisplay).keyPressed(e);
        }
    }

    /**
     * Receives a key that is released in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void keyReleased(KeyEvent e){
        if(this.currentDisplay instanceof KeyListener){
            ((KeyListener)this.currentDisplay).keyReleased(e);
        }
    }

    /**
     * Receives a typable key that is typed in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void keyTyped(KeyEvent e){
        if(this.currentDisplay instanceof KeyListener){
            ((KeyListener)this.currentDisplay).keyTyped(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had exited the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseExited(MouseEvent e){
        if(this.currentDisplay instanceof MouseListener){
            ((MouseListener)this.currentDisplay).mouseExited(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had been released in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseReleased(MouseEvent e){
        if(this.currentDisplay instanceof MouseListener){
            ((MouseListener)this.currentDisplay).mouseReleased(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had been clicked in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseClicked(MouseEvent e){
        if(this.currentDisplay instanceof MouseListener){
            ((MouseListener)this.currentDisplay).mouseClicked(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had been pressed in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mousePressed(MouseEvent e){
        if(this.currentDisplay instanceof MouseListener){
            ((MouseListener)this.currentDisplay).mousePressed(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had entered the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseEntered(MouseEvent e){
        if(this.currentDisplay instanceof MouseListener){
            ((MouseListener)this.currentDisplay).mouseEntered(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had been dragged in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseDragged(MouseEvent e){
        if(this.currentDisplay instanceof MouseMotionListener){
            ((MouseMotionListener)this.currentDisplay).mouseDragged(e);
        }
    }

    /**
     * Receives a MouseEvent if the mouse had been moved in the GameWindow.
     * Sends it to the current display.
     * @param e The KeyEvent to handle.
     */
    public void mouseMoved(MouseEvent e){
        if(this.currentDisplay instanceof MouseMotionListener){
            ((MouseMotionListener)this.currentDisplay).mouseMoved(e);
        }
    }
}
