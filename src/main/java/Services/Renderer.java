package Services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import Datatype.Vector;
import Datatype.WorldObject;
public class Renderer extends Frame {
    
    public int pixelsPerUnit = 20;
    private String feedback = "null";
    private World world;
    private BufferedImage buffer;

    public Renderer(World world, UserInput userInput) {
        this.world = world;
        
        setSize(1920, 1080);
        setResizable(true);
        setTitle("Platformer");
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setBackground(Color.BLACK);
        addKeyListener(userInput);
        addMouseWheelListener(userInput);
        addMouseListener(userInput);
        
        // Initialize buffer
        buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        setVisible(true);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        // Create or resize buffer if needed
        if (buffer == null || buffer.getWidth() != getSize().width || buffer.getHeight() != getSize().height) {
            buffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        }

        double worldScale = world.camera.worldScale;
        Vector cameraPosition = world.camera.position;
        // Clear the buffer
        Graphics2D bufferG = buffer.createGraphics();
        bufferG.setColor(getBackground());
        bufferG.fillRect(0, 0, getSize().width, getSize().height);

        // Draw all objects to buffer
        bufferG.setColor(Color.WHITE);
        for (int i = 0; i < world.instances.size(); i++) {
            WorldObject instance = world.instances.get(i);
            bufferG.drawRect(
                (int) Math.round(
                        getSize().width/2 // screen offset
                        + (
                            instance.position.x 
                            - instance.size.x/2 
                            - cameraPosition.x
                        ) * worldScale * pixelsPerUnit // object offset

                    ),
                (int) Math.round(
                        getSize().height/2 // screen offset
                        - (instance.position.y + instance.size.y/2 - cameraPosition.y) * worldScale * pixelsPerUnit // object offset
                        ),
                (int) Math.round(instance.size.x * worldScale * pixelsPerUnit),
                (int) Math.round(instance.size.y * worldScale * pixelsPerUnit)
            );
        }
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        bufferG.setColor(Color.WHITE);
        bufferG.drawString(String.format("Camera - X: %.3f, Y: %.3f, Z: %.3f", cameraPosition.x, cameraPosition.y, cameraPosition.z), 50, 50);
        bufferG.drawString(String.format("Player - X: %.3f, Y: %.3f, Z: %.3f", world.camera.subject.position.x, world.camera.subject.position.y, world.camera.subject.position.z), 50, 100);
        bufferG.drawString(String.format("Zoom: %.1f", world.camera.worldScale), 50, 150);
        bufferG.drawString("Feedback: " + feedback, 50, 200);
        bufferG.dispose();

        // Draw the buffer to the screen
        g.drawImage(buffer, 0, 0, this);
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
