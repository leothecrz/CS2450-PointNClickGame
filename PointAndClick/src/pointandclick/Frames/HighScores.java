
/**
 *      file: HighScores.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: (WIP) Will display the high scores from the
 *          game.
 */

package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pointandclick.Frames.commonResources.RoundedBorder;
import pointandclick.Frames.highscoreResources.Score;
import pointandclick.Frames.highscoreResources.ScoreTable;
import pointandclick.PointAndClick;

public class HighScores extends JPanel {
    
    private Font MarkerFelt;
   
    /**
     * Panel constructor.Initializes the leader board with the current top Scores 
     * @param listener - listener for the backButton to connect with ActionListener
     * @param path
     */
    public HighScores(ActionListener listener) {
        
        MarkerFelt = new Font("Marker Felt", Font.PLAIN, 20); // font set
        setLayout(null);
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);
        backButton.setFont(new Font("Marker Felt", Font.PLAIN, 20));
        backButton.setBounds(480,310,80,30);
        backButton.setBorder(new RoundedBorder(15));
        backButton.setContentAreaFilled(false);
        
        add(backButton);

    }

    /**
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Score[] scores = new ScoreTable(PointAndClick.SCOREFILEPATH).getScoreArray();
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(MarkerFelt.deriveFont(40f));
        g2.drawString("Highscores:", (this.getWidth()/2)-75, 60);
        g2.setFont(MarkerFelt.deriveFont(20f));
        g2.drawString("Name:", (this.getWidth()/2)-120, 90);
        g2.drawString("Score:", (this.getWidth()/2)+75, 90);

        
        g2.setFont(MarkerFelt.deriveFont(20f));
        for(int i=0; i<5; i++){
            String drawString = new String();
            drawString = drawString.concat(String.valueOf(i+1) + ". "); 
            drawString = drawString.concat(scores[i].getName());
            
            String scoreString = String.valueOf(scores[i].getScore());
            
            
            g2.drawString(drawString, (this.getWidth()/2)-120, 120+(i*30));
            g2.drawString(scoreString, (this.getWidth()/2)+90, 120+(i*30));

        }
    }   
    
}
