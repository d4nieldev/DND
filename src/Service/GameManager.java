package Service;

import Business.MessageCallback;
import Business.Player;

import java.util.List;

public class GameManager {
    private List<LevelManager> levelManagers;
    private Player player;
    private boolean playNextLevel;
    private MessageCallback messageCallback;

    public GameManager(int playerSelection, String pathToLevels, MessageCallback messageCallback){
        TileFactory tf = new TileFactory(messageCallback);
        player = tf.producePlayer(playerSelection);
        levelManagers = LevelParser.produceLevels(pathToLevels, player, messageCallback);
        playNextLevel = true;
        this.messageCallback = messageCallback;
    }

    public void playGame(){
        for (LevelManager lm : levelManagers){
            if(playNextLevel) {
                lm.initialize(player);
                lm.setMessageCallback(messageCallback);
                boolean playerSurvived = lm.playGame();
                if (!playerSurvived) playNextLevel = false;
            }
        }
        if(playNextLevel)
            messageCallback.send("You Won!");
        else
            messageCallback.send("You Died.");
    }
}
