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

    public static void initialise(GameWindow gameWindow, Level level){
        window = gameWindow;
        levelScreen = level;
        setCurrentScreen(Screen.GAME);
    }
    
    public static void update(){
        if(currentScreen == Screen.GAME){
            levelScreen.update();
        }
    }

    public static void setCurrentScreen(Screen screen){
        currentScreen = screen;
        window.setDisplay(getCurrentScreen());
    }

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
} 
