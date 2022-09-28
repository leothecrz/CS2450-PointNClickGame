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
        JButton button1 = new JButton();
        button1.setBorder(new RoundedBorder(120));
        button1.addActionListener(listener);
        button1.setBounds(450, 50, 120, 120);
        JButton button2 = new JButton();
        button2.setBorder(new RoundedBorder(120));
        button2.addActionListener(listener);
        button2.setBounds(380, 180, 120, 120);
        JButton button3 = new JButton();
        button3.setBorder(new RoundedBorder(120));
        button3.addActionListener(listener);
        button3.setBounds(250, 180, 120, 120);
        JButton button4 = new JButton();
        button4.setBorder(new RoundedBorder(120));
        button4.addActionListener(listener);
        button4.setBounds(120, 180, 120, 120);
        JButton button5 = new JButton();
        button5.setBorder(new RoundedBorder(120));
        button5.addActionListener(listener);
        button5.setBounds(50, 50, 120, 120);
        
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
    }
    
    
    
    
}
