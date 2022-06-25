package Service;

import Business.Player;

import java.util.List;

public class GameManager {
    private List<LevelManager> levelManagers;
    private Player player;

    public GameManager(int playerSelection, String pathToLevels){
        TileFactory tf = new TileFactory();
        player = tf.producePlayer(playerSelection);
        levelManagers = LevelParser.produceLevels(pathToLevels, player);
    }

    public void playGame(){
        for (LevelManager lm : levelManagers){
            lm.initialize(player);
            lm.playGame();
        }
    }
}
