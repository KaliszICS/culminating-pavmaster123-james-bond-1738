import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.awt.GradientPaint;

/**
 * A menu screen that allows players to select different game levels.
 * @author Levon Alexanian
 */
public class LevelSelectMenu implements Displayable{
    private Button[] levelButtons;
    private Button backButton;
    private static final int NUM_LEVELS = 5;

    /**
     * Renders the level selection menu with a gradient background, title,
     * level selection buttons, and a back button.
     * @param g The Graphics2D context to render on
     * @param width The width of the display area
     * @param height The height of the display area
     */
    @Override
    public void render(Graphics2D g, int width, int height) {
        int buttonWidth = width / 4;
        int buttonHeight = height / 20;
        int buttonSpacing = height / 40;
        int startY = height / 4;

        // Create level buttons
        levelButtons = new Button[NUM_LEVELS];
        for (int i = 0; i < NUM_LEVELS; i++) {
            levelButtons[i] = new Button("Level " + (i + 1), 
                width/2 - buttonWidth/2, 
                startY + (buttonHeight + buttonSpacing) * i,
                buttonWidth, 
                buttonHeight);
        }

        // Create back button
        backButton = new Button("Back", 
            width/2 - buttonWidth/2, 
            startY + (buttonHeight + buttonSpacing) * (NUM_LEVELS + 1),
            buttonWidth, 
            buttonHeight);
        // Draw background
        GradientPaint backgroundGradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, height, new Color(135, 206, 235));
        g.setPaint(backgroundGradient);
        g.fillRect(0, 0, width, height);

        // Draw title
        g.setFont(new Font("Broadway", Font.BOLD, 48));
        String title = "Select Level";
        g.setColor(Color.WHITE);
        g.drawString(title, width/2 - g.getFontMetrics().stringWidth(title)/2, height/6);

        // Draw buttons
        for (Button button : levelButtons) {
            button.render(g);
        }
        backButton.render(g);
    }

    /**
     * Handles mouse click events on level buttons and the back button.
     * When a level button is clicked, it loads the corresponding level.
     * When the back button is clicked, it returns to the main menu.
     * @param e The MouseEvent containing information about the click
     */
    public void mouseClicked(MouseEvent e){
        for(int i = 0; i < NUM_LEVELS; i++){
            if(levelButtons[i].checkHovered(e.getX(), e.getY())){
                switch(i){
                    case 0:
                        GameState.setLevel(new LevelOne());
                        break;
                    case 1:
                        GameState.setLevel(new LevelTwo());
                        break;
                    case 2:
                        GameState.setLevel(new LevelThree());
                        break;
                    default:
                        break;
                }
                GameState.setCurrentScreen(GameState.Screen.GAME);
                System.out.println("Level " + (i + 1) + " selected");
                return;
            }
        }
        
        if (backButton.checkHovered(e.getX(), e.getY())) {
            GameState.setCurrentScreen(GameState.Screen.MAIN_MENU);
        }
    }

    /**
     * Handles mouse movement events to update button hover states.
     * Updates the hover state for all level buttons and the back button.
     * @param e The MouseEvent containing information about the mouse movement
     */
    public void mouseMoved(MouseEvent e) {
        for (Button button : levelButtons) {
            boolean isHovered = button.checkHovered(e.getX(), e.getY());
            if (button.isHovered() != isHovered) {
                button.setHovered(isHovered);
            }
        }
        
        boolean backHovered = backButton.checkHovered(e.getX(), e.getY());
        if(backButton.isHovered() != backHovered) {
            backButton.setHovered(backHovered);
        }
    }


    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
} 