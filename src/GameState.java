/**
 * Manages the game's state and screen transitions and handles the switching between them.\
 * @author Levon Alexanian
 */
public class GameState{
    /**
     * Enum representing the different screens in the game.
     */
    public enum Screen{
        /** The main menu screen */
        MAIN_MENU,
        /** The level selection screen */
        LEVEL_SELECT,
        /** The settings menu screen */
        SETTINGS,
        /** The credits screen */
        CREDITS,
        /** The active gameplay screen */
        GAME
    }

    private static Screen currentScreen;
    private static GameWindow window;
    private static Level levelScreen;
    private final static MainMenu mainMenu = new MainMenu();
    private final static LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
    private final static SettingsMenu settingsMenu = new SettingsMenu();
    private final static CreditsScreen creditsScreen = new CreditsScreen();

    /**
     * Initializes the game state with the main window and level screen.
     * Sets the initial screen to the main menu.
     * @param gameWindow The main game window
     * @param level The level screen instance
     */
    public static void initialise(GameWindow gameWindow, Level level){
        window = gameWindow;
        levelScreen = level;
        setCurrentScreen(Screen.MAIN_MENU);
    }
    
    /**
     * Updates the current game state.
     * If the current screen is the game screen, updates the level.
     */
    public static void update(){
        if(currentScreen == Screen.GAME){
            levelScreen.update();
        }
    }

    /**
     * Changes the current screen and updates the window's display.
     * @param screen The screen to switch to
     */
    public static void setCurrentScreen(Screen screen){
        currentScreen = screen;
        window.setDisplay(getCurrentScreen());
    }

    /**
     * Returns the current screen's displayable object.
     * @return The Displayable object for the current screen
     */
    public static Displayable getCurrentScreen(){
        switch(currentScreen){
            case MAIN_MENU:
                return mainMenu;
            case LEVEL_SELECT:
                return levelSelectMenu;
            case SETTINGS:
                return settingsMenu;
            case CREDITS:
                return creditsScreen;
            case GAME:
                return levelScreen;
            default:
                return mainMenu;
        }
    }

    /**
     * Sets the current level screen.
     * @param level The new level screen instance
     */
    public static void setLevel(Level level){
        levelScreen = level;
    }

    /**
     * Handles level completion by displaying a completion message.
     * Shows a black screen with "Level Complete!" text.
     */
    public static void levelComplete(){
        window.setDisplay(new BlackScreenWithText("Level Complete!"));
        long start = System.currentTimeMillis();
        //while(System.currentTimeMillis() - start < 1000);
    }
} 
