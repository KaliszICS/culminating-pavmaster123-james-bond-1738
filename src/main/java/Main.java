import Datatype.Character;
import Datatype.Vector;
import Services.Run;
public class Main {
    public static void main(String[] args) {
        
        Run game = new Run();
        // Create a controllable character at (2,3,2)
        Character player = new Character();
        player.position = new Vector(5,10,5);
        player.size = new Vector(1,1,1);
        player.anchored = false;
        player.elasticity = 0;
        // Replace the first instance in the world with the player
        game.world.instances.add(player);
        game.world.camera.subject = player;
        
        game.RenderStepped.addListener((dt) -> {
            game.world.camera.update();
        });

        player.connectToGame(game);

        game.init();
    }
}
