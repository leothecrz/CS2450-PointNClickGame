package pointandclick.Frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Hangman extends JPanel implements ActionListener {
    
    private final int ALPHABET_COUNT = 26;
    private final String keys = "qwertyuiopasdfghjklzxcvbnm";
    private final String[] wordBank = {
        "abstract", "cemetery", "nurse", "pharmacy", "climbing"
    };
        
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    
    private JButton skipButton;
    private JButton endButton;
    private JButton[] keyButtons;
    
    public Hangman(ActionListener listener) { // lister is able to call on PointAndCLICK
        
        setLayout(new FlowLayout());
        //setLayout(new GridLayout(2, 1));
        
        ActionListener buttonsGameListener = evt -> { //Button Listener
                      
            /* //Either Works Unsure Which is safer...
            if(evt.getSource() == skipButton){
                System.out.println("SKIP BUTTON PRESSED");
            }
            */ 
            if((evt.getActionCommand()).equals("Skip")){
                
                System.out.println("SKIP BUTTON PRESSED");
                endButton.setVisible(true);
                  
            } else {

                switch ((evt.getActionCommand()).charAt(0)) {
                    case 'w':
                        keyButtons[1].setEnabled(false); // Disable Button After Press
                        break;

                    case 'n':
                        keyButtons[24].setEnabled(false); // Disable Button After Press
                        break;

                    default:
                }

                // IF Statements or a Switch...
                if(evt.getActionCommand().equals("q")){
                    keyButtons[0].setEnabled(false); // Disable Button After Press
                }
                if(evt.getActionCommand().equals("m")){
                    keyButtons[25].setEnabled(false); // Disable Button After Press
                }
            }
        }; // buttonsGameListener end
        
        skipButton = new JButton("Skip"); // SKIP BUTTON
        skipButton.addActionListener(buttonsGameListener);
        skipButton.setActionCommand("Skip");
        
        endButton = new JButton("END");
        endButton.addActionListener(listener);
        endButton.setActionCommand("End");
        endButton.setVisible(false); // Button Not Shown Until Requested
        
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(600, (int)(400*0.6))); // HARD CODED PANEL SIZES
        topPanel.setBackground(Color.red); // USED TO DIFFIRENTIATE
        topPanel.add(skipButton);
        topPanel.add(endButton);
        add(topPanel);
        
        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(590, (int)(400*0.4))); // HARD CODED PANEL SIZES
        bottomPanel.setBackground(Color.blue); // USED TO DIFFIRENTIATE
        //bottomPanel.setLayout(new GridLayout(4, 26)); // GRID LAYOUT 2x13
        bottomPanel.setLayout(new FlowLayout());
        add(bottomPanel);
       

        keyButtons = new JButton[ALPHABET_COUNT];
        Font buttonFont = new Font(null, Font.PLAIN, 16);
        for(int i=0; i<ALPHABET_COUNT; i++){
            keyButtons[i] = new JButton(); 
            //keyButtons[i].setSize(40, 25);
            keyButtons[i].setText(String.valueOf(keys.charAt(i))); // Each Key Represents its letter
            keyButtons[i].addActionListener(buttonsGameListener); // AllButtons Connected To Same Listener
            keyButtons[i].setActionCommand(String.valueOf(keys.charAt(i))); // Buttons Have Action Command Correspondint To Its Letter. Key a has command "a".
            
            
            keyButtons[i].setFont(buttonFont);
            
            bottomPanel.add(keyButtons[i]); // Add keyButtons to bottomPanel's grid. 
        }
        
        
    } //constructor end
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
    
    public void resetButtons(){
        for(int i=0; i<ALPHABET_COUNT; i++){
            if(!keyButtons[i].isEnabled()){
                keyButtons[i].setEnabled(true);
            }
        }
    }
    
    
}
