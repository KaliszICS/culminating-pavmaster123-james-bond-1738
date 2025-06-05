public class Game{
    private GameWindow window;
    private Player player;
    private Space space;
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
                space.update();
                start = now;
                update++;
            }
            if(System.currentTimeMillis() - seconds > 1000){
                window.setFeedback(String.valueOf(update));
                seconds = System.currentTimeMillis();
                update = 0;
            }
        }
    }

    public void play(){
        System.out.println("START!");
        this.player = new Player(new Position(0, 0), 1, 1);
        Camera camera = new Camera(this.player);
        this.space = new Space(this.player, camera);
        this.level = new Level(this.space);
        this.window = new GameWindow(this.level);
        for(int i = 0; i < 500; i++){
            Thing thing = new Thing(new Position(20 + ((double) i) * (0.5 + Math.random()), ((double) i) * 0.5),2 , 0.1); // 2 0.1
            //Thing thing = new Thing(new Position(1 + ((double) i) * (0.5 + Math.random()) + 1, ((double) i) * 0.5 + 10),2 , 2);
            space.things.add(thing);
        }
        
        startGame();
    }

    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }
}