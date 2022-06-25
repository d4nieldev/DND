package UI;
import Service.GameManager;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class CLI {
    static GameManager gameManager;

    public static void main(String[] args){
        // TODO: implement the main method
        int playerSelection = 1;

        String pathToLevels = System.getProperty("user.dir") + "\\levels";
        System.out.println(pathToLevels);
        gameManager = new GameManager(playerSelection, pathToLevels);
        gameManager.playGame();

//        File p = new File("levels");
//        System.out.println(p.getAbsolutePath());
//        System.out.println(Arrays.toString(p.listFiles()));
    }
}
