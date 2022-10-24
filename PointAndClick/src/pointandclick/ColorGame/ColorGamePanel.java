package pointandclick.ColorGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class ColorGamePanel extends JPanel {
    private static final Color[] COLORS = {new Color(0xD32F2F), new Color(0xFDD835), new Color(0x4CAF50), new Color(0x1565C0), new Color(0x7B1FA2)};
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
        
        // Time
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            String date = format.format(new Date());
            timeLabel.setText(date); 
            timeLabel.setToolTipText("Current time: " + date);
        };
        
        timeLabel = new JLabel("");
        timeLabel.setBounds(410, 16, 200, 25);
        timeLabel.setFont(new Font("Digital-7", Font.BOLD, 18));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        add(timeLabel);

        // Color buttons
        ColorButtonListener buttonListener = color -> {
            if (color.equals(colorLabel.getForeground())) {
                playerScore += 100; // for each correct round add 100 to score
            }
            updateColor();
            shuffleButtons();
            
            if (++rounds == MAX_ROUNDS){
                rounds = 0;
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ColorGameEnd"));
                playerScore = 0;
            }
        };
        
        colorButtons = new ColorButton[5];
        int i = 0;
        for (Color color : COLORS) {
            ColorButton button = new ColorButton(color, buttonListener);
            button.setToolTipText("Select color: " + COLOR_NAMES[i]);
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
        String colorName = COLOR_NAMES[random.nextInt(COLOR_NAMES.length)];
        colorLabel.setText(colorName);
        colorLabel.setToolTipText(colorName);
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
