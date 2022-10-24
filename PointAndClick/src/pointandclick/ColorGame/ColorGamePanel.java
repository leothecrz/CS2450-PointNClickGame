package pointandclick.ColorGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ColorGamePanel extends JPanel {
    // Constants
    private static final Color[] COLORS = {new Color(0xD32F2F), new Color(0xFDD835), new Color(0x4CAF50), new Color(0x1565C0), new Color(0x7B1FA2)};
    private static final String[] COLOR_NAMES = {"RED", "YELLOW", "GREEN", "BLUE", "PURPLE"};

    // View
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel colorLabel;
    private ColorButton[] colorButtons;
    private Timer timeLabelTimer;

    // Model
    private Random random;
    private static final int MAX_ROUNDS = 5;
    private int rounds;
    private int playerScore;
    
    // Constructor
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
                playerScore += 100; // For each correct round, add 100 to score
                scoreLabel.setText("Score: " + String.valueOf(playerScore));
            }
            updateColor();
            shuffleButtons();
            
            if (++rounds == MAX_ROUNDS){
                rounds = 0;
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ColorGameEnd"));
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
        colorLabel.setBounds(210, 55, 200, 50);
        colorLabel.setFont(new Font("Marker Felt", Font.BOLD, 30));
        add(colorLabel);

        // Score label
        scoreLabel = new JLabel("Score: " + String.valueOf(playerScore), SwingConstants.CENTER);
        scoreLabel.setBounds(210, 100, 200, 25);
        scoreLabel.setFont(new Font("Marker Felt", Font.PLAIN, 24));
        add(scoreLabel);

        // Set color label text
        updateColor();

        // Keybind to shuffle buttons at an interval (for debugging purposes)
        Timer shuffleTimer = new Timer(1000, evt -> {
            shuffleButtons();
        });
        Action shuffleButtonsAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle timer
                if (!shuffleTimer.isRunning())
                    shuffleTimer.start();
                else
                    shuffleTimer.stop();
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK), "shuffleButtonsAction");
        getActionMap().put("shuffleButtonsAction", shuffleButtonsAction);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void resetPlayerScore() {
        playerScore = 0;
        scoreLabel.setText("Score: " + String.valueOf(playerScore));
    }

    private void updateColor() {
        String colorName = COLOR_NAMES[random.nextInt(COLOR_NAMES.length)];
        colorLabel.setText(colorName);
        colorLabel.setToolTipText(colorName);
        colorLabel.setForeground(COLORS[random.nextInt(COLORS.length)]);
    }
    
    /**
     * Shuffle button positions by randomly generating positions and checking for collision
     */
    private void shuffleButtons() {
        Rectangle[] boundingBoxes = new Rectangle[8];

        // Add boxes for labels so buttons do not collide with label
        boundingBoxes[0] = new Rectangle(410, 16, 200, 25); // Time label
        boundingBoxes[1] = new Rectangle(210, 75, 200, 25); // Color label
        boundingBoxes[2] = new Rectangle(210, 100, 200, 25); // Score label

        // Find position for each button
        for (int i = 3; i < 8; i++) {
            // Keep repeating until a non-colliding position is found
            boundingBoxes[i] = new Rectangle(getRandomNumber(20, 500), getRandomNumber(0, 250), 100, 100);
            while (isColliding(boundingBoxes[i], boundingBoxes)) {
                boundingBoxes[i].setRect(getRandomNumber(20, 500), getRandomNumber(0, 250), 100, 100);
            }

            // Set position of button once position has been found
            colorButtons[i - 3].setBounds(boundingBoxes[i]);
        }
    }

    /**
     * Checks if the specified rectangle is colliding with any of the other rectangles in the array
     */
    private boolean isColliding(Rectangle rectangle, Rectangle[] boxes) {
        boolean colliding = false;
        for (Rectangle box : boxes) {
            if (box == null || box == rectangle)
                continue;
            if (rectangle.intersects(box)) {
                colliding = true;
                break;
            }
        }
        return colliding;
    }

    /*
     * Generate a random number between min and max
     */
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
