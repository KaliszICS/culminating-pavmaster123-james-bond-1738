import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A Save Utility Class. Can read and write to game saves and loads Levels.
 * @author Pavarasan Karunainathan
 */
public class Save{
    private Save(){}
    public static void save(String toSaveFile, Level level){
        
    }

    /**
     * Loads data on Things (things, spikes, buttons) into a Level from a File.
     * @param level The level to load into.
     * @param levelFile The level filename to load from.
     */
    public static void load(Level level, String levelFile){
        BufferedReader buffer;
        try{
            buffer = new BufferedReader(new FileReader(levelFile));
            String line;
            ArrayList<Thing> things = new ArrayList<Thing>();
            ArrayList<Thing> spikes = new ArrayList<Thing>();
            ArrayList<GameButton> buttons = new ArrayList<GameButton>();
            while((line = buffer.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line);
                String type = tokenizer.nextToken();
                System.out.println(type);
                if(type.equals("THING") || type.equals("SPIKE")){
                    double x = Double.parseDouble(tokenizer.nextToken());
                    double y = Double.parseDouble(tokenizer.nextToken());
                    double sizeX = Double.parseDouble(tokenizer.nextToken());
                    double sizeY = Double.parseDouble(tokenizer.nextToken());
                    int r = Integer.parseInt(tokenizer.nextToken());
                    int g = Integer.parseInt(tokenizer.nextToken());
                    int b = Integer.parseInt(tokenizer.nextToken());
                    Thing item = new Thing(new Position(x, y), sizeX, sizeY, new Color(r, g, b));
                    if(type.equals("THING")){
                        things.add(item);
                    }else{
                        spikes.add(item);
                    }
                }else if(type.equals("BUTTON")){
                    double x = Double.parseDouble(tokenizer.nextToken());
                    double y = Double.parseDouble(tokenizer.nextToken());
                    int buttonID = Integer.parseInt(tokenizer.nextToken());
                    GameButton button = new GameButton(new Position(x, y), level, buttonID);
                    buttons.add(button);
                }
            }
            level.loadThings(things);
            level.loadSpikes(spikes);
            level.loadButtons(buttons);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
