import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.GradientPaint;

public class SettingsMenu implements Displayable {
    private Button backButton;
    private Button volumeUpButton;
    private Button volumeDownButton;
    private Button CharacterButton;
    private int volume = 50; // 0-100
    private String Character = "Kalisz"; 

    public SettingsMenu(int width, int height) {
        int buttonWidth = width / 4;
        int buttonHeight = height / 20;
        int buttonSpacing = height / 40;
        int startY = height / 4;

        // Create volume control buttons
        volumeUpButton = new Button("Volume +", 
            width/2 - buttonWidth/2, 
            startY,
            buttonWidth, 
            buttonHeight);
            
        volumeDownButton = new Button("Volume -", 
            width/2 - buttonWidth/2, 
            startY + buttonHeight + buttonSpacing,
            buttonWidth, 
            buttonHeight);

        // Create difficulty button
        CharacterButton = new Button("Character: " + Character, 
            width/2 - buttonWidth/2, 
            startY + (buttonHeight + buttonSpacing) * 2,
            buttonWidth, 
            buttonHeight);

        // Create back button
        backButton = new Button("Back", 
            width/2 - buttonWidth/2, 
            startY + (buttonHeight + buttonSpacing) * 4,
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
        String title = "Settings";
        g.setColor(Color.WHITE);
        g.drawString(title, width/2 - g.getFontMetrics().stringWidth(title)/2, height/6);

        // Draw volume level
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        String volumeText = "Volume: " + volume + "%";
        g.drawString(volumeText, width/2 - g.getFontMetrics().stringWidth(volumeText)/2, height/3);

        // Draw buttons
        volumeUpButton.render(g);
        volumeDownButton.render(g);
        CharacterButton.render(g);
        backButton.render(g);
    }

    public void mouseClicked(MouseEvent e) {
        if (volumeUpButton.checkHovered(e.getX(), e.getY())) {
            volume = Math.min(100, volume + 10);
            GameState.getWindow().repaint();
        } else if (volumeDownButton.checkHovered(e.getX(), e.getY())) {
            volume = Math.max(0, volume - 10);
            GameState.getWindow().repaint();
        } else if (CharacterButton.checkHovered(e.getX(), e.getY())) {
            switch (Character) {
                case "Kalisz":
                    Character = "Kalisz";
                    break;
                case "Cheung":
                    Character = "Cheung";
                    break;
                case "Marr":
                    Character = "Marr";
                    break;
                case "Ong":
                    Character = "Ong";
                    break;
            }
            CharacterButton.setText("Character: " + Character);
            GameState.getWindow().repaint();
        } else if (backButton.checkHovered(e.getX(), e.getY())) {
            GameState.setCurrentScreen(GameState.Screen.MAIN_MENU);
        }
    }

    public void mouseMoved(MouseEvent e) {
        boolean anyHoverChanged = false;
        
        boolean volumeUpHovered = volumeUpButton.checkHovered(e.getX(), e.getY());
        if (volumeUpButton.isHovered() != volumeUpHovered) {
            volumeUpButton.setHovered(volumeUpHovered);
            anyHoverChanged = true;
        }
        
        boolean volumeDownHovered = volumeDownButton.checkHovered(e.getX(), e.getY());
        if (volumeDownButton.isHovered() != volumeDownHovered) {
            volumeDownButton.setHovered(volumeDownHovered);
            anyHoverChanged = true;
        }
        
        boolean CharacterHovered = CharacterButton.checkHovered(e.getX(), e.getY());
        if (CharacterButton.isHovered() != CharacterHovered) {
            CharacterButton.setHovered(CharacterHovered);
            anyHoverChanged = true;
        }
        
        boolean backHovered = backButton.checkHovered(e.getX(), e.getY());
        if (backButton.isHovered() != backHovered) {
            backButton.setHovered(backHovered);
            anyHoverChanged = true;
        }
        
        if (anyHoverChanged) {
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