# Introduction
This game is asingle-player multi-level version of a dungeons and dragons board game.
<br />
You are trapped in a dungeon, surrounded by enemies. Your goal is to fight your way through them and get to the next level of the dungeon. Once you complete all the levels, you win the game.

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

## Units
Each unit (player / enemy) has a Name, Health, Attack and Defense. Each property differs depending on the unit type.

### Players
In addition to the game unit properties, each player has the following properties:
* **Experience**: Initially 0. Increased by killing enemies.
* **Player Level**: Initially 1. Increased by gaining experience.
* **Special Ability**: each player type has a different special ability. Will be explained later.

#### Player Types

##### 1. Warriors
**Special ability**: *Avenger’s Shield*, randomly hits one enemy withing range < 3 for an amount equals to 10% of the warrior’s max health and heals the warrior for amount equals to 10×defense (but will not exceed the total amount of health pool). *Avenger's Shield* has a *cooldown* that is reduced by 1 every turn.

![image](https://user-images.githubusercontent.com/102467192/209530403-59bca9e3-d189-483f-8252-ec88b61e06e9.png)

##### 2. Mages
**Special ability**: *Blizzard*, randomly hit enemies within range for an amount equals to the mage’s spell power at the cost of *mana*. The mage needs to have enough *mana* to cast *Blizzard*. The *mana* is increased every turn according to the level, and on leveling up.

**Hits Count**: maximal number of times a single cast of the ability can hit.

![image](https://user-images.githubusercontent.com/102467192/209530514-de71bdbd-8f7f-4e92-9aa6-9bfd12accf3b.png)

##### 3. Rouges
**Special ability**: *Fan of Knives* hits everyone around the rogue for an amount equals to the rogue’s attack points at the cost of energy. The rouge needs to have enough *energy* to case *Fan of Knives*. The *energy* is increased every turn by 10, and is filled upon leveling up.

![image](https://user-images.githubusercontent.com/102467192/209530620-c53da040-b040-4654-9f58-81bb64a50d9e.png)

##### 4. Hunters
**Special ability**: *Shoot*, hits the closest enemy for an amount equals to the hunter’s attack points at the cost of an *arrow*. The hunter should have at lease one enemy in range and at lease one *arrow* in his quiver starting with 10. every 10 turns the hunter gains additional arrows depending on his level.

![image](https://user-images.githubusercontent.com/102467192/209530277-cab099a8-6300-4912-be68-5fa505f65b74.png)

### Enemies
The player may encounter enemies while traveling around the world. Each enemy has a property of *Experience Value* which is the amount of experience gained by defeating this enemy.

#### Enemy Types

##### 1. Monsters
Each monster has a **vision range** that represents the monster’s vision range.
<br />
The monster will travel around the board. Monsters can move 1 step in one the following directions: Up/Down/Left/Right, and may chase the player if the player is within its *vision range*.

![image](https://user-images.githubusercontent.com/102467192/209529346-abd22410-6bb0-44fe-99ff-a100a2573b2b.png)

##### 2. Traps
Each trap has the following properties:
* **visibility time**: amount of turns that the trap remains visible.
* **invisibility time**: amount of turns that the trap remains invisible.

A trap can’t move (unlike monsters) but updates its state on each turn. After *visibility time* turns, the trap will become invisible. The trap becomes visible again after *invisibility time* turns elapsed. The trap may attack the player if the player is within a range of 2 from it.

![image](https://user-images.githubusercontent.com/102467192/209529430-5a50d074-2b69-4361-88eb-cfd830348ade.png)

## Combat System
When the player attempts to step on a location that has an enemy, or when an enemy attempts to step on the player’s location, they engage in melee combat.
The attacker is always the unit that performed the step. The other unit will attempt to defend itself.

# How to Play
The program takes a path of directory as command-line argument. The directory contains files represent the game boards. Each file is named "level\<i\>.txt" where \<i\> is the number of the level (For example: "level1.txt", "level2.txt", etc... Also, see the levels directory in the repo).

We will use the following tiles for each level:
  
![image](https://user-images.githubusercontent.com/102467192/209529137-932fe979-73c0-45e4-98d5-8a0e9e4dafa5.png)
  
And enemy tiles of your choice.

The game starts by asking the user to select the player character from a list of pre-defined characters. After choosing a character, the game will start.
A may use the following actions:

![image](https://user-images.githubusercontent.com/102467192/209528815-5815c438-5014-4090-9dba-b67b22a66fe3.png)

# Have fun!
