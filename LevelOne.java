import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 * The first Level of the Game?
 * @author ???
 */
public class LevelOne extends Level{
    Thing floor;
    KdTree spikes;

    private static final Color BACKGROUND_COLOUR = new Color(19, 152, 236);
    private static final Color FLOOR_COLOUR = new Color(71, 184, 88);
    private static final Color SPIKE_COLOUR = new Color(236, 103, 19);
    private static final Color HITBOX_COLOUR = new Color(255, 0, 0);

    public LevelOne(){
        super();
        this.player.getPosition().setX(0);
        this.player.getPosition().setY(2);
        this.camera.setZoom(2.5);
        initialiseLevel();
    }

    private MovingThing door;

    protected void initialiseLevel(){
        floor = new Thing(new Position(0, -5), 1000, 10, FLOOR_COLOUR);
        Save.load(this, "./levels/level1.txt");
        this.door = new MovingThing(new Position(64, 2), 0.3, 4, new Color(92, 64, 51));
        this.space.things.add(this.door);
    }

    protected void loadSpikes(ArrayList<Thing> spikes){
        this.space.spikeArray = spikes;
        this.spikes = new KdTree(this.space.spikeArray);
    }

    private void endLevel(){
        GameState.levelComplete();
    }

    public void buttonPressed(int buttonID){
        switch(buttonID){
            case 1: // move door up
                this.door.moveTo(new Position(this.door.getPosition().getX(), this.door.getPosition().getY()+2));
                break;
            case 2: // end level
                endLevel();
            default:
                break;
        }
    }

    public void restart(){
        this.player.getPosition().setX(0);
        this.player.getPosition().setY(2);
        this.player.respawn();
        for(GameButton button : this.space.buttons){
            button.unpress();
        }
        this.camera.moveToPlayer();
        this.door.reset();
    }

    public void update(){
        this.space.update();
        this.door.update();
        for(Thing thing : this.spikes.search(this.player.getPosition(), 10)){
            if(this.player.collide(thing)){
                restart();
                break;
            }
        }
    }

    private void drawSpike(Graphics2D g, Position position, double sizeX, double sizeY, int width, int height){
        g.setColor(SPIKE_COLOUR);
        int[] x = new int[]{
            width/2 + this.camera.relativeToCameraX(position.getX() - sizeX/2), 
            width/2 + this.camera.relativeToCameraX(position.getX()), 
            width/2 + this.camera.relativeToCameraX(position.getX() + sizeX/2)
        };
        int[] y = new int[]{
            height/2 - this.camera.relativeToCameraY(position.getY() - sizeY/2), 
            height/2 - this.camera.relativeToCameraY(position.getY() + sizeY/2), 
            height/2 - this.camera.relativeToCameraY(position.getY() - sizeY/2)
        };
        g.fill(new Polygon(x, y, 3));
        g.setColor(BACKGROUND_COLOUR);
    }

    private void drawSpike(Graphics2D g, Position position, int width, int height){
        drawSpike(g, position, 1, 1, width, height);
    }

    private void drawButton(Graphics2D g, GameButton button, int width, int height){
        drawThing(g, button.getButton(), width, height); // draw button first
        drawThing(g, button.getFoundation(), width, height);
    }

    public void render(Graphics2D g, int width, int height){
        double zoom = this.camera.getZoom();
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        drawThing(g, this.floor, width, height);
        for(Thing thing : this.space.things){
            drawThing(g, thing, width, height);
        }
        for(Thing thing : this.space.spikeArray){
            drawSpike(g, thing.getPosition(), width, height);
        }
        for(GameButton button : this.space.buttons){
            drawButton(g, button, width, height);
        }
        drawThing(g, this.player, width, height);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.WHITE);
        g.drawString(this.camera.getLocation(), 50, 50);
        g.drawString(this.player.getLocation(), 50, 100);
        g.drawString(String.format("Zoom: %.1f", zoom), 50, 150);
        g.drawString("Feedback: " + feedback, 50, 200);
    }
}