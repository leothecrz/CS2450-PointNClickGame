# CS2450-PointNClickGame
An introduction to GUI's via a point and click game. Basic hangman interface using java swing.

Requirements:
1. 600x400 centered window
2. 3 second splash start screen that displays PROJECT name and GROUP name
3. Main menu with a logo and three options.
4. HIGHSCORES Button - leads to placeholder leaderboard
5. CREDITS - leads to credit screen with groups names and ids
6. PLAY Button - leads to HANGMAN game.
7. HIGHSCORE and CREDITS both should have a back button to return to the menu
8. The Player begins with a base 100 points. 10 points are loss for each incorect guess. 6 Tries before the hangman is fully drawn.
9. Skip button and Current Time Display During the hangman game.
10. At Games end players score will be displayed (a zero score for skipped games) and an end button to take the player back to the menu.

Word List: 
abstract, 
cemetery, 
nurse, 
pharmacy, 
climbing

Version 1.1:

0. Implement highscore functionallity.
1. If game ends or skip(0 points) is pressed swich to Color Buttons Game. Do not share score screen.
2. 5 buttons of a color from the COLOR LIST should be randomly placed.
3. A word matching the text for the name of one of the colors from the COLOR LIST.
4. The word would also be given a color at random from the COLOR LIST. 
5. The player is to click the button matching the text's COLOR. 100 points for correct and 0 for incorrect.
6. 5 Rounds then the end screen with score 
7. If the player score if bigger than the worst save score in the highscore allow them to save their score and input name or initials.

Color List:
Red,
Yellow,
Green,
Blue,
Purple

Version 1.2:

1. Tooltips for every component.
2. F1 key opens pop up display. Shows Names, Bronco #'s, Project Name, and the term name.
3. ESC key quits program.
4. Color Button Game leads to sudoku puzzle.
5. Sudoku puzzle panel will have the buttons SUBMIT and QUIT. Will display Time. Will display 'Sudoku' tittle.
6. The puzzle will use the same solution. 54 Empty boxes.
7. On submit 10 points will be loss for every incorrect box. Only once.
8. Subsequent submissions will only check if correct.
9. Alert the user if their submission is inccorect.
10. User input method. If using a text field inform the user of incorrect character use.

Version 1.3:

1. Add "Play Pong" Button to main menu.
2. Implement the pong game.
3. Pong Game should have a tittle and show the current data and time. The screen should also have a way for the player to exit the game.
4. Game must be played by two players
5. The pong game screen will have to keep track and display each of the player's scores.
6. 10 points are awarded for each goal. Points are never deducted
7. The first player to reach 100 wins.
8. If a player reaches 100, then the game ends and the players are informed.
9. The players should be allowed to replay or quit to menu.

