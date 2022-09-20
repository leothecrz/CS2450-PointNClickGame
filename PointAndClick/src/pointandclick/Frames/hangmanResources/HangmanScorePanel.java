package pointandclick.Frames.hangmanResources;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author LeothEcRz
 */
public class HangmanScorePanel extends JPanel{
    
    private int playerScore;
    
    /**
     * 
     * @param endAndSkip
     */
    public HangmanScorePanel(ActionListener endAndSkip){
        super();
        playerScore = 0;
        setPreferredSize(new Dimension(600, 400));
        
        JButton endButton = new JButton();
        endButton.setText("END");
        endButton.addActionListener(endAndSkip);
        endButton.setActionCommand("End");
        
        add(endButton);
        
    }
    
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(new Font("Marker Felt", Font.BOLD, 50));
        g2.drawString(("Score: "+ String.valueOf(playerScore)), 200, 200);
        
    }
      
}
