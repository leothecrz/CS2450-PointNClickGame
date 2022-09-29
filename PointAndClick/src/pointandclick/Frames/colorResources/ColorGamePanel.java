package pointandclick.Frames.colorResources;

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
import pointandclick.Frames.hangmanResources.HangmanGamePanel;
import pointandclick.Frames.hangmanResources.HangmanScorePanel;

public class ColorGamePanel extends JPanel {
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private static final String[] COLOR_NAMES = {"RED", "YELLOW", "GREEN", "BLUE", "PURPLE"};
    private static final int[][] BUTTON_POSITIONS = {
        {440, 50},
        {370, 180},
        {240, 180},
        {110, 180},
        {40, 50}
    };
    private JLabel timeLabel;
    private JLabel colorLabel;
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

        // End
        JButton endButton = new JButton();
        endButton.setBounds(500, 320, 70, 25);
        endButton.setText("END");
        endButton.setActionCommand("SwitchToScore");
        endButton.addActionListener(listener);
        add(endButton);
        
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
            
            if (++rounds == MAX_ROUNDS){
                rounds = 0;
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SwitchToScore")); // Should go to score screen
            }
         };
        
        java.util.List<int[]> positions = Arrays.asList(BUTTON_POSITIONS); 
        Collections.shuffle(positions);
        int i = 0;
        for (int[] position : positions) {
            int index = i++;
            ColorButton button = new ColorButton(COLORS[index], COLOR_NAMES[index], buttonListener);
            button.setBounds(position[0], position[1], 120, 120);
            add(button);
        }

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
}
