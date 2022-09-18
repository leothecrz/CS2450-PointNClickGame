package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HighScores extends JPanel {
    
    private Font MarkerFelt;
    private String[] topFiveNames;
    private String[] topFiveScores;
    
    public HighScores(ActionListener listener) {
        
        setLayout(null);
                    
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);
        backButton.setBounds(480,310,80,30);
        
        add(backButton);
        
        //Null Strings
        topFiveNames = new String[5];
        topFiveScores = new String[5];
        for(int i=0; i<5; i++){
            topFiveNames[i] = "";
            topFiveScores[i] = "0";
        }
        
        MarkerFelt = new Font("Marker Felt", Font.PLAIN, 20); // font set

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(MarkerFelt.deriveFont(40f));
        g2.drawString("Highscore:", (this.getWidth()/2)-75, 60);
        g2.setFont(MarkerFelt.deriveFont(20f));
        g2.drawString("Name:", (this.getWidth()/2)-120, 90);
        g2.drawString("Score:", (this.getWidth()/2)+75, 90);

        
        g2.setFont(MarkerFelt.deriveFont(20f));
        for(int i=0; i<5; i++){
            String drawString = new String();
            drawString = drawString.concat(String.valueOf(i+1) + ". "); 
            drawString = drawString.concat(topFiveNames[i]);
            
            String scoreString = topFiveScores[i];
            
            
            g2.drawString(drawString, (this.getWidth()/2)-120, 120+(i*30));
            g2.drawString(scoreString, (this.getWidth()/2)+90, 120+(i*30));

        }
        
        

    }   
    
    
}
