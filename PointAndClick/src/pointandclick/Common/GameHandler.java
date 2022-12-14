
/**
 *      file: GameHandler.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: Handles the events cause by the game ending.
 *          Uses a JPanel with a cardLayout to swap the panel as
 *          necessary.
 */

package pointandclick.Common;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pointandclick.ColorGame.ColorGamePanel;
import pointandclick.Hangman.HangmanPanel;
import pointandclick.MainMenu.ScorePanel;
import pointandclick.Pong.PongPanel;
import pointandclick.Sudoku.SudokuPanel;

public class GameHandler extends JPanel {
    
    private JPanel face;
    
    private HangmanPanel hangmanPanel;
    private ColorGamePanel colorGamePanel;
    private SudokuPanel sudokuPanel;
    private ScorePanel scorePanel;
    private PongPanel pongPanel;
    
    private CardLayout panelLayout;
    
    /**
     * Hangman Constructor.Creates the JPanel 'face' with CardLayout. Adds panels hold.
     * Creates an ActionListener to listen for the skip button to swap to next JPanel.
     * @param backButtonListener
     */
    public GameHandler(ActionListener backButtonListener) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setFocusable(true);
        setVisible(true);
        
        // Handles game end events
        ActionListener endOfGameListener = evt -> {
            switch (evt.getActionCommand()) {
                case "HangmanSkip":
                    scorePanel.setHangmanScore(0);     
                    panelLayout.show(face, colorGamePanel.getClass().getSimpleName());
                    break;
                case "HangmanEnd":
                    scorePanel.setHangmanScore(hangmanPanel.getPlayerScore());
                    panelLayout.show(face, colorGamePanel.getClass().getSimpleName());
                    break;
                case "ColorGameEnd":
                    scorePanel.setColorGameScore(colorGamePanel.getPlayerScore());
                    colorGamePanel.resetPlayerScore();
                    panelLayout.show(face, sudokuPanel.getClass().getSimpleName());
                    break;
                case "SudokuEnd":
                    scorePanel.setSudokuScore(sudokuPanel.getScore());
                    scorePanel.getScoreTable().loadScores();
                    if (scorePanel.getScoreTable().checkIfHighscoreBoolean(scorePanel.getPlayerScore())) {
                        scorePanel.setState(true);
                    } else {
                        scorePanel.setState(false);
                    }
                    panelLayout.show(face, scorePanel.getClass().getSimpleName());
                    break;
                case "PongQuit":
                    pongPanel.endPong();
                    backButtonListener.actionPerformed(new ActionEvent(getParent(), ActionEvent.ACTION_PERFORMED, "PongQuit")); 
                    break;
                default:
                    System.err.println("GameHandler: unknown command " + evt.getActionCommand());
                    break;
            }
        };
        
        panelLayout = new CardLayout(0, 0);
        face = new JPanel(panelLayout);
        
        hangmanPanel = new HangmanPanel(endOfGameListener);
        scorePanel = new ScorePanel(backButtonListener); 
        colorGamePanel = new ColorGamePanel(endOfGameListener);        
        sudokuPanel = new SudokuPanel(endOfGameListener);
        pongPanel = new PongPanel(endOfGameListener);
        
        face.add(pongPanel, pongPanel.getClass().getSimpleName());
        face.add(scorePanel, scorePanel.getClass().getSimpleName());
        face.add(hangmanPanel, hangmanPanel.getClass().getSimpleName());
        face.add(colorGamePanel, colorGamePanel.getClass().getSimpleName());
        face.add(sudokuPanel, sudokuPanel.getClass().getSimpleName());
             
        add(face);
    }
    
    /**
     * Sends signal that game is staring to HangmanGamePanel.
     * Sets the face JPanel to display the HangmanGamePanel.
     */
    public void startGame() {
       scorePanel.resetPlayerScore();
       hangmanPanel.startGame();
       sudokuPanel.reset();
       panelLayout.show(face, hangmanPanel.getClass().getSimpleName());
    }

    public void playPong() {
        panelLayout.show(face, pongPanel.getClass().getSimpleName());
        pongPanel.getFocus();
        pongPanel.resetScores();
    }
}
