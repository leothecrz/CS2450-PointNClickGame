
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

package pointandclick.MainMenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pointandclick.Common.RoundedBorder;

/**
 *
 */
public class ScorePanel extends JPanel{
    
    private int playerScore;
    private ScoreTable scoreTable;
    
    private JLabel gotHSLabel;
    private JTextField gotHSNameField;
    
    /**
     * Constructor of the panel. Sets ups the end button
     * with the endAndSkip ActionListener.
     * @param endAndSkip
     */
    public ScorePanel(ActionListener endAndSkip){
        super();
        
        playerScore = 0;
        scoreTable = new ScoreTable(pointandclick.PointAndClick.SCORE_FILE_PATH);
        
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        
        Font MarkerFelt = new Font("Marker Felt", Font.BOLD, 15);
        
        gotHSLabel = new JLabel();
        gotHSLabel.setText(" You Set a High Score!! Sumbit Your Name. ");
        gotHSLabel.setFont(MarkerFelt);
        gotHSLabel.setBounds(170, 50, 300, 25);
        gotHSLabel.setVisible(true);
        
        add(gotHSLabel);
        
        ActionListener gotHStextFieldListener = evt -> {
            String userName = gotHSNameField.getText();
            System.out.print(userName);
            gotHSNameField.setEnabled(false);
            
            scoreTable.secureAdd(playerScore, userName);
        };
        
        gotHSNameField = new JTextField(14);
        gotHSNameField.setEnabled(true);
        gotHSNameField.setEditable(true);
        gotHSNameField.addActionListener(gotHStextFieldListener);
        gotHSNameField.setBounds(220, 90, 150, 25);
        gotHSNameField.setVisible(false);
        
        add(gotHSNameField);
        
        JButton endButton = new JButton();
        endButton.setText("END");
        endButton.setFont(MarkerFelt.deriveFont(Font.PLAIN, 25f));
        endButton.setBorder(new RoundedBorder(18));
        endButton.setContentAreaFilled(false);
        endButton.addActionListener(endAndSkip);
        endButton.setActionCommand("End");
        endButton.setSelected(true);
        endButton.setBounds(480, 300, 100, 50);
        
        add(endButton);
        
    }
    
    /**
     * Allows outside classes to add to the playerScore field.
     * @param playerScore - score for the playerScore field.
     */
    public void addPlayerScore(int pScore) {
        this.playerScore += pScore;
    }
    
    /**
     * Allows outside classes to reset the playerScore field.
     * @param playerScore - score for the playerScore field.
     */
    public void resetPlayerScore() {
        this.playerScore = 0;
    }

    /**
     * Allows outside classes to get the playerScore field.
     * @return The value of the playerScore field
     */
    public int getPlayerScore() {
        return playerScore;
    }
    
    public ScoreTable getScoreTable(){
        return this.scoreTable;
    }
    
    public void setState(boolean state){
        gotHSLabel.setVisible(state);
        gotHSNameField.setVisible(state);
        
        if(state){
            gotHSNameField.setText("");
            gotHSNameField.setEnabled(state);
        }
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
