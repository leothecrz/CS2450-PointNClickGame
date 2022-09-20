package pointandclick.Frames;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import pointandclick.Frames.hangmanResources.HangmanGamePanel;
import pointandclick.Frames.hangmanResources.HangmanScorePanel;

/**
 * The Hangman class is a modified JPanel with a card layout.
 * This JPanel holds two other JPanels that can be swapped as the face.
 * HangmanGamePanel holds the game UI
 * HangmanScorePanel holds the end screen UI
 */
public class Hangman extends JPanel{
    
    private JPanel face;
    private HangmanGamePanel gamePanel;
    private HangmanScorePanel scorePanel;
    private CardLayout panelLayout;
    
    /**
     * Hangman Constructor. Creates the JPanel 'face' with CardLayout. Adds panels hold.
     * Creates an ActionListener to listen for the skip button to swap to next JPanel.
     * @param listener 
     */
    public Hangman(ActionListener listener) {
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        //
        ActionListener skipAndEndListener = evt -> {
            
            //Skip Button Pressed
            if(evt.getActionCommand().equals("Skip")){
                
                System.out.println("Skip Button Pressed");
                scorePanel.setPlayerScore(0);
                
            }
            //Natural Game End
            if(evt.getActionCommand().equals("Word Found") || evt.getActionCommand().equals("Word Not Found")){
                
                scorePanel.setPlayerScore(gamePanel.getPlayerScore());
            }
            
            panelLayout.show(face, "HangmanScorePanel");
        };
        
        panelLayout = new CardLayout(0, 0);
        face = new JPanel(panelLayout);
        gamePanel = new HangmanGamePanel(skipAndEndListener);
        scorePanel = new HangmanScorePanel(listener);
        face.add(scorePanel, scorePanel.getClass().getSimpleName());
        face.add(gamePanel, gamePanel.getClass().getSimpleName());
             
        add(face);
    }
    
    /**
     * Sends signal that game is staring to HangmanGamePanel.
     * Sets the face JPanel to display the HangmanGamePanel.
     */
    public void startGame(){
       gamePanel.startGame();
       panelLayout.show(face, "HangmanGamePanel");
       
    }
  
}
