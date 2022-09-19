package pointandclick.Frames.hangmanResources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class HangmanGamePanel extends JPanel {
    // Constants
    private static final int MAX_ERRORS = 6;
    private static final int ALPHABET_COUNT = 26;
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
     * 
     * @param skipAndEndListener 
     */
    public HangmanGamePanel(ActionListener skipAndEndListener){
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
                repaint();
                
                boolean isWordFound = true;
                for (int i = 0; i < charsFound.length; i++){
                    if (charsFound[i] == '_') {
                        isWordFound = false;
                        break;
                    }
                }
                
                if (isWordFound) {
                    skipAndEndListener.actionPerformed(evt);
                }
            } else {
                playerScore -= 10;
                if (errors == MAX_ERRORS - 1) {
                    skipAndEndListener.actionPerformed(evt);
                } else {
                    errors++;
                }
            }
            System.out.println("Score: " + playerScore);
        };

        // Top panel
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(600, (int)(400*0.55))); // HARD CODED PANEL SIZES
        topPanel.setBackground(Color.GRAY); // USED TO DIFFIRENTIATE
        topPanel.setOpaque(false);

        skipButton = new JButton("Skip");
        skipButton.addActionListener(skipAndEndListener);
        skipButton.setActionCommand("Skip");
        skipButton.setBounds(500, 15, 70, 25);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            timeLabel.setText(formatter.format(new Date())); 
        };
        timeLabel = new JLabel("");
        timeLabel.setBounds(335, 16, 200, 25);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 15));
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

        // Add panels
        add(topPanel);
        add(bottomPanel);
        
        // Keyboard buttons
        keyButtons = new JButton[ALPHABET_COUNT];
        Font buttonFont = new Font(null, Font.PLAIN, 15);
        for (int i = 0; i < ALPHABET_COUNT; i++){
            keyButtons[i] = new JButton(); 
            keyButtons[i].setText(String.valueOf(KEYS.charAt(i))); // Each Key Represents its letter
            keyButtons[i].addActionListener(buttonsGameListener); // AllButtons Connected To Same Listener
            keyButtons[i].setActionCommand(String.valueOf(KEYS.charAt(i))); // Buttons Have Action Command Correspondint To Its Letter. Key a has command "a".
            keyButtons[i].setFont(buttonFont);
            keyButtons[i].setEnabled(true);
            
            bottomPanel.add(keyButtons[i]); // Add keyButtons to bottomPanel's grid. 
        }
    }

    public void resetButtons(){
        for (JButton keyButton : keyButtons)
            keyButton.setEnabled(true);
    }

    public void startGame() {
       resetButtons();
       playerScore = 100;
       errors = 0;
       wordToFind = WORDBANK[(int) (Math.random() * WORDBANK.length)]; // Choose random word from wordbank
       charsFound = new char[wordToFind.length()];
       for (int i = 0; i < charsFound.length; i++) {
            charsFound[i] = '_';
       }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(100, 20, 5, 200);
        g2.fillRect(30, 220, 180, 5);
        g2.fillRect(100, 20, 80, 5);
        g2.fillRect(180, 20, 5, 30);
        g2.setFont(new Font("Marker Felt", Font.BOLD, 35));
        g2.drawString(wordFoundContent(), 300, 220);
        
        
        /* havent checked if it works but the dimensions are correct to draw pieces
        if(errors == 1) {
                    g2.fillOval(163, 53, 40, 40);
                } else if (errors == 2) {
                    g2.fillRect(180, 50, 5, 120);
                } else if (errors == 3) {
                    g2.drawLine(160, 130, 180, 92);
                } else if (errors == 4) {
                    g2.drawLine(207, 130, 185, 92);
                } else if (errors == 5) {
                   g2.drawLine(160, 206, 180, 169);
                } else if (errors == 6) {
                    g2.drawLine(207, 206, 182, 165);
                } */
        
    }
    
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
}
