package game.menu;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import game.Displayable;
import game.GameState;
import game.KeyBindings;

import java.awt.GradientPaint;

/**
 * A settings menu screen that allows players to customize game settings.
 * @author Levon Alexanian
 */
public class SettingsMenu extends MouseAdapter implements Displayable, KeyListener{
    private Button backButton;
    private Slider sfxVolumeSlider;
    private Slider musicVolumeSlider;

    // Key binding buttons
    private Button jumpKeyButton;
    private Button leftKeyButton;
    private Button rightKeyButton;
    private Button waitingForKeyButton = null;

    /**
     * Constructs a new SettingsMenu with default settings.
     * Initializes volume sliders and all control buttons with default positions.
     */
    public SettingsMenu() {
        // Initialize sliders with default values
        sfxVolumeSlider = new Slider("SFX Volume", 0, 0, 0, 0, 0, 100, 50);
        musicVolumeSlider = new Slider("Music Volume", 0, 0, 0, 0, 0, 100, 50);
        
        // Initialize buttons with default positions (will be updated in render)
        int defaultWidth = 200;
        int defaultHeight = 40;
        int defaultX = 0;
        int defaultY = 0;
        
        jumpKeyButton = new Button("Jump: " + KeyBindings.getKeyName(KeyBindings.getJumpKey()), 
            defaultX, defaultY, defaultWidth, defaultHeight);
        
        leftKeyButton = new Button("Left: " + KeyBindings.getKeyName(KeyBindings.getLeftKey()), 
            defaultX, defaultY, defaultWidth, defaultHeight);
        
        rightKeyButton = new Button("Right: " + KeyBindings.getKeyName(KeyBindings.getRightKey()), 
            defaultX, defaultY, defaultWidth, defaultHeight);
        
        backButton = new Button("Back", defaultX, defaultY, defaultWidth, defaultHeight);
    }

    /**
     * Renders the settings menu with a gradient background, title,
     * volume sliders, key binding buttons, character selection, and back button.
     * Updates the positions and sizes of all UI elements based on the window dimensions.
     * @param g The Graphics2D context to render on
     * @param width The width of the display area
     * @param height The height of the display area
     */
    @Override
    public void render(Graphics2D g, int width, int height) {
        int sliderWidth = width / 2;
        int sliderHeight = height / 20;
        int buttonSpacing = height / 40;
        int startY = height / 4;
        int buttonWidth = width / 4;
        int buttonHeight = height / 20;

        // Update slider positions and sizes
        sfxVolumeSlider.setPosition(width/2 - sliderWidth/2, startY);
        sfxVolumeSlider.setSize(sliderWidth, sliderHeight);
        musicVolumeSlider.setPosition(width/2 - sliderWidth/2, startY + sliderHeight + buttonSpacing);
        musicVolumeSlider.setSize(sliderWidth, sliderHeight);

        // Update button positions
        jumpKeyButton.setPosition(width/2 - buttonWidth/2, startY + (buttonHeight + buttonSpacing) * 2);
        jumpKeyButton.setSize(buttonWidth, buttonHeight);
        
        leftKeyButton.setPosition(width/2 - buttonWidth/2, startY + (buttonHeight + buttonSpacing) * 3);
        leftKeyButton.setSize(buttonWidth, buttonHeight);
        
        rightKeyButton.setPosition(width/2 - buttonWidth/2, startY + (buttonHeight + buttonSpacing) * 4);
        rightKeyButton.setSize(buttonWidth, buttonHeight);
        
        backButton.setPosition(width/2 - buttonWidth/2, startY + (buttonHeight + buttonSpacing) * 6);
        backButton.setSize(buttonWidth, buttonHeight);

        // Draw background
        GradientPaint backgroundGradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, height, new Color(135, 206, 235));
        g.setPaint(backgroundGradient);
        g.fillRect(0, 0, width, height);

        // Draw title
        g.setFont(new Font("Broadway", Font.BOLD, 48));
        String title = "Settings";
        g.setColor(Color.WHITE);
        g.drawString(title, width/2 - g.getFontMetrics().stringWidth(title)/2, height/6);

