import java.util.ArrayList;

public class Space{
    private Camera camera;
    private Player player;
    public ArrayList<Thing> things;
    
    public Space(Player player, Camera camera){
        this.player = player;
        this.camera = camera;
        this.things = new ArrayList<Thing>();
    }

    public void update(){
        this.player.update();
        for(int i = 0; i < things.size(); i++){
            this.player.collide(things.get(i));
        }
    }

    public Camera getCamera(){
        return this.camera;
    }

    public Player getPlayer(){
        return this.player;
    }
}