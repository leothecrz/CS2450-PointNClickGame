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
import javax.swing.*;
import java.awt.*;

public class ColorGamePanel extends JPanel {
    private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private static final String[] COLOR_NAMES = {"RED", "YELLOW", "GREEN", "BLUE", "MAGENTA"};
    private static final int[][] BUTTON_POSITIONS = {
        {450, 50},
        {380, 180},
        {250, 180},
        {120, 180},
        {50, 50}
    };
    private JLabel timeLabel;
    private Timer timeLabelTimer;

    //Constructor
    public ColorGamePanel(ActionListener listener) {
        super();
        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        
        JLabel tittleLabel = new JLabel();
        tittleLabel.setText("Button Game Screen");
        JButton endButton = new JButton();
        endButton.setBounds(500, 320, 70, 25);
        endButton.setText("END");
        endButton.setActionCommand("SwitchToScore");
        endButton.addActionListener(listener);
        
        add(tittleLabel);
        add(endButton);
        
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            timeLabel.setText(formatt.format(new Date())); 
        };
        
        // timer
        timeLabel = new JLabel("");
        timeLabel.setBounds(400, 16, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        
        add(timeLabel);

        ColorButtonListener buttonListener = color -> {

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
    }
}
