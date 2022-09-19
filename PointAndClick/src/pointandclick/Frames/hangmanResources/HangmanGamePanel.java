package pointandclick.Frames.hangmanResources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;


/**
 *  
 *
 */
public class HangmanGamePanel extends JPanel{
    
    private static final int ALPHABET_COUNT = 26;
    private static final String KEYS = "abcdefghijklmnopqrstuvwxyz";
    private static final String[] WORDBANK = {
        "abstract", "cemetery", "nurse", "pharmacy", "climbing"
    };
    
    public static final int MAX_ERRORS = 6;
    
    private JPanel topPanel;
    private JPanel bottomPanel;
    
    private JLabel wordSpaces;
    
    private JButton skipButton;
    private JButton[] keyButtons;
        
    private String wordToFind; // The current word
    private char[] wordFound; // Char array of found characters in the word
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
            int keynum = pressedLetter - 97; 
            //System.out.println(keyButtons[keynum].getText());
            keyButtons[keynum].setEnabled(false); // Disable each letter after it has been used.
            
            int index = wordToFind.indexOf(pressedLetter);
            if( index != -1){
              
                do{
                    wordFound[index] = pressedLetter;
                    wordToFind = wordToFind.replaceFirst(String.valueOf(pressedLetter), "_");             
                    index = wordToFind.indexOf(pressedLetter);
                
                }while(index != -1);
                
                System.out.println(wordFoundContent());
                
                boolean isWordFound = true;
                for(int i=0; i<wordFound.length; i++){
                    if(wordFound[i] == '_')
                            isWordFound = false;
                }
                
                if(isWordFound){
                    
                    Timer gameWinTimer = new Timer(0, skipAndEndListener); // A Timer with a 0 delay calls on skipListener
                    gameWinTimer.setRepeats(false);
                    gameWinTimer.setActionCommand("Word Found");
                    gameWinTimer.start();
                    
                }
                
            } else {
                System.out.println("Not in Word");
                if(errors == MAX_ERRORS-1){
                    
                    Timer gameLossTimer = new Timer(0, skipAndEndListener); // A Timer with a 0 delay calls on skipListener
                    gameLossTimer.setRepeats(false);
                    gameLossTimer.setActionCommand("Word Not Found");
                    gameLossTimer.start();
                    
                } else{
                    errors++;
                }
                System.out.println("Errors: " + errors);
            }
            
            
               
        };
        
        /**
         * 
         */
        ActionListener ScreenUpdatesLister = evt -> { // Update Screen For Game start
            
        };
        
        // TOP PANEL
        
        topPanel = new JPanel();
        
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(600, (int)(400*0.55))); // HARD CODED PANEL SIZES
        topPanel.setBackground(Color.GRAY); // USED TO DIFFIRENTIATE
        topPanel.setOpaque(false);
        
        skipButton = new JButton("Skip");
        skipButton.addActionListener(skipAndEndListener);
        skipButton.setActionCommand("Skip");
        skipButton.setBounds(500, 15, 70, 25);
        
        topPanel.add(skipButton);
        
        add(topPanel);
        
        // BOTTOM PANEL
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(595, (int)(400*0.4))); // HARD CODED PANEL SIZES
        bottomPanel.setBackground(Color.DARK_GRAY); // USED TO DIFFIRENTIATE
        //bottomPanel.setLayout(new GridLayout(4, 26)); // GRID LAYOUT 2x13
        bottomPanel.setLayout(new FlowLayout());
        add(bottomPanel);
        
        keyButtons = new JButton[ALPHABET_COUNT];
        Font buttonFont = new Font(null, Font.PLAIN, 15);
        for(int i=0; i<ALPHABET_COUNT; i++){
            keyButtons[i] = new JButton(); 
            //keyButtons[i].setSize(40, 25);
            keyButtons[i].setText(String.valueOf(KEYS.charAt(i))); // Each Key Represents its letter
            keyButtons[i].addActionListener(buttonsGameListener); // AllButtons Connected To Same Listener
            keyButtons[i].setActionCommand(String.valueOf(KEYS.charAt(i))); // Buttons Have Action Command Correspondint To Its Letter. Key a has command "a".
            
            keyButtons[i].setFont(buttonFont);
            keyButtons[i].setEnabled(true);
            
            bottomPanel.add(keyButtons[i]); // Add keyButtons to bottomPanel's grid. 
        }
    }
    
    /**
     * 
     */
    public void resetButtons(){
        for(int i=0; i<ALPHABET_COUNT; i++){  
            keyButtons[i].setEnabled(true);
        }
    }
    
    /**
     * 
     */
    public void startGame(){
       resetButtons();
       playerScore = 100;
       errors = 0;
       int r = (int) (Math.random() * (4)); 
       wordToFind = WORDBANK[r];                // Choose random word
       wordFound = new char[wordToFind.length()];
       
       for (int i = 0; i < wordFound.length; i++) {
               wordFound[i] = '_';
       }
       
       
       //JLabel wordSpaces = new JLabel (wordFoundContent()); // Doesn't work but an attempt to display empty letters
       //topPanel.add(wordSpaces);                                     // like this "_ _ _ _"
    }
    
    // This makes a stand for hanging the man but it appears behind the gray JPanel... i dont know how to bring it in front of the gray background
    @Override
    public void paintComponent(Graphics g){
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D) g;
                
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRect(100, 20, 5, 200);
                g2.fillRect(30, 220, 180, 5);
                g2.fillRect(100, 20, 80, 5);
                g2.fillRect(180, 20, 5, 30);
                
    } 
    
    private String wordFoundContent() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < wordFound.length; i++) {
            b.append(wordFound[i]);

            if (i <wordFound.length - 1) {
                b.append(" ");
            }
        }         
        return b.toString();
    }
    
}
