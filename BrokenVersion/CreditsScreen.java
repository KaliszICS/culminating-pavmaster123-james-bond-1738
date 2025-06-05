import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.GradientPaint;

public class CreditsScreen implements Displayable {
    private Button backButton;
    private String[] credits = {
        "Super Kalisz World",
        "",
        "Created by:",
        "Your Name",
        "",
        "Special Thanks to:",
        "My Family",
        "My Friends",
        "My Teachers",
        "",
        "© 2024 All Rights Reserved"
    };

    public CreditsScreen(int width, int height) {
        int buttonWidth = width / 4;
        int buttonHeight = height / 20;
        
        // Create back button at the bottom
        backButton = new Button("Back", 
            width/2 - buttonWidth/2, 
            height - buttonHeight - 20,
            buttonWidth, 
            buttonHeight);
    }

    @Override
    public void render(Graphics2D g, int width, int height) {
        // Draw background
        GradientPaint backgroundGradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, height, new Color(135, 206, 235));
        g.setPaint(backgroundGradient);
        g.fillRect(0, 0, width, height);

        // Draw title
        g.setFont(new Font("Broadway", Font.BOLD, 48));
        String title = "Credits";
        g.setColor(Color.WHITE);
        g.drawString(title, width/2 - g.getFontMetrics().stringWidth(title)/2, height/6);

        // Draw credits text
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        int y = height/4;
        for (String line : credits) {
            g.drawString(line, width/2 - g.getFontMetrics().stringWidth(line)/2, y);
            y += 30;
        }

        // Draw back button
        backButton.render(g);
    }

    public void mouseClicked(MouseEvent e) {
        if (backButton.checkHovered(e.getX(), e.getY())) {
            GameState.setCurrentScreen(GameState.Screen.MAIN_MENU);
        }
    }

    public void mouseMoved(MouseEvent e) {
        boolean backHovered = backButton.checkHovered(e.getX(), e.getY());
        if (backButton.isHovered() != backHovered) {
            backButton.setHovered(backHovered);
            GameState.getWindow().repaint();
        }
    }

    // Required interface methods
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
} 