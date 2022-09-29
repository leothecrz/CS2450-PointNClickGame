
/**
 *      file: HangmanScorePanel.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: End Screen of the hangman game.
 *          Displays the score that was earned by the player
 *          during the game. Displays end button to return to 
 *          main menu.
 */

package pointandclick.Frames.hangmanResources;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import pointandclick.Frames.commonResources.RoundedBorder;


/**
 *
 */
public class HangmanScorePanel extends JPanel{
    
    private int playerScore;
    
    /**
     * Constructor of the panel. Sets ups the end button
     * with the endAndSkip ActionListener.
     * @param endAndSkip
     */
    public HangmanScorePanel(ActionListener endAndSkip){
        super();
        playerScore = 0;
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        
        JButton endButton = new JButton();
        endButton.setText("END");
        endButton.setFont(new Font("Marker Felt", Font.PLAIN, 20));
        endButton.setBorder(new RoundedBorder(15));
        endButton.setContentAreaFilled(false);
        endButton.addActionListener(endAndSkip);
        endButton.setActionCommand("End");
        endButton.setSelected(true);
        endButton.setBounds(480, 300, 100, 50);
        
        add(endButton);
        
    }
    
    /**
     * Allows outside classes to set the playerScore field.
     * @param playerScore - score for the playerScore field.
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Allows outside classes to get the playerScore field.
     * @return The value of the playerScore field
     */
    public int getPlayerScore() {
        return playerScore;
    }
    
    /**
     * Displays Score directly on the panel
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(new Font("Marker Felt", Font.BOLD, 50));
        g2.drawString(("Score: "+ String.valueOf(playerScore)), 210, 215);
        
    }
      
}
