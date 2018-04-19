CS 141: Final Project
===
Intro to Programming and Problem Solving  
Professor: Edwin Rodríguez  
TEAM: The Ornithologists  
* Eduardo Saenz (Team captain)
* Jacob Chong
* Lance Dall
* Bumjoong Kim
* Corey Perez
* Grant Posner

Project Description
===
This project was designed to help students create simple turn-based game using objected oriented design philosophy extended 
from the Java object-oriented coding designs originally learned from the CS141 class. A small, yet interesting text-based game is to be developed, with these certain specifications.
 
The game design was to bring fun and challenge to the player who enjoys playing simple text-based adventure game. 
This would be achieved through an object-oriented design that allowed for turn-based commands from the players to be inputted to the 
game and have the game react accordingly.

Espionage Unbound!
===
You are trapped in a building where six ninja assassins are trying to kill you! The goal of the game is to find a briefcase with important documents inside.  The briefcase can spawn in one of nine rooms. If you are in an adjacent tile to a ninja, you will be stabbed and lose a life. You are equipped with a gun that has only one bullet. You can shoot ninjas to kill them, but they must be horizontal or vertical to your position. (which displays the room the briefcase is in), and an invincibility potion (which makes you immune to ninja stabbings for five turns. Good luck finding the briefcase!

### Controls
W: Moves the player up one cell; Shoots a bullet upwards.
A: Moves the player left one cell; Shoots a bullet left.
S: Moves the player down one cell; Shoots a bullat downwards.
D: Moves the player right one cell; Shoots a bullet right.
B: Enables _Shooting Mode_; Player will shoot a bullet instead of moving for the next input.
P: Opens Pause Menu; Allows player to load, save, exit, or open help.

### Game
The game begins with you placed on the bottom left cell in a 9x9 building, there are 9 rooms that could contain the briefcase. The building is pitch black, and you can only see two cells in any direction. Move around and search each room (its entrance is always on the north side) and find the briefcase to win. There are five Assassins trying to kill you, and your gun has only one bullet. You can find an extra bullet or invinibility powerup. The player has three lives before a Game Over.

##### Map and Key
```
P = The Player
R = A Room
A = An Assassin
b = Bullet Powerup
i = Invincibilty Powerup
B = Briefcase Room (Debug Mode only)
* = an empty room
```

```
  [*][*][*][*][*][*][*][*][*]  
  [*][R][*][*][R][*][*][R][*]  
  [*][*][*][*][*][*][*][*][*]  
  [*][*][*][*][*][*][*][*][*]  
  [*][R][*][*][R][*][*][R][*]  
  [*][*][*][*][*][*][*][*][*]  
  [ ][*][*][*][*][*][*][*][*]  
  [ ][R][*][*][R][*][*][R][*]  
  [P][ ][ ][*][*][*][*][*][*]  
  Choose your move: W, A, S, D, B, L, P
  Lives: 3
  Ammo: 1
```
*Fig 1 : The Player in the default spawn position within the building.*

Final Thoughts
===
Working on a game from scratch wasn’t a new problem for us, but doing so in a group, and with tighter time restraints, definitely was. 
Adopting ideas and information off of each other and online resources helped us push our way toward the answer, and having others to help 
with a small problem was very helpful and effective. However, having others work on code also lead to problems. Discrepancies within what 
solution to use and even how it should be implemented arose, but were conquered. In the end, we learned how to collaborate effectively, 
and built something together that we couldn’t have done alone, and there’s something to be proud of in that.

