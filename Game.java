public class Game{
    private GameWindow window;
    private Level level;
    public static final double TICKS_PER_SECOND = 60;
    
    public void startGame(){
        long start = System.nanoTime();
        long seconds = System.currentTimeMillis();
        int update = 0;

        while(true){
            long now = System.nanoTime();
            if((double)(now - start)/1000000000.0 > 1.0/TICKS_PER_SECOND){
                window.repaint();
                GameState.update();
                start = now;
                update++;
            }
            if(System.currentTimeMillis() - seconds > 1000){
                //window.setFeedback(String.valueOf(update));
                seconds = System.currentTimeMillis();
                update = 0;
            }
        }
    }

    public void play(){
        System.out.println("START!");
        this.window = new GameWindow();
        this.level = new LevelOne();
        GameState.initialise(this.window, this.level);
        startGame();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }
}