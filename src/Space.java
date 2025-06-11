import java.util.ArrayList;


/**
 * A Space for the Level to exist in. Has a Player, Camera and various Things.
 */
public class Space{
    private Camera camera;
    private Player player;
    public ArrayList<Thing> things;
    public ArrayList<Thing> spikeArray;
    public ArrayList<GameButton> buttons;

    /**
     * Constructs the Space.
     * @param player The Player in the Space. 
     * @param camera The Camera in the Space.
     */
    public Space(Player player, Camera camera){
        this.player = player;
        this.camera = camera;
        this.things = new ArrayList<Thing>();
        this.spikeArray = new ArrayList<Thing>();
        this.buttons = new ArrayList<GameButton>();
    }

    /**
     * Updates the space every game tick.
     */
    public void update(){
        CollisionUpdate collision = new CollisionUpdate(1, CollisionUpdate.UPDATE_BOTH);
        for(int i = 0; i < things.size(); i++){
            this.player.collide(things.get(i));
            CollisionUpdate update = this.player.moveCollide(things.get(i));
            collision.combine(update);
        }
        for(int i = 0; i < buttons.size(); i++){
            GameButton button = buttons.get(i);
            this.player.collide(button.getFoundation());
            CollisionUpdate update = this.player.moveCollide(button.getFoundation());
            collision.combine(update);
            this.player.collide(button.getButton());
            update = this.player.moveCollide(button.getButton());
            if(update.getUpdateType() == CollisionUpdate.UPDATE_X){
                button.press();
            }
            collision.combine(update);
            button.update();
        }

        if(collision.getUpdateType() == CollisionUpdate.UPDATE_BOTH){
            if(collision.getCollisionTimeX() == 1 && collision.getCollisionTimeY() == 1){
                this.player.update();
            }else{
                this.player.update(collision.getCollisionTimeX(), collision.getCollisionTimeY());
            }
        }else if(collision.getUpdateType() == CollisionUpdate.UPDATE_X){
            this.player.updateXOnTime(collision.getCollisionTimeX());
        }else if(collision.getUpdateType() == CollisionUpdate.UPDATE_Y){
            this.player.updateYOnTime(collision.getCollisionTimeY());
        }

        this.camera.update();
    }

    /**
     * Gets the Camera looking at the Space.
     * @return The Camera in the Space.
     */
    public Camera getCamera(){
        return this.camera;
    }

    /**
     * Gets the Player in the Space.
     * @return The Player in the Space.
     */
    public Player getPlayer(){
        return this.player;
    }
}