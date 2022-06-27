package Service;

import Business.*;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class LevelManager {
    private Board board;
    private Player player;
    private Position initialPlayerPosition;

    public LevelManager(Tile[][] tiles, List<Enemy> enemyList, Position initialPlayerPosition){
        this.board = new Board(tiles, enemyList);
        this.initialPlayerPosition = initialPlayerPosition;
    }

    public void initialize(Player player){
        this.player = player;
        player.initialize(initialPlayerPosition);
    }

    public void playGame(){
        Scanner scanner = new Scanner(System.in);
        while (!player.isDead()){
            System.out.println(board);

            char playerMove = scanner.next().charAt(0);
            switch(playerMove){
                case ('w'):
                    // interact up
                    player.interact(board.get(player.getX(), player.getY() - 1));
                    break;
                case ('s'):
                    // interact down
                    player.interact(board.get(player.getX(), player.getY() + 1));
                    break;
                case ('a'):
                    // interact left
                    player.interact(board.get(player.getX() - 1, player.getY()));
                    break;
                case ('d'):
                    // move right
                    player.interact(board.get(player.getX() + 1, player.getY()));
                    break;
                case ('e'):
                    // cast special ability
                    player.abilityCast(board.getEnemyList());
                    break;
                case ('q'):
                    // pass
                    break;
            }

            for (Enemy e : board.getEnemyList())
                e.processStep(player);

        }
    }
}
