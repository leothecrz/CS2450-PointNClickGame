
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

package pointandclick.Frames;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import pointandclick.Frames.colorResources.ColorGamePanel;
import pointandclick.Frames.hangmanResources.HangmanGamePanel;
import pointandclick.Frames.hangmanResources.HangmanScorePanel;
import pointandclick.Frames.highscoreResources.Score;


public class Hangman extends JPanel{
    
    private JPanel face;
    private HangmanGamePanel gamePanel;
    private HangmanScorePanel scorePanel;
    private ColorGamePanel colorGamePanel;
    private CardLayout panelLayout;
    
    /**
     * Hangman Constructor. Creates the JPanel 'face' with CardLayout. Adds panels hold.
     * Creates an ActionListener to listen for the skip button to swap to next JPanel.
     * @param listener 
     */
    public Hangman(ActionListener listener) {
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        // Handles Hangman Game End Events
        ActionListener skipAndEndListener = evt -> {
            
            //Skip Button Pressed
            if(evt.getActionCommand().equals("Skip")){
                
                System.out.println("Skip Button Pressed");
                scorePanel.resetPlayerScore();
            }
            //Natural Game End
            if(evt.getActionCommand().equals("Word Found") || evt.getActionCommand().equals("Word Not Found")){
                scorePanel.addPlayerScore(gamePanel.getPlayerScore()); // add score from Hangman
            }
            
            if(!evt.getSource().equals(colorGamePanel)){
                panelLayout.show(face, "ColorGamePanel");
            }
            
            if(evt.getActionCommand().equals("SwitchToScore")){
                scorePanel.addPlayerScore(colorGamePanel.playerScore);// add score in ColorGamePanel to the score in scorePanel
                
                scorePanel.getScoreTable().loadScores();
                if(scorePanel.getScoreTable().checkIfHighscoreBoolean(scorePanel.getPlayerScore())){
                    scorePanel.setHSState(true);
                } else {
                    scorePanel.setHSState(false);
                }
                
                panelLayout.show(face, "HangmanScorePanel"); // Should Switch to next game
                
                
            }
        };
        
        panelLayout = new CardLayout(0, 0);
        face = new JPanel(panelLayout);
        gamePanel = new HangmanGamePanel(skipAndEndListener);
        scorePanel = new HangmanScorePanel(listener);
        
        colorGamePanel = new ColorGamePanel(skipAndEndListener);
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
