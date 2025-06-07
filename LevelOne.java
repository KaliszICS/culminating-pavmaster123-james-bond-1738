public class LevelOne extends Level{
    public LevelOne(){
        super();
        initialiseLevel();
    }

    protected void initialiseLevel(){
        for(int i = 0; i < 500; i++){
            Thing thing = new Thing(new Position(20 + ((double) i)  * (0.5 + Math.random()), ((double) i) * 0.5+1),2, 0.1);
            this.space.things.add(thing);
        }
    }
}