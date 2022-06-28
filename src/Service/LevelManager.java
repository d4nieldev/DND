package Service;

import Business.*;

import java.util.*;

public class LevelManager {
    private final Board board;
    private Player player;
    private final Position initialPlayerPosition;
    private MessageCallback messageCallback;

    public LevelManager(Tile[][] tiles, List<Enemy> enemyList, Position initialPlayerPosition){
        this.board = new Board(tiles, enemyList);
        this.initialPlayerPosition = initialPlayerPosition;
    }

    public void initialize(Player player){
        this.player = player;
        player.initialize(initialPlayerPosition);
        player.setMoveCallback(action -> {
            switch (action) {
                case (0) ->
                        // interact up
                        player.interact(board.get(player.getX(), player.getY() - 1));
                case (1) ->
                        // interact down
                        player.interact(board.get(player.getX(), player.getY() + 1));
                case (2) ->
                        // interact left
                        player.interact(board.get(player.getX() - 1, player.getY()));
                case (3) ->
                        // move right
                        player.interact(board.get(player.getX() + 1, player.getY()));
            }
        });
    }

    public void setMessageCallback(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
    }

    private Map<Character, Integer> getPlayerMoves(){
        Map<Character, Integer> playerMoves = new HashMap<>();
        playerMoves.put('w', 0); // interact up
        playerMoves.put('s', 1); // interact down
        playerMoves.put('a', 2); // interact left
        playerMoves.put('d', 3); // interact right

        return playerMoves;
    }

    public boolean playGame(){
        Map<Character, Integer> playerMoves = getPlayerMoves();

        Scanner scanner = new Scanner(System.in);
        while (!player.isDead() && !board.getEnemyList().isEmpty()){
            messageCallback.send(board.toString());
            messageCallback.send(player.describe());

            char playerMove = scanner.next().charAt(0);

            if (playerMoves.containsKey(playerMove))
                // move up, down, left or right
                player.interact(playerMoves.get(playerMove));
            else if (playerMove == 'e')
                // cast special ability
                player.abilityCast(board.getEnemyList());
            else if (playerMove == 'q')
                // do nothing
                player.onGameTick();

            for (Enemy e : board.getEnemyList())
                e.processStep(player);
        }

        if(!player.isDead())
            return true;
        return false;
    }
}
