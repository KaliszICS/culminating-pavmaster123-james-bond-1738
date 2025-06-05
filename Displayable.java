/** 
 * @author Pavarasan Karunainathan
 * An interface that may be used to display Graphics, while also
 * taking in user input.
 */

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface Displayable extends KeyListener, MouseListener, MouseMotionListener{
    /**
     * Writes to a given Graphics2D object, to be displayed
     * @param g The Graphics2D object to write to
     * @param width The width of the Displayable
     * @param height The height of the Displayable
     */
    public void render(Graphics2D g, int width, int height);
}