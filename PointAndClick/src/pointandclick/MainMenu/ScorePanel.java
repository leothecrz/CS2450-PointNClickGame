
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pointandclick.Common.ScoreTable;
import pointandclick.Common.RoundedBorder;

public class ScorePanel extends JPanel {
    
    private int playerScore;
    private ScoreTable scoreTable;
    
    private JLabel gotHSLabel;
    private JTextField gotHSNameField;
    private JButton gotHSSubmitButton;
    
    /**
     * Constructor of the panel. Sets ups the end button
     * with the endAndSkip ActionListener.
     * @param endAndSkip
     */
    public ScorePanel(ActionListener endAndSkip){
        super();
        
        playerScore = 0;
        scoreTable = new ScoreTable();
        
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        
        Font MarkerFelt = new Font("Marker Felt", Font.BOLD, 15);
        
        // Listener when user submits name for high score
        ActionListener gotHStextFieldListener = evt -> {
            String userName = gotHSNameField.getText();
            System.out.println(userName);
            gotHSNameField.setEnabled(false);
            gotHSSubmitButton.setVisible(false);
            
            scoreTable.secureAdd(playerScore, userName);
        };
        
        // High score components
        gotHSLabel = new JLabel();
        gotHSLabel.setText("You set a high score! Submit your name:");
        gotHSLabel.setToolTipText("You set a high score! Submit your name:");
        gotHSLabel.setFont(MarkerFelt);
        gotHSLabel.setBounds(170, 50, 300, 25);
        gotHSLabel.setVisible(true);

        gotHSNameField = new JTextField(14);
        gotHSNameField.setEnabled(true);
        gotHSNameField.setEditable(true);
        gotHSNameField.addActionListener(gotHStextFieldListener);
        gotHSNameField.setBounds(220, 90, 150, 25);
        gotHSNameField.setVisible(false);
        gotHSNameField.setToolTipText("Enter your name here.");

        gotHSSubmitButton = new JButton("Submit");
        gotHSSubmitButton.addActionListener(gotHStextFieldListener);
        gotHSSubmitButton.setBounds(263, 120, 80, 25);
        gotHSSubmitButton.setBorder(new RoundedBorder(15));
        gotHSSubmitButton.setContentAreaFilled(false);
        gotHSSubmitButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        gotHSSubmitButton.setToolTipText("Submit name");
        
        // End button
        JButton endButton = new JButton();
        endButton.setText("END");
        endButton.setFont(MarkerFelt.deriveFont(Font.PLAIN, 25f));
        endButton.setBorder(new RoundedBorder(18));
        endButton.setContentAreaFilled(false);
        endButton.addActionListener(endAndSkip);
        endButton.setActionCommand("End");
        endButton.setToolTipText("End game/Continue");
        endButton.setSelected(true);
        endButton.setBounds(480, 300, 100, 50);
        
        add(gotHSLabel);
        add(gotHSNameField);
        add(gotHSSubmitButton);
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
    
    public ScoreTable getScoreTable() {
        return this.scoreTable;
    }
    
    public void setState(boolean state) {
        gotHSLabel.setVisible(state);
        gotHSNameField.setVisible(state);
        gotHSSubmitButton.setVisible(state);
        
        if (state) {
            gotHSNameField.setText("");
            gotHSNameField.setEnabled(state);
        }
    }
    
    /**
     * Displays Score directly on the panel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setFont(new Font("Marker Felt", Font.BOLD, 50));

        // Center text
        FontMetrics metric = g2.getFontMetrics();
        String text = "Score: " + String.valueOf(playerScore);
        int x = (600 - metric.stringWidth(text)) / 2;
        int y = 215;
        g2.drawString(text, x, y);
    }
}
