import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;

/**
 * The main menu screen of the game.
 * @author Levon Alexanian
 */
public class MainMenu implements Displayable{
    private Button playButton;
    private Button settingsButton;
    private Button creditsButton;

    /**
     * Renders the main menu with a nature-themed background and interactive buttons.
     * Creates a sky gradient, grass, sun, and initializes the menu buttons if they haven't been created.
     * Displays the game title and renders all interactive buttons.
     * @param g The Graphics2D context to render on
     * @param width The width of the display area
     * @param height The height of the display area
     */
    @Override
    public void render(Graphics2D g, int width, int height){
        // Draw nature-themed background
        GradientPaint skyGradient = new GradientPaint(0, 0, new Color(135, 206, 235), 0, height, new Color(176, 224, 230));
        g.setPaint(skyGradient);
        g.fillRect(0, 0, width, height);

        // Draw grass
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, height * 2/3, width, height/3);

        // Draw sun
        g.setColor(new Color(255, 255, 0));
        g.fillOval(width - 100, 50, 80, 80);

        // Initialize buttons if they haven't been yet
        if (playButton == null) {
            int buttonWidth = width / 4;
            int buttonHeight = height / 20;
            int buttonSpacing = height / 40;
            int startY = height / 2;

            playButton = new Button("Play", width/2 - buttonWidth/2, startY, buttonWidth, buttonHeight);
            settingsButton = new Button("Settings", width/2 - buttonWidth/2, startY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);
            creditsButton = new Button("Credits", width/2 - buttonWidth/2, startY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight);
        }

        // Draw title
        g.setFont(new Font("Broadway", Font.BOLD, 96));
        String title = "Super \n Kalisz World";
        Rectangle2D titleBounds = g.getFontMetrics().getStringBounds(title, g);
        g.setColor(Color.WHITE);
        g.drawString(title, (int)(width/2 - titleBounds.getWidth()/2), height/4);

        // Draw buttons
        playButton.render(g);
        settingsButton.render(g);
        creditsButton.render(g);
    }

    /**
     * Handles mouse click events on menu buttons.
     * Navigates to different screens based on which button is clicked:
     * - Play button: Level Select screen
     * - Settings button: Settings screen
     * - Credits button: Credits screen
     *
     * @param e The MouseEvent containing information about the click
     */
    public void mouseClicked(MouseEvent e){
        System.out.println(playButton);
        if(playButton != null){
            if(playButton.checkHovered(e.getX(), e.getY())){
                GameState.setCurrentScreen(GameState.Screen.LEVEL_SELECT);
            }else if(settingsButton.checkHovered(e.getX(), e.getY())){
                GameState.setCurrentScreen(GameState.Screen.SETTINGS);
            }else if(creditsButton.checkHovered(e.getX(), e.getY())){
                GameState.setCurrentScreen(GameState.Screen.CREDITS);
            }
        }
    }

    /**
     * Handles mouse movement events to update button hover states.
     * Updates the hover state for all menu buttons (Play, Settings, Credits)
     * based on the current mouse position.
     *
     * @param e The MouseEvent containing information about the mouse movement
     */
    public void mouseMoved(MouseEvent e){
        if(playButton != null){
            boolean playHovered = playButton.checkHovered(e.getX(), e.getY());
            boolean settingsHovered = settingsButton.checkHovered(e.getX(), e.getY());
            boolean creditsHovered = creditsButton.checkHovered(e.getX(), e.getY());
            
            if(playButton.isHovered() != playHovered){
                playButton.setHovered(playHovered);
            }
            if(settingsButton.isHovered() != settingsHovered){
                settingsButton.setHovered(settingsHovered);
            }
            if(creditsButton.isHovered() != creditsHovered){
                creditsButton.setHovered(creditsHovered);
            }
        }
    }

    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}