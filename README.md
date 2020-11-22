# Slash Game

## Now Free to Play

Why you should play:
- No advertisements
- Endless gameplay
- Most importantly, it's free!!!

Slash Game is a free single player infinite mob killing game.
Slash game enables you to command your character to do a multitude of things.
Most importantly you will be able to kill enemys to progress to higher levels.
The end user will hopefully be someone who would like to try what is traditionally a 
mobile game on a computer. This project is interesting to me because as of late,
I have played many clicker games, and I thought it would be cool to create my own clicker 
game. As well it allowed me to put my own twist on the genre by enabling users to create
and kill their bosses.






## User Stories
- As a user I want to be able to attack the enemy
- As a user I want to be able to upgrade my weapon
- As a user I want to be able to create and attack my own bosses
- As a user I want to be able to prestige in order beat enemies faster
- As a user I want to be able to check how much money I have
- As a user I want to be able to save my game
- As a user I want to be able to resume my game from my last save



## Phase 4: Task 2
I have chosen to implement the "Test and design a class in your model package that is robust.
You must have at least one method that throws a checked exception.  You must have one test for the case where the 
exception is expected and another where the exception is not expected." option. 

The class that is robust is the boss class. The method that makes the class robust is the constructor. During the 
construction of the boss, if the given health is less than 1, the constructor throws a NegativeHealthException. 
This exception is caught in the GUI class when hitting the save boss button. If the exception is caught it prompts the
user to use a number that is greater than 0 for the health. 