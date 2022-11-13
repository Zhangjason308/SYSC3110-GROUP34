README for Scrabble Project including: version 2.0 11/13/2022

Deliverable 2: GUI based version

Description:
------------

- This project contains 15 java classes

- The Project is made up of three files:
	Bag.java 				- model class
	Board.java 				- model class
	BoardPanel.java 			- view class
	Dictionary.txt 			- Text File
	GameButtonPanel 			- view class
	Hand.java 				- model class
	HandPanel.java 			- view class
	InfoPanel.java 			- view class
	Piece.java 				- model class
	ScrabbleController.java 	- controller class
	ScrabbleFrame.java 		- view class
	ScrabbleGame.java 		- view class
	ScrabbleGameTest.java		- test class
	ScrabbleView.java 		- view class
	SelectionData.java 		- data type class

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
This class will create a JPanel that displays the remaining pieces in the bag and both player1 and player2's total score..

>Piece.java
This class will create a Piece that contains a character, and is assigned a points value.

>ScrabbleController.java
This class will react to alerts from the views by playing pieces on a board, checking for valid words, calculating the score, 
skip a player's turn, and swapping a player's hand. This controller then updates the ScrabbleGame. 

>ScrabbleFrame.java
This class will create a JFrame that contains the BoardPanel, GameButtonPanel, HandPanel, and InfoPanel.

>ScrabbleGame.java
This class will create a game model of Scrabble.

>ScrabbleGameTest.java
This class will use JUnit to test various piece placements and internal methods.

>ScrabbleView.java
This interface contains an update method.

>SelectionData.java
This class will parses JButtons to x-coordinate, y-coordinate, and Piece.

>
Credits:
--------
Jalal Mourad
	- UML
	- Piece.java
	- ScrabbleGame.java
	- ScrabbleGameTest.java
	- ScrabbleView.java
	- GameButtonPanel.java
	- Readme
	- Dictionary.txt

Caleb Lui-Yee
	- UML
	- Piece.java
	- ScrabbleGame.java
	- InfoPanel.java
	- ScrabbleGameTest.java
	- GameButtonPanel.java
	- Readme
	- Dictionary.txt

Jason Zhang
	- Board.java
	- BoardPanel.java
	- ScrabbleFrame.java
	- Hand.java
	- ScrabbleController.java
	- ScrabbleGameTest.java
	- UML
	- Readme
	
Triton Crowley
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
2. Player's turn:
	
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

3. Next Player's turn
4. Repeat until Game Bag is empty, or no possible words can be made