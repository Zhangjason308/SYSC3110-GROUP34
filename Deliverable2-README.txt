README for Scrabble Project including: version 4.0 12/9/2022

Deliverable 4: For this deliverable, we added save,load,undo,redo commands, and the player can pick a board (board1,board2,board3) then the player can pick to play with
the AI or player2 which the user will be playing against.

Description:
------------

- This project contains fifteen java classes.

- The Project is made up of the following files:
	Bag.java 				- model class (A single Java script)
	Board.java 				- model class (A single Java script)
	BoardPanel.java 		- view class  (A single Java script)
	Dictionary.txt 			- A single text file
	GameButtonPanel.java 	- view class  (A single Java script)
	Hand.java 				- model class (A single Java script)
	HandPanel.java 			- view class (A single Java script)
	InfoPanel.java 			- view class  (A single Java script)
	MenuBarController.java  - controller class (A single Java script)
	MenuBarPanel.java       - view class  (A single Java script)
	Piece.java 				- model class (A single Java script)
	SavedGameState.java     - model class (A single Java script)
	ScrabbleController.java - controller class (A single Java script)
	ScrabbleFrame.java 		- view class (A single Java script)
	ScrabbleGame.java 		- view class (A single Java script)
	ScrabbleGameTest.java	- test class (A single Java script)
	ScrabbleView.java 		- view class (A single Java script)
	SelectionController.java- controller class (A single Java script)
	SelectionData.java 		- data type class (A single Java script)
	StartFrame.java         - model class (A single Java script)
	StartFrameController.java - controller class (A single Java script)
	UML					    - A single image of a UML diagram

Installation:
-------------
To be able to to run the program, you should have Java 15.0.2 or later installed on your
computer.


Usage:
------

>Bag class
This class will contain a randomly generated list of the given Scrabble letters.

>Board.java
This class will create a new 15x15 array of Pieces.

>BoardPanel.java
This class will create a new 15x15 array of JButtons that will update the board model upon button clicks.

>Dictionary.txt
This text file contains all the valid words in the dictionary.

>GameButtonPanel.java
This class will create a JPanel that contains a "play", "skip", and "swap" JButton.

>Hand.java
This class Hand is made up of 7 piece/characters that will be selected to be placed on the board.

>HandPanel.java
This class will create a JPanel that contains 7 JButtons representing the pieces in the player's respective hand.

>InfoPanel.java
This class will create a JPanel that displays the remaining pieces in the bag and both player1 and player2's total score.

>MenuBarController.java
This class will react to alerts from the views by pressing Save,Load,Undo,Redo commands.

>MenuBarPanel.java
This class will create a JMenuBar that contains the JMenuItems Save,Load,Undo,Redo commands.

>Piece.java
This class will create a Piece that contains a character, and is assigned a points value.

>SavedGameState.java
This class will save the state of the game that is currently in progress.

>ScrabbleController.java
This class will react to alerts from the views by playing pieces on a board, checking for valid words, calculating the score,
skip a player's turn, and swapping a player's hand. This controller then updates the ScrabbleGame.

>ScrabbleFrame.java
This class will create a JFrame that contains the BoardPanel, GameButtonPanel, HandPanel, and InfoPanel.

>ScrabbleGame.java
This class will handle the implementation of a game model of Scrabble.

>ScrabbleGameTest.java
This class will use JUnit to test various piece placements and internal methods.

>ScrabbleView.java
This interface contains an update method, it handles Scrabble game's data presentation and user interaction.

>SelectionData.java
This class will parses JButtons to x-coordinate, y-coordinate, and Piece.

>StartFrame.java
This class will create a JFrame that chooses the starting frame of the player which contains information about Board1,Board2,Board3.

>StartFrameController.java
This class will react to alerts from the views by pressing the starting Frame (Board1,Board2,Board3) and if the player will be playing against the AI or player2.

Credits:
--------
Jalal Mourad
	- UML
	- Piece.java
	- ScrabbleGame.java
	- ScrabbleGameTest.java
	- ScrabbleView.java
	- GameButtonPanel.java
	- StartFrameController.java
	- Readme
	- Dictionary.txt

Caleb Lui-Yee
    -
	- UML
	- Piece.java
	- ScrabbleGame.java
	- InfoPanel.java
	- ScrabbleGameTest.java
	- GameButtonPanel.java
	- Readme
	- Dictionary.txt

Jason Zhang
    - StartFrame.java
	- Board.java
	- BoardPanel.java
	- ScrabbleFrame.java
	- Hand.java
	- ScrabbleController.java
	- ScrabbleGameTest.java
	- UML
	- Readme

Triton Crowley
    - SavedGameState.java
	- ScrabbleGame.java
	- ScrabbleController.java
	- Bag.java
	- BoardPanel.java
	- HandPanel.java
	- ScrabbleGameTest.java
	- SelectionData.java
	- UML
	- Readme


NOTE:

Copyright 2022 Jalal Mourad, Caleb Lui-Yee, Jason Zhang, Triton Crowley

How to use:

1. Run the main.java file in ScrabbleFrame.java.
2. Choose your Board
3. The player picks if he is playing with an AI or Player2
4. Player's turn: Start from the yellow tile

-------------------------------------------- Game Options: Play, Skip, Swap ---------------------------------------------

	Play:
		1. Click desired number of pieces from the Hand
		2. Click the piece placement on the board (If multiple pieces were selected, they will be placed in order of selection)
		3. Select "Play"
		Note: Invalid words or placements will return all pieces to Hand, and will ask player to retry

	Skip:
		1. Select "Skip"
		Note: If pieces are placed on the Board prior to skipping, they will return to hand
	Swap:
		1. Click desired number of pieces from the Hand
		2. Select "Swap"
		Note: If pieces are placed on the Board prior to swapping, they will return to hand,
			and only the selected pieces from the Hand will be swapped.
-------------------------------------------------------------------------------------------------------------------------

5. AI's turn or Player2's turn.
6. If a word is placed on a multiplier, the player's score will me multiplied depending on the color of the multiplier it is placed on, red color
tiles are x3 multipliers, and blue color tiles are x2 multipliers.
7. Blank pieces are represented by "!" .
8. Player Can Undo or Redo his tile.
9. Player can save his game, then load it when he enters again.
10. Repeat until Game Bag is empty, or no possible words can be made.