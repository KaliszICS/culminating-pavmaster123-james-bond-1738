package Services;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import Datatype.Signal;

/**
 * This class combines many listeners, which then operates inside of the Renderer class.
 * Since the renderer class owns the graphics window, UserInput relies on it to make
 * key event listeners. Those events are then funneled through this class.
 * 
 * @author Wayne Bai
 */
public class UserInput implements KeyListener, MouseListener, MouseWheelListener {

    public final Signal<KeyEvent> keyboardInputBegan = new Signal<KeyEvent>();
    public final Signal<KeyEvent> keyboardInputEnded = new Signal<KeyEvent>();
    public final Signal<MouseEvent> mouseInputBegan = new Signal<MouseEvent>();
    public final Signal<MouseEvent> mouseInputEnded = new Signal<MouseEvent>();
    public final Signal<MouseWheelEvent> mouseWheelMoved = new Signal<MouseWheelEvent>();
    public UserInput() {}
    public void keyPressed(KeyEvent e) {
        keyboardInputBegan.fire(e);

    } 
    public void keyReleased(KeyEvent e) {
        keyboardInputEnded.fire(e);

    } 
    public void keyTyped(KeyEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {
        mouseInputEnded.fire(e);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        mouseInputBegan.fire(e);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseWheelMoved.fire(e);
    }

}
