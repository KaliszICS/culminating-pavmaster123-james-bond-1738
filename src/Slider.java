import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * A customizable slider component for adjusting numerical values.
 * @author Levon Alexanian
 */
public class Slider {
    private int x, y, width, height;
    private int value;
    private int minValue, maxValue;
    private boolean isDragging;
    private String label;
    private Rectangle sliderTrack;
    private Rectangle sliderHandle;

    /**
     * Constructs a new Slider with specified parameters.
     * @param label The text label displayed above the slider
     * @param x The x-coordinate of the slider's top-left corner
     * @param y The y-coordinate of the slider's top-left corner
     * @param width The width of the slider track
     * @param height The height of the slider handle
     * @param minValue The minimum value the slider can represent
     * @param maxValue The maximum value the slider can represent
     * @param initialValue The starting value of the slider
     */
    public Slider(String label, int x, int y, int width, int height, int minValue, int maxValue, int initialValue) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = initialValue;
        this.sliderTrack = new Rectangle(x, y + height/2 - 4, width, 8);
        this.sliderHandle = new Rectangle(0, y, 20, height);
        updateHandlePosition();
    }

    /**
     * Updates the position of the slider.
     * @param x The new x-coordinate of the slider's top-left corner
     * @param y The new y-coordinate of the slider's top-left corner
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.sliderTrack.setLocation(x, y + height/2 - 4);
        updateHandlePosition();
    }

    /**
     * Updates the size of the slider.
     * @param width The new width of the slider track
     * @param height The new height of the slider handle
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.sliderTrack.setSize(width, 8);
        this.sliderHandle.setSize(20, height);
        updateHandlePosition();
    }

    /**
     * Updates the position of the slider handle based on the current value.
     * Calculates the handle position as a percentage between min and max values.
     */
    private void updateHandlePosition() {
        float percentage = (float)(value - minValue) / (maxValue - minValue);
        int handleX = x + (int)(percentage * (width - 20));
        sliderHandle.x = handleX;
        sliderHandle.y = y;
    }

    /**
     * Renders the slider on the specified graphics context.
     * Draws the label, track background, filled portion, and handle.
     * @param g The Graphics2D context to render on
     */
    public void render(Graphics2D g) {
        // Draw label
        g.setColor(Color.WHITE);
        g.drawString(label, x, y - 10);

        // Draw track background
        g.setColor(new Color(100, 100, 100));
        g.fill(sliderTrack);

        // Draw filled portion of track
        float percentage = (float)(value - minValue) / (maxValue - minValue);
        int filledWidth = (int)(percentage * width);
        g.setColor(new Color(60, 60, 60));
        g.fillRect(x, y + height/2 - 4, filledWidth, 8);

        // Draw handle
        g.setColor(isDragging ? new Color(200, 200, 200) : Color.WHITE);
        g.fill(sliderHandle);
        g.setColor(Color.BLACK);
        g.draw(sliderHandle);
    }

    /**
     * Checks if the given mouse coordinates are within the slider's interactive area.
     * Returns true if the mouse is over either the handle or the track.
     * @param mouseX The x-coordinate of the mouse
     * @param mouseY The y-coordinate of the mouse
     * @return true if the mouse is over the slider, false otherwise
     */
    public boolean checkHovered(int mouseX, int mouseY) {
        // Check if mouse is over either the handle or the track
        return sliderHandle.contains(mouseX, mouseY) || sliderTrack.contains(mouseX, mouseY);
    }

    /**
     * Sets the dragging state of the slider.
     * @param dragging The new dragging state
     */
    public void setDragging(boolean dragging) {
        this.isDragging = dragging;
    }

    /**
     * Returns whether the slider is currently being dragged.
     * @return true if the slider is being dragged, false otherwise
     */
    public boolean isDragging() {
        return isDragging;
    }

    /**
     * Updates the slider's value based on the mouse position.
     * Clamps the value between minValue and maxValue.
     * @param mouseX The x-coordinate of the mouse
     */
    public void updateValue(int mouseX) {
        if (mouseX < x) {
            value = minValue;
        } else if (mouseX > x + width) {
            value = maxValue;
        } else {
            float percentage = (float)(mouseX - x) / width;
            value = minValue + (int)(percentage * (maxValue - minValue));
        }
        updateHandlePosition();
    }

    /**
     * Returns the current value of the slider.
     * @return The current value between minValue and maxValue
     */
    public int getValue() {
        return value;
    }
} 