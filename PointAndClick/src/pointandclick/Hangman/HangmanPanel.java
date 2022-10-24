
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import pointandclick.Common.RoundedBorder;

public class HangmanPanel extends JPanel {
    // Constants
    private static final int MAX_ERRORS = 6;
    private static final String KEYS = "qwertyuiopasdfghjklzxcvbnm";
    private static final String[] WORDBANK = {
        "abstract", "cemetery", "nurse", "pharmacy", "climbing"
    };

    // Components
    private JPanel topPanel; // Hangman panel
    private JPanel bottomPanel; // Keyboard panel
    private JButton skipButton;
    private JButton[] keyboardButtons; // Keyboard buttons
    private JLabel timeLabel;
    private Timer timeLabelTimer;

    // Listeners
    private ActionListener skipAndEndListener;
    private ActionListener buttonsGameListener;
    
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
    public HangmanPanel(ActionListener skipAndEndListener) {
        super();
        this.skipAndEndListener = skipAndEndListener;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.lightGray);

        setupListeners();
        setupUI();
    }

    private void setupListeners() {
        /**
         * Action Listener. Handles all interaction with the keyboard on the bottom panel.
         * Handles events that may occur from button presses such as a game loss or a game victory.
         */
        buttonsGameListener = evt -> { // Button Listener
            char pressedLetter = evt.getActionCommand().charAt(0);
            JButton pressedButton = keyboardButtons[KEYS.indexOf(pressedLetter)];
            if (!pressedButton.isEnabled())
                return; // User pressed letter that is disabled (keybind)
            pressedButton.setEnabled(false); // Disable each letter after it has been used.
            
            int index = wordToFind.indexOf(pressedLetter);
            if (index != -1) {
                do {
                    charsFound[index] = pressedLetter;
                    wordToFind = wordToFind.replaceFirst(String.valueOf(pressedLetter), "_");             
                    index = wordToFind.indexOf(pressedLetter);
                } while (index != -1);
                
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
                playerScore -= 10; // Subtract score
                if (errors++ == MAX_ERRORS - 1) {
                    // End game
                    disableButtons();

                    // Slowly reveal each letter
                    Timer endTimer = new Timer(1000, end -> {
                        boolean shouldContinue = true;
                        for (int i = 0; i < wordToFind.length(); i++) {
                            if (charsFound[i] == '_') {
                                charsFound[i] = wordToFind.charAt(i);
                                shouldContinue = false;
                                repaint();
                                break;
                            }
                        }

                        if (shouldContinue == true) {
                            ((Timer)end.getSource()).stop();
                            skipAndEndListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "HangmanEnd"));
                        }
                    });
                    endTimer.start();
                }
            }

            // Repaint to update hangman 
            repaint(); 
        };

        // Setup keybinds for keyboard input
        Action keyboardAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                buttonsGameListener.actionPerformed(e);
            }
        };

        for (char key : KEYS.toCharArray()) {
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
            getActionMap().put(key, keyboardAction);
        }
    }

    private void setupUI() {
        /* Top panel */ 
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(600, (int)(400*0.55))); // HARD CODED PANEL SIZES
        topPanel.setBackground(Color.GRAY); // USED TO DIFFIRENTIATE
        topPanel.setOpaque(false);

        // Skip button
        skipButton = new JButton("Skip");
        skipButton.addActionListener(skipAndEndListener);
        skipButton.setActionCommand("HangmanSkip");
        skipButton.setBounds(500, 70, 70, 25);
        skipButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        skipButton.setBorder(new RoundedBorder(15));
        skipButton.setContentAreaFilled(false);
        skipButton.setToolTipText("Skip Hangman Game.");

        // Time label
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            timeLabel.setText(formatter.format(new Date())); 
        };
        timeLabel = new JLabel("");
        timeLabel.setBounds(410, 11, 200, 25);
        timeLabel.setFont(new Font("Digital-7", Font.BOLD, 18));
        timeListener.actionPerformed(null); // Set the label's initial text
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        
        topPanel.add(skipButton);
        topPanel.add(timeLabel);
        
        /* Bottom panel */
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setPreferredSize(new Dimension(600, (int)(400 * 0.4))); // Set panel size
        bottomPanel.setBackground(Color.DARK_GRAY);
 
        // Keyboard buttons
        keyboardButtons = new JButton[KEYS.length()];
        Font buttonFont = new Font("Marker Felt", Font.PLAIN, 15);

        // Create row for each keyboard row
        // q w e r t y u i o p  10
        //  a s d f g h j k l   9
        //   z x c v b n m      7
        JPanel[] keyboardRows = new JPanel[] { new JPanel(), new JPanel(), new JPanel() };
        for (int i = 0; i < keyboardRows.length; i++) {
            keyboardRows[i].setLayout(new GridLayout(1, i == 0 ? 10 : i == 1 ? 9 : 7, 6, 0));
            keyboardRows[i].setBackground(Color.DARK_GRAY);
        }

        // Create and add keyboard button for each key
        for (int i = 0; i < KEYS.length(); i++) {
            keyboardButtons[i] = new JButton(); 
            keyboardButtons[i].setText(String.valueOf(KEYS.charAt(i)).toUpperCase()); // Set text to key
            keyboardButtons[i].addActionListener(buttonsGameListener); // All buttons connected to same listener
            keyboardButtons[i].setActionCommand(String.valueOf(KEYS.charAt(i))); // Buttons have action command corresponding to its letter
            keyboardButtons[i].setFont(buttonFont);
            keyboardButtons[i].setBackground(Color.LIGHT_GRAY);
            keyboardButtons[i].setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            keyboardButtons[i].setToolTipText("Input the letter '" + KEYS.charAt(i) + "'.");
            keyboardButtons[i].setEnabled(true);
            
            // Add button to respective keyboard row
            keyboardRows[i < 10 ? 0 : i < 19 ? 1 : 2].add(keyboardButtons[i]);
        }

        // Add each keyboard row to bottom panel
        for (JPanel row : keyboardRows) {
            bottomPanel.add(row);
        }
        
        // Add panels to layout
        add(topPanel);
        add(bottomPanel);
    }

    /**
     * Enables all buttons.
     */
    public void resetButtons() {
        for (JButton keyButton : keyboardButtons)
            keyButton.setEnabled(true);
    }

    private void disableButtons() {
        for (JButton keyButton : keyboardButtons)
            keyButton.setEnabled(false);
    }

    /**
     * Resets all necessary attributes and gets ready for game start.
     * A word is chosen at random.
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
     * Displays the hangman according to the amount of errors that have been made.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hangman stand
        g2.fillRect(100, 20, 5, 200);
        g2.fillRect(30, 220, 180, 5);
        g2.fillRect(100, 20, 80, 5);
        g2.fillRect(180, 20, 5, 30);

        // Display letters found and score
        g2.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        g2.drawString(wordFoundContent(), 300, 220);
        g2.setFont(new Font("Marker Felt", Font.BOLD, 25));
        g2.drawString("Score: " + playerScore, 300, 100);
        
        // Hangman person
        if (errors >= 1) g2.fillOval(163, 53, 40, 40);
        if (errors >= 2) g2.fillRect(180, 50, 5, 120);
        if (errors >= 3) g2.drawLine(160, 130, 180, 92);
        if (errors >= 4) g2.drawLine(207, 130, 185, 92);
        if (errors >= 5) g2.drawLine(160, 206, 180, 169);
        if (errors >= 6) {
            g2.drawLine(207, 206, 182, 165);

            // Draw face
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.setColor(Color.WHITE);
            g2.drawString("X", 172, 72);
            g2.drawString("X", 187, 72);
            g.drawArc(176, 80, 15, 15, 20, 140);
        }
    }
    
    /**
     * Formats the contents of charsFound into a string.
     * @return String of the contents held in the charsFound character array. 
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

    public int getPlayerScore(){
        return playerScore;
    }
}