        // Draw sliders and buttons
        sfxVolumeSlider.render(g);
        musicVolumeSlider.render(g);
        jumpKeyButton.render(g);
        leftKeyButton.render(g);
        rightKeyButton.render(g);
        backButton.render(g);
    }

    /**
     * Handles mouse click events on settings buttons.
     * Manages key binding button states and character selection.
     * Returns to main menu when back button is clicked.
     * @param e The MouseEvent containing information about the click
     */
    public void mouseClicked(MouseEvent e){
        if(waitingForKeyButton != null) {
            return; // Ignore clicks while waiting for a key
        }

        if(jumpKeyButton.checkHovered(e.getX(), e.getY())){
            waitingForKeyButton = jumpKeyButton;
            jumpKeyButton.setText("Press any key...");
            e.consume(); // Prevent the click from being processed by other components
        } else if(leftKeyButton.checkHovered(e.getX(), e.getY())){
            waitingForKeyButton = leftKeyButton;
            leftKeyButton.setText("Press any key...");
            e.consume();
        } else if(rightKeyButton.checkHovered(e.getX(), e.getY())){
            waitingForKeyButton = rightKeyButton;
            rightKeyButton.setText("Press any key...");
            e.consume();
        } else if(backButton.checkHovered(e.getX(), e.getY())){
            GameState.setCurrentScreen(GameState.Screen.MAIN_MENU);
            e.consume();
        }
    }

    /**
     * Handles key press events for key binding configuration.
     * Updates the selected key binding when a new key is pressed.
     * @param e The KeyEvent containing information about the key press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(waitingForKeyButton != null) {
            int keyCode = e.getKeyCode();
            if(waitingForKeyButton == jumpKeyButton) {
                KeyBindings.setJumpKey(keyCode);
                jumpKeyButton.setText("Jump: " + KeyBindings.getKeyName(keyCode));
            } else if(waitingForKeyButton == leftKeyButton) {
                KeyBindings.setLeftKey(keyCode);
                leftKeyButton.setText("Left: " + KeyBindings.getKeyName(keyCode));
            } else if(waitingForKeyButton == rightKeyButton) {
                KeyBindings.setRightKey(keyCode);
                rightKeyButton.setText("Right: " + KeyBindings.getKeyName(keyCode));
            }
            waitingForKeyButton = null;
            e.consume(); // Prevent the key from being processed by other components
        }
    }

    /**
     * Handles mouse movement events to update button hover states.
     * Updates hover states for all interactive elements.
     * @param e The MouseEvent containing information about the mouse movement
     */
    public void mouseMoved(MouseEvent e){
        if(waitingForKeyButton != null) {
            return; // Ignore hover effects while waiting for a key
        }

        boolean jumpHovered = jumpKeyButton.checkHovered(e.getX(), e.getY());
        boolean leftHovered = leftKeyButton.checkHovered(e.getX(), e.getY());
        boolean rightHovered = rightKeyButton.checkHovered(e.getX(), e.getY());
        boolean backHovered = backButton.checkHovered(e.getX(), e.getY());

        if(jumpKeyButton.isHovered() != jumpHovered){
            jumpKeyButton.setHovered(jumpHovered);
        }
        if(leftKeyButton.isHovered() != leftHovered){
            leftKeyButton.setHovered(leftHovered);
        }
        if(rightKeyButton.isHovered() != rightHovered){
            rightKeyButton.setHovered(rightHovered);
        }
        if(backButton.isHovered() != backHovered){
            backButton.setHovered(backHovered);
        }
    }

    /**
     * Handles mouse press events for volume sliders.
     * Initiates dragging state for the sliders when clicked.
     * @param e The MouseEvent containing information about the mouse press
     */
    public void mousePressed(MouseEvent e) {
        if (sfxVolumeSlider.checkHovered(e.getX(), e.getY())) {
            sfxVolumeSlider.setDragging(true);
            sfxVolumeSlider.updateValue(e.getX());
        } else if (musicVolumeSlider.checkHovered(e.getX(), e.getY())) {
            musicVolumeSlider.setDragging(true);
            musicVolumeSlider.updateValue(e.getX());
        }
    }

    /**
     * Handles mouse release events for volume sliders.
     * Ends dragging state for all sliders.
     * @param e The MouseEvent containing information about the mouse release
     */
    public void mouseReleased(MouseEvent e) {
        sfxVolumeSlider.setDragging(false);
        musicVolumeSlider.setDragging(false);
    }

    /**
     * Handles mouse drag events for volume sliders.
     * Updates slider values based on mouse position during drag.
     * @param e The MouseEvent containing information about the mouse drag
     */
    public void mouseDragged(MouseEvent e) {
        if (sfxVolumeSlider.checkHovered(e.getX(), e.getY()) || sfxVolumeSlider.isDragging()) {
            sfxVolumeSlider.updateValue(e.getX());
        }
        if (musicVolumeSlider.checkHovered(e.getX(), e.getY()) || musicVolumeSlider.isDragging()) {
            musicVolumeSlider.updateValue(e.getX());
        }
    }

    /**
     * Detects if a key was released. Does nothing.
     * @param e The KeyEvent detected.
     */
    @Override
    public void keyReleased(KeyEvent e){}
    
    /**
     * Detects if a key was typed. Does nothing.
     * @param e The KeyEvent detected.
     */
    @Override
    public void keyTyped(KeyEvent e){}
} 
