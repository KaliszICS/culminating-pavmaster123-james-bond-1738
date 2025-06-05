import java.awt.Frame;
import java.awt.Canvas;

public class GameState {
    public enum Screen {
        MAIN_MENU,
        LEVEL_SELECT,
        SETTINGS,
        CREDITS,
        GAME
    }

    private static Screen currentScreen = Screen.MAIN_MENU;
    private static Frame window;
    private static Canvas canvas;
    private static MainMenu mainMenu;
    private static LevelSelectMenu levelSelectMenu;
    private static SettingsMenu settingsMenu;
    private static CreditsScreen creditsScreen;

    public static void initialize(Frame gameWindow, Canvas gameCanvas) {
        window = gameWindow;
        canvas = gameCanvas;
        
        // Initialize all screens
        mainMenu = new MainMenu();
        levelSelectMenu = new LevelSelectMenu(canvas.getWidth(), canvas.getHeight());
        settingsMenu = new SettingsMenu(canvas.getWidth(), canvas.getHeight());
        creditsScreen = new CreditsScreen(canvas.getWidth(), canvas.getHeight());
    }

    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
        window.repaint();
    }

    public static Displayable getCurrentScreen() {
        switch (currentScreen) {
            case MAIN_MENU:
                return mainMenu;
            case LEVEL_SELECT:
                return levelSelectMenu;
            case SETTINGS:
                return settingsMenu;
            case CREDITS:
                return creditsScreen;
            default:
                return mainMenu;
        }
    }

    public static Frame getWindow() {
        return window;
    }

    public static Canvas getCanvas() {
        return canvas;
    }
} 