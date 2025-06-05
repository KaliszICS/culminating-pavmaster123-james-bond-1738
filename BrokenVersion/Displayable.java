import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface Displayable extends KeyListener, MouseListener, MouseMotionListener{
    public void render(Graphics2D g, int width, int height);
}