package pointandclick.Frames.colorResources;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import pointandclick.Frames.commonResources.RoundedBorder;

public class ColorGamePanel extends JPanel{

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
        timeLabel.setBounds(400, 40, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        
        add(timeLabel);
        
        // hardcoded the buttons in specifc places 
        ColorButton redButton = new ColorButton(Color.RED, listener);
        redButton.setBounds(450, 50, 120, 120);
        ColorButton yellowButton = new ColorButton(Color.YELLOW, listener);
        yellowButton.setBounds(380, 180, 120, 120);
        ColorButton greenButton = new ColorButton(Color.GREEN, listener);
        greenButton.setBounds(250, 180, 120, 120);
        ColorButton blueButton = new ColorButton(Color.BLUE, listener);
        blueButton.setBounds(120, 180, 120, 120);
        ColorButton purpleButton = new ColorButton(Color.MAGENTA, listener);
        purpleButton.setBounds(50, 50, 120, 120);
        
        add(redButton);
        add(yellowButton);
        add(greenButton);
        add(blueButton);
        add(purpleButton);
    }
}
