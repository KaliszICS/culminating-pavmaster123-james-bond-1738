package game;

import java.awt.Graphics2D;

public interface Displayable{
    /**
     * Writes to a given Graphics2D object, to be displayed
     * @param g The Graphics2D object to write to
     * @param width The width of the Displayable
     * @param height The height of the Displayable
     */
    public void render(Graphics2D g, int width, int height);
}