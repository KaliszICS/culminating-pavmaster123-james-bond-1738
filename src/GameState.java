/**
 * The GameState class, handles the changing of displays in GameWindow.
 */
public class GameState{
    public enum Screen{
        MAIN_MENU,
        LEVEL_SELECT,
        SETTINGS,
        CREDITS,
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
     * Initializes the GameState.
     * @param gameWindow The GameWindow that will receive input from the GameState.
     */
    public static void initialise(GameWindow gameWindow){
        window = gameWindow;
        levelScreen = new LevelOne();
        setCurrentScreen(Screen.MAIN_MENU);
    }
    
    /**
     * If the current Screen is a Level, updates the level.
     */
    public static void update(){
        if(currentScreen == Screen.GAME){
            levelScreen.update();
        }
    }

    /**
     * Sets the currently viewed Screen to a given Screen.
     * @param screen The Screen to display.
     */
    public static void setCurrentScreen(Screen screen){
        currentScreen = screen;
        window.setDisplay(getCurrentScreen());
    }

    /**
     * Returns the Displayable that is currently being viewed.
     * @return The screen currently being viewed.
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
     * Sets the current Level.
     * @param level The Level to set to.
     */
    public static void setLevel(Level level){
        levelScreen = level;
    }

    /**
     * Is called after a level is complete.
     */
    public static void levelComplete(){
        setCurrentScreen(Screen.LEVEL_SELECT);
    }
} 
