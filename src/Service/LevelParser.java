package Service;

import Business.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LevelParser {
    public static List<LevelManager> produceLevels(String pathToLevels, Player player){
        TileFactory tf = new TileFactory();
        File levelsDir = new File(pathToLevels);
        File[] levels = levelsDir.listFiles();
        List<LevelManager> levelManagers = new ArrayList<>();
        if(levels != null){
            for(File level : levels){
                int[] maxes = getBoardLimits(level);
                try (FileReader fr = new FileReader(level)){
                    BufferedReader br = new BufferedReader(fr);
                    Tile[][] tiles = new Tile[maxes[0]][maxes[1]];
                    List<Enemy> enemyList = new ArrayList<>();
                    Position initialPlayerPosition = null;
                    int row = 0;
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        for (int col = 0; col < line.length(); col++){
                            if(line.charAt(col) == '@') {
                                tiles[row][col] = player;
                                initialPlayerPosition = new Position(col, row);
                            }
                            else if (line.charAt(col) == '.' || line.charAt(col) == '#'){
                                tiles[row][col] = tf.produceTile(line.charAt(col));
                                tiles[row][col].initialize(new Position(col, row));
                            }
                            else {
                                Enemy enemy = tf.produceEnemy(line.charAt(col));
                                tiles[row][col] = enemy;
                                enemy.initialize(new Position(col, row));
                                enemyList.add(enemy);
                            }
                        }
                        row++;
                    }
                    levelManagers.add(new LevelManager(tiles, enemyList, initialPlayerPosition));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return levelManagers;
    }

    private static int[] getBoardLimits(File level){
        int maxLine = 0;
        int maxCol = 0;

        try(FileReader fr = new FileReader(level)) {
            String line;
            try (BufferedReader br = new BufferedReader(fr)) {
                while ((line = br.readLine()) != null) {
                    maxCol = line.length();
                    maxLine++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return new int[]{maxLine, maxCol};
    }
}
