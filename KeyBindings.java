import java.awt.event.KeyEvent;

/**
 * Manages the key bindings for player controls in the game.
 * Provides functionality to get, set, and retrieve the names of control keys.
 * @author Levon Alexanian
 */
public class KeyBindings {
    private static int jumpKey = KeyEvent.VK_W;
    private static int leftKey = KeyEvent.VK_A;
    private static int rightKey = KeyEvent.VK_D;

    /**
     * Returns the key code for the jump action.
     * @return The key code for jumping
     */
    public static int getJumpKey() {
        return jumpKey;
    }

    /**
     * Returns the key code for the left movement action.
     * @return The key code for moving left
     */
    public static int getLeftKey() {
        return leftKey;
    }

    /**
     * Returns the key code for the right movement action.
     * @return The key code for moving right
     */
    public static int getRightKey() {
        return rightKey;
    }

    /**
     * Sets a new key code for the jump action.
     * @param key The new key code to use for jumping
     */
    public static void setJumpKey(int key) {
        jumpKey = key;
    }

    /**
     * Sets a new key code for the left movement action.
     * @param key The new key code to use for moving left
     */
    public static void setLeftKey(int key) {
        leftKey = key;
    }

    /**
     * Sets a new key code for the right movement action.
     * @param key The new key code to use for moving right
     */
    public static void setRightKey(int key) {
        rightKey = key;
    }

    /**
     * Converts a key code to its human-readable name.
     * @param keyCode The key code to convert
     * @return The human-readable name of the key
     */
    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }
}