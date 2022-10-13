
/**
 *      file: HangmanGamePanel.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: Handles the logic and what should be displayed
 *          during the hangman game. Random word is chosen at
 *          the beginning of each game start.
 */

package pointandclick.Hangman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.Random;

import pointandclick.Common.RoundedBorder;

public class HangmanPanel extends JPanel {
    // Constants
    private static final int MAX_ERRORS = 6;
    private static final String KEYS = "abcdefghijklmnopqrstuvwxyz";
    private static final String[] WORDBANK = {
        "abstract", "cemetery", "nurse", "pharmacy", "climbing"
    };

    // Components
    private JPanel topPanel; // Hangman panel
    private JPanel bottomPanel; // Keyboard panel
    private JButton skipButton;
    private JButton[] keyButtons; // Keyboard buttons
    private JLabel timeLabel;
    private Timer timeLabelTimer;
    
    // Model
    private String wordToFind; // The current word
    private char[] charsFound; // Char array of found characters in the word
    private int errors; // Number of incorrect letters guessed
    private int playerScore;
    
    /**
     * Constructor for the HangmanGamePanel. Constructs actionListener 
     * responsible for handling all on screen keyboard interactions.
     * 
     * @param skipAndEndListener 
     */
    public HangmanPanel(ActionListener skipAndEndListener){
        super();
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.lightGray);
        
        /**
         * Action Listener. Handles all interaction with the keyboard on the bottom panel.
         * Handles events that may occur from button presses such as a Game Loss or a Game Victory.
         */
        ActionListener buttonsGameListener = evt -> { //Button Listener
            char pressedLetter = evt.getActionCommand().charAt(0);
            keyButtons[KEYS.indexOf(pressedLetter)].setEnabled(false); // Disable each letter after it has been used.
            
            int index = wordToFind.indexOf(pressedLetter);
            if (index != -1){
                do {
                    charsFound[index] = pressedLetter;
                    wordToFind = wordToFind.replaceFirst(String.valueOf(pressedLetter), "_");             
                    index = wordToFind.indexOf(pressedLetter);
                } while(index != -1);
                
                
                boolean isWordFound = true;
                for (int i = 0; i < charsFound.length; i++){
                    if (charsFound[i] == '_') {
                        isWordFound = false;
                        break;
                    }
                }
                
                if (isWordFound) {
                    skipAndEndListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "HangmanEnd"));
                }
            } else {
                playerScore -= 10;
                if (errors == MAX_ERRORS - 1) {
                    skipAndEndListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "HangmanEnd"));
                } else {
                    errors++;
                }
            }
            repaint(); 
        };

        // Top panel
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(600, (int)(400*0.55))); // HARD CODED PANEL SIZES
        topPanel.setBackground(Color.GRAY); // USED TO DIFFIRENTIATE
        topPanel.setOpaque(false);

        skipButton = new JButton("Skip");
        skipButton.addActionListener(skipAndEndListener);
        skipButton.setActionCommand("HangmanSkip");
        skipButton.setBounds(500, 70, 70, 25);
        skipButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        skipButton.setBorder(new RoundedBorder(15));
        skipButton.setContentAreaFilled(false);
        skipButton.setToolTipText("Skip Hangman Game.");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            timeLabel.setText(formatter.format(new Date())); 
        };
        timeLabel = new JLabel("");
        timeLabel.setBounds(400, 16, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null); // Set the label's initial text
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        
        topPanel.add(skipButton);
        topPanel.add(timeLabel);
        
        // Bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        // bottomPanel.setLayout(new GridLayout(4, 26)); // Alternate layout: GRID LAYOUT 2x13
        bottomPanel.setPreferredSize(new Dimension(595, (int)(400*0.4))); // HARD CODED PANEL SIZES
        bottomPanel.setBackground(Color.DARK_GRAY); // USED TO DIFFIRENTIATE
 
        // Keyboard buttons
        keyButtons = new JButton[KEYS.length()];
        Font buttonFont = new Font("Marker Felt", Font.PLAIN, 15);
        for (int i = 0; i < KEYS.length(); i++){
            keyButtons[i] = new JButton(); 
            keyButtons[i].setText(String.valueOf(KEYS.charAt(i))); // Each Key Represents its letter
            keyButtons[i].addActionListener(buttonsGameListener); // AllButtons Connected To Same Listener
            keyButtons[i].setActionCommand(String.valueOf(KEYS.charAt(i))); // Buttons Have Action Command Correspondint To Its Letter. Key a has command "a".
            keyButtons[i].setFont(buttonFont);
            keyButtons[i].setBackground(Color.LIGHT_GRAY);
            keyButtons[i].setBorder(BorderFactory.createEmptyBorder(15, 15, 20,15));
            keyButtons[i].setEnabled(true);
            
            bottomPanel.add(keyButtons[i]); // Add keyButtons to bottomPanel's grid. 
            // bottomPanel.add(Box.createRigidArea(new Dimension(2, 2))); // Buffer between buttons
        }
        
        // Add panels
        add(topPanel);
        add(bottomPanel);
    
    }

    /**
     * Sets all disabled buttons to the
     * enabled state.
     */
    public void resetButtons(){
        for (JButton keyButton : keyButtons)
            keyButton.setEnabled(true);
    }

    /**
     * Resets all necessary attributes and gets ready for game
     * start. A word is chosen at random.
     */
    public void startGame() {
       resetButtons();
       playerScore = 100;
       errors = 0;
       Random randomWord = new Random();
       wordToFind = WORDBANK[randomWord.nextInt(WORDBANK.length)]; // Choose random word from wordbank
       charsFound = new char[wordToFind.length()];
       for (int i = 0; i < charsFound.length; i++) {
            charsFound[i] = '_';
       }
    }
    
    /**
     * Displays the hangman according to the amount of errors that
     * have been made.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(100, 20, 5, 200);
        g2.fillRect(30, 220, 180, 5);
        g2.fillRect(100, 20, 80, 5);
        g2.fillRect(180, 20, 5, 30);
        g2.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        g2.drawString(wordFoundContent(), 300, 220);
        g2.setFont(new Font("Marker Felt", Font.BOLD, 25));
        g2.drawString("Score: " + playerScore, 300, 100);
        
        
        if (errors >= 1) g2.fillOval(163, 53, 40, 40);
        if (errors >= 2) g2.fillRect(180, 50, 5, 120);
        if (errors >= 3) g2.drawLine(160, 130, 180, 92);
        if (errors >= 4) g2.drawLine(207, 130, 185, 92);
        if (errors >= 5) g2.drawLine(160, 206, 180, 169);
        if (errors >= 6) g2.drawLine(207, 206, 182, 165);
    }
    
    /**
     * Formats the contents of charsFound into a string.
     * @return - String of the contents held in the charsFound character array. 
     */
    private String wordFoundContent() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < charsFound.length; i++) {
            b.append(charsFound[i]);

            if (i < charsFound.length - 1) {
                b.append(" ");
            }
        }         
        return b.toString();
    }
    
    /**
     * 
     * @return 
     */
    public int getPlayerScore(){
        return this.playerScore;
    }
    
}
