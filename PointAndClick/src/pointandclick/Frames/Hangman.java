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
            if(evt.getActionCommand().equals("Skip")){
                panelLayout.show(face, "HangmanScorePanel");
                System.out.println("Skip Button Pressed");
            }
        };
        
        panelLayout = new CardLayout(0, 0);
        face = new JPanel(panelLayout);
        gamePanel = new HangmanGamePanel(endAndSkip);
        scorePanel = new HangmanScorePanel(listener);
        System.out.println(scorePanel.getClass().getSimpleName());
        face.add(scorePanel, scorePanel.getClass().getSimpleName());
        System.out.println(gamePanel.getClass().getSimpleName());
        face.add(gamePanel, gamePanel.getClass().getSimpleName());
             
        add(face);
    }
    
    /**
     * 
     */
    public void startGame(){
       gamePanel.startGame();
       panelLayout.show(face, "HangmanGamePanel");
       
    }
  
}
