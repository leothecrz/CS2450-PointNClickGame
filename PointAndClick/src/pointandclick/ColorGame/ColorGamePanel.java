package pointandclick.ColorGame;

import pointandclick.Hangman.HangmanGamePanel;
import pointandclick.MainMenu.ScorePanel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorGamePanel extends JPanel {
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private static final String[] COLOR_NAMES = {"RED", "YELLOW", "GREEN", "BLUE", "PURPLE"};
    private static final int[][] BUTTON_POSITIONS = {
        {490, 180},
        {370, 180},
        {240, 180},
        {130, 180},
        {10, 180}
    };
    private JLabel timeLabel;
    private JLabel colorLabel;
    private ColorButton[] colorButtons;
    private Timer timeLabelTimer;
    private Random random;
    private static final int MAX_ROUNDS = 5;
    private int rounds;
    public int playerScore; // score to be used in color game, should come from hangman game
    
    //Constructor
    public ColorGamePanel(ActionListener listener) {
        super();
        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        random = new Random();
        
        
        // Title
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Color Game Screen");
        add(titleLabel);

       
        
        // Time
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            timeLabel.setText(format.format(new Date())); 
        };
        
        timeLabel = new JLabel("");
        timeLabel.setBounds(400, 16, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        add(timeLabel);

        // Color buttons
        ColorButtonListener buttonListener = color -> {
            if (color.equals(colorLabel.getForeground())) {
                System.out.println("User selected the correct color");
                playerScore += 100; // for each correct round add 100 to score
            } else {
                System.out.println("User selected the incorrect color");
            }
            updateColor();
            shuffleButtons();
            
            if (++rounds == MAX_ROUNDS){
                rounds = 0;
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SwitchToScore")); // Should go to score screen
                playerScore = 0;
            }
         };
        
        colorButtons = new ColorButton[5];
        int i = 0;
        for (Color color : COLORS) {
            ColorButton button = new ColorButton(color, buttonListener);
            colorButtons[i++] = button;
            add(button);
        }
        shuffleButtons();
        

        // Color label
        colorLabel = new JLabel("", SwingConstants.CENTER);
        colorLabel.setBounds(210, 75, 200, 25);
        colorLabel.setFont(new Font("Marker Felt", Font.BOLD, 30));
        updateColor();
        add(colorLabel);
    }

    private void updateColor() {
        colorLabel.setText(COLOR_NAMES[random.nextInt(COLOR_NAMES.length)]);
        colorLabel.setForeground(COLORS[random.nextInt(COLORS.length)]);
    }

    private void shuffleButtons() {
        java.util.List<int[]> positions = Arrays.asList(BUTTON_POSITIONS); 
        Collections.shuffle(positions);
        int i = 0;
        for (int[] position : positions) {
            colorButtons[i++].setBounds(position[0] + (random.nextInt(21) - 10), position[1] + (random.nextInt(101) - 50), 100, 100);
        }
    }
}
