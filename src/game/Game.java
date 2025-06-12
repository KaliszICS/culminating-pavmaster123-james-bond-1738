package game;

import game.menu.Save;

/**
 * The main Game class. Has a game loop to run the Game.
 * @author Pavarasan Karunainathan
 */
public class Game{
    private GameWindow window;
    public static final double TICKS_PER_SECOND = 60.0;
    public static final double NANOS_PER_SECOND = 1000000000.0;

    /**
     * Starts the Game.
     */
    public void startGame(){
        long start = System.nanoTime();
        while(true){
            long now = System.nanoTime();
            if((double)(now - start)/NANOS_PER_SECOND > 1.0/TICKS_PER_SECOND){
                window.repaint();
                GameState.update();
                start = now;
            }
        }
    }

    /**
     * Initializes the GameWindow and starts the Game.
     * Also loads any settings saved.
     */
    public void play(){
        System.out.println("START!");
        this.window = new GameWindow();
        Save.loadKeybindings();
        GameState.initialise(this.window);
        startGame();
    }

    /**
     * Creates a new Game and plays it.
     */
    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }
}



