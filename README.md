# About
This game is asingle-player multi-level version of a dungeons and dragons board game.
<br />
You are trapped in a dungeon, surrounded by enemies. 
<br />
Your goal is to fight your way through them and get to the next level of the dungeon. 
<br />
Once you complete all the levels, you win the game.

# Game Description
The game is played on a board similar to the board in Figure 1. 
<br />
The game board consists of a player, enemies of different types, walls and empty areas that both players and enemies can walk through.
<br />
In this board, the symbol '@' in green represents the player while the later red symbols 'B', 's', 'k' and 'M' represent the enemies that the player should fight. 
<br />
In addition, there are dots scattered along the paths, representing the free areas and # symbols that represent the walls. 
<br />
The game takes a path to a directory that containing indexed files via the command line argument (explained later). Each file represents a game level.

![image](https://user-images.githubusercontent.com/102467192/209524647-0c9ddeed-0568-4495-aa2e-b2170eef7a15.png)

# Units
Each unit (player / enemy) has a Name, Health, Attack and Defense. Each property differs depending on the unit type.
## Players
In addition to the game unit properties, each player has the following properties:
* **Experience**: Initially 0. Increased by killing enemies.
* **Player Level**: Initially 1. Increased by gaining experience.
* **Special Ability**: each player type has a different special ability. Will be explained later.
### Player Types
#### Warrior
**Special ability**: *Avenger’s Shield*, randomly hits one enemy withing range < 3 for an amount equals to 10% of the warrior’s max health and heals the warrior for amount equals to 10×defense (but will not exceed the total amount of health pool).
<br />
The warrior’s ability has a *cooldown*, meaning it can only be used once every ability cooldown game ticks.


