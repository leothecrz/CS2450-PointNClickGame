
/**
 *      file: ScorePanel.java
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
    
    private int hangmanScore;
    private int colorGameScore;
    private int sudokuScore;
    private ScoreTable scoreTable;
    
    private JLabel gotHSLabel;
    private JFormattedTextField gotHSNameField;
    private JButton gotHSSubmitButton;
    
    /**
     * Constructor of the panel. Sets ups the end button
     * with the endAndSkip ActionListener.
     * @param endAndSkip
     */
    public ScorePanel(ActionListener endAndSkip){
        super();
        
        hangmanScore = 0;
        colorGameScore = 0;
        sudokuScore = 0;
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
            
            scoreTable.secureAdd(getPlayerScore(), userName);
        };
        
        // High score components
        gotHSLabel = new JLabel();
        gotHSLabel.setText("You set a high score! Submit your name:");
        gotHSLabel.setToolTipText("You set a high score! Submit your name:");
        gotHSLabel.setFont(MarkerFelt);
        gotHSLabel.setBounds(170, 50, 300, 25);
        gotHSLabel.setVisible(true);

        gotHSNameField = new JFormattedTextField();
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
     * Allows outside classes to reset the playerScore field.
     */
    public void resetPlayerScore() {
        this.hangmanScore = 0;
        this.colorGameScore = 0;
        this.sudokuScore = 0;
    }

    /**
     * Allows outside classes to get the total score.
     * @return Total player score
     */
    public int getPlayerScore() {
        return hangmanScore + colorGameScore + sudokuScore;
    }

    /**
     * Set the score of the current player in hangman.
     * @param score 
     */
    public void setHangmanScore(int score) {
        this.hangmanScore = score;
    }
    
    /**
     * Set the score of the current player in the color game.
     * @param score 
     */
    public void setColorGameScore(int score) {
        this.colorGameScore = score;
    }
    
    /**
     * Set the score of the current player in Sudoku.
     * @param score 
     */
    public void setSudokuScore(int score) {
        this.sudokuScore = score;
    }
    
    /**
     * Get access to the high score table in memory.
     * @return 
     */
    public ScoreTable getScoreTable() {
        return this.scoreTable;
    }
    
    /**
     * Set if the high score alert should be displayed. 
     * @param state 
     */
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
        String text = "Score: " + String.valueOf(getPlayerScore());
        g2.drawString(text, (600 - g2.getFontMetrics().stringWidth(text)) / 2, 215);

        g2.setFont(new Font("Marker Felt", Font.PLAIN, 25));
        text = "Hangman: " + String.valueOf(hangmanScore);
        g2.drawString(text, (600 - g2.getFontMetrics().stringWidth(text)) / 2, 240);
        text = "Colors: " + String.valueOf(colorGameScore);
        g2.drawString(text, (600 - g2.getFontMetrics().stringWidth(text)) / 2, 265);
        text = "Sudoku: " + String.valueOf(sudokuScore);
        g2.drawString(text, (600 - g2.getFontMetrics().stringWidth(text)) / 2, 290);
    }
}
