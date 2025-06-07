import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class Button {
    private String text;
    private int x, y, width, height;
    private boolean hovered;
    private Color normalColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;
    private Rectangle bounds;

    public Button(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hovered = false;
        this.normalColor = new Color(105, 105, 105); // Dull Gray
        this.hoverColor = new Color(47, 79, 79); // Dark Gray
        this.textColor = Color.WHITE;
        this.font = new Font("Arial", Font.BOLD, 20);
        this.bounds = new Rectangle(x, y, width, height);
    }

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

    public void setText(String text) {
        this.text = text;
    }

    public boolean checkHovered(int mouseX, int mouseY) {
        //System.out.println(bounds.contains(mouseX, mouseY));
        return bounds.contains(mouseX, mouseY);
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public Rectangle getBounds() {
        return bounds;
    }
} 