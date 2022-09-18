package pointandclick.Frames;

import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.*;
import pointandclick.Frames.hangmanResources.HangmanGamePanel;
import pointandclick.Frames.hangmanResources.HangmanScorePanel;

public class Hangman extends JPanel{
    
    private JPanel face;
    private HangmanGamePanel gamePanel;
    private HangmanScorePanel scorePanel;
    private CardLayout panelLayout;
    
    /**
     * 
     * @param listener 
     */
    public Hangman(ActionListener listener) {
        
        ActionListener endAndSkip = evt -> {
            panelLayout.show(face, scorePanel.getClass().getSimpleName());
        };
        
        
        panelLayout = new CardLayout();
        face = new JPanel(panelLayout);
        gamePanel = new HangmanGamePanel(endAndSkip);
        scorePanel = new HangmanScorePanel();
        face.add(scorePanel, gamePanel.getClass().getSimpleName());
        face.add(gamePanel, gamePanel.getClass().getSimpleName());
        
         
        add(face);
    }
    
    /**
     * 
     */
    public void startGame(){
       gamePanel.startGame();
       panelLayout.show(face, gamePanel.getClass().getSimpleName());
       
    }
  
}
