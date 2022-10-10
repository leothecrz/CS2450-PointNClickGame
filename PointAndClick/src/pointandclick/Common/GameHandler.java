
/**
 *      file: Hangman.java
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

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import pointandclick.ColorGame.ColorGamePanel;
import pointandclick.Hangman.HangmanGamePanel;
import pointandclick.MainMenu.ScorePanel;

public class GameHandler extends JPanel {
    private JPanel face;
    private HangmanGamePanel gamePanel;
    private ScorePanel scorePanel;
    private ColorGamePanel colorGamePanel;
    private CardLayout panelLayout;
    
    /**
     * Hangman Constructor.Creates the JPanel 'face' with CardLayout. Adds panels hold.
     * Creates an ActionListener to listen for the skip button to swap to next JPanel.
     * @param backButtonListener
     */
    public GameHandler(ActionListener backButtonListener) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        // Handles Game End Events
        ActionListener endOfGameListener = evt -> {
            
            //Hangman SKIP Button Pressed
            if(evt.getActionCommand().equals("Skip")){
                System.out.println("Skip Button Pressed");
                scorePanel.addPlayerScore(0);
                                
                panelLayout.show(face, colorGamePanel.getClass().getSimpleName());
                return;

            } 
            //Hangman Natural Game End
            if(evt.getActionCommand().equals("Word Found") || evt.getActionCommand().equals("Word Not Found")){
                scorePanel.addPlayerScore(gamePanel.getPlayerScore()); // add score from Hangman

                panelLayout.show(face, colorGamePanel.getClass().getSimpleName());
                return;
            }
            //
            if(evt.getActionCommand().equals("")){
                
            }
            //Swtich to Score Panel
            if(evt.getActionCommand().equals("SwitchToScore")){
                scorePanel.addPlayerScore(colorGamePanel.playerScore);// add score in ColorGamePanel to the score in scorePanel
                
                scorePanel.getScoreTable().loadScores();
                if(scorePanel.getScoreTable().checkIfHighscoreBoolean(scorePanel.getPlayerScore())){
                    scorePanel.setHSState(true);
                } else {
                    scorePanel.setHSState(false);
                }
                
                panelLayout.show(face, scorePanel.getClass().getSimpleName());
                return;
            }
            
        }; // Action Listener End
        
        panelLayout = new CardLayout(0, 0);
        face = new JPanel(panelLayout);
        gamePanel = new HangmanGamePanel(endOfGameListener);
        scorePanel = new ScorePanel(backButtonListener);
        
        colorGamePanel = new ColorGamePanel(endOfGameListener);
        colorGamePanel.playerScore = scorePanel.getPlayerScore();                 // get score from scorePanel to store in int newScore from ColorGamePanel class (theoretically at least) 
        
        face.add(scorePanel, scorePanel.getClass().getSimpleName());
        face.add(gamePanel, gamePanel.getClass().getSimpleName());
        face.add(colorGamePanel, colorGamePanel.getClass().getSimpleName());
             
        add(face);
    }
    
    /**
     * Sends signal that game is staring to HangmanGamePanel.
     * Sets the face JPanel to display the HangmanGamePanel.
     */
    public void startGame(){
       scorePanel.resetPlayerScore();
       gamePanel.startGame();
       panelLayout.show(face, "HangmanGamePanel");
    }
  
}
