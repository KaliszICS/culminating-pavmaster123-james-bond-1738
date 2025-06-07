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
        CollisionUpdate collision = new CollisionUpdate(1, CollisionUpdate.UPDATE_BOTH);
        for(int i = 0; i < things.size(); i++){
            this.player.collide(things.get(i));
            CollisionUpdate update = this.player.moveCollide(things.get(i));
            collision.combine(update);
        }
        if(collision.getUpdateType() == CollisionUpdate.UPDATE_BOTH){
            this.player.update();
        }else if(collision.getUpdateType() == CollisionUpdate.UPDATE_X){
            this.player.updateXOnTime(collision.getCollisionTime());
        }else if(collision.getUpdateType() == CollisionUpdate.UPDATE_Y){
            this.player.updateYOnTime(collision.getCollisionTime());
        }
    }

    public Camera getCamera(){
        return this.camera;
    }

    public Player getPlayer(){
        return this.player;
    }
}