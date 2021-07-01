# Battleship [![Build Status](https://travis-ci.com/SinusP-CW90/Battleship.svg?branch=main)](https://travis-ci.com/SinusP-CW90/Battleship) [![Coverage Status](https://coveralls.io/repos/github/SinusP-CW90/Battleship/badge.svg?branch=main)](https://coveralls.io/github/SinusP-CW90/Battleship?branch=main)
As a software project of the course Software Engineering at the University of Applied Sciences 
HTWG Konstanz, I'm developing the everywhere popular game 'Battleship' in the Scala programming language.

How to Play:
In the TUI:

You can change the Battlefiled size by enter: [without the quotation marks ;-)  ]
"2x2"
"3x3"
"4x4"
"6x6"
"9x9"

-> You can start the game with enter "start"
-> in the set State first the Left player can set their Ships for example with the enter of "a1" or "D4"
(With a 4x4 field there are 4 ships to set, with a 6x6 field 6 ships etc.)
then it's the right player's turn.
Then the right player has to guess the position of the ships of the right player and vice versa.
(unfortunately the ships of the opponent are not visible, this will be changed in the next feature branch)

-> The winner is the player who hit all of the opponent's ships

More options:
-> the current move can be undone with "undo" and carried out again with "redo"
-> In addition, the game status can also be saved and loaded. by entering "save" and "load"
-> You can also set random ships with "rl" for the left side, "rr" for the right and "rrr" for both sides.
-> you can switch the current Player with "sp"

_______________________________
GUI is still in progress - sorry