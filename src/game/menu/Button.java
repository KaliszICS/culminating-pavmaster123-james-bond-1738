package game.menu;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

/**
 * A customizable button that can be rendered on a graphics context.
 * The button supports hover effects, custom colors, and text display.
 * @author Levon Alexanian
 */
public class Button {
    private String text;
    private int x, y, width, height;
    private boolean hovered;
    private Color normalColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;
    private Rectangle bounds;

    private static Color DULL_GREY = new Color(105, 105, 105);
    private static Color DARK_GREY = new Color(47, 79, 79);

    /**
     * Constructs a new Button with specified text and dimensions.
     * @param text The text to display on the button
     * @param x The x-coordinate of the button's top-left corner
     * @param y The y-coordinate of the button's top-left corner
     * @param width The width of the button
     * @param height The height of the button
     */
    public Button(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hovered = false;
        this.normalColor = DULL_GREY;
        this.hoverColor = DARK_GREY;
        this.textColor = Color.WHITE;
        this.font = new Font("Arial", Font.BOLD, 20);
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Updates the position of the button.
     * @param x The new x-coordinate of the button's top-left corner
     * @param y The new y-coordinate of the button's top-left corner
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.bounds.setLocation(x, y);
    }

    /**
     * Updates the size of the button.
     * @param width The new width of the button
     * @param height The new height of the button
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.bounds.setSize(width, height);
    }

    /**
     * Renders the button on the specified graphics context.
     * Draws the button background, border, and centered text.
     * @param g The Graphics2D context to render the button on
     */
    public void render(Graphics2D g) {
        // Draw button background
        g.setColor(hovered ? hoverColor : normalColor);
        g.fillRoundRect(x, y, width, height, 10, 10);
        
        // Draw button border
        g.setColor(Color.WHITE);
        g.drawRoundRect(x, y, width, height, 10, 10);

        // Draw text
        g.setColor(textColor);
        g.setFont(font);
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(text, x + (width - textWidth) / 2, y + (height + textHeight) / 2 - 5);
    }

    /**
     * Updates the text displayed on the button.
     * @param text The new text to display
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Checks if the given mouse coordinates are within the button's bounds.
     * @param mouseX The x-coordinate of the mouse
     * @param mouseY The y-coordinate of the mouse
     * @return true if the mouse is over the button, false otherwise
     */
    public boolean checkHovered(int mouseX, int mouseY) {
        //System.out.println(bounds.contains(mouseX, mouseY));
        return bounds.contains(mouseX, mouseY);
    }

    /**
     * Returns whether the button is currently in a hovered state.
     * @return true if the button is hovered, false otherwise
     */
    public boolean isHovered() {
        return hovered;
    }

    /**
     * Sets the hover state of the button.
     * @param hovered The new hover state
     */
    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    /**
     * Returns the bounds of the button as a Rectangle.
     * @return The button's bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }
} 