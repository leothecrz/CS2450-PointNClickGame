/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointandclick.Frames.hangmanResources;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


/**
 *
 * @author LeothEcRz
 */
public class ButtonGamePanel extends JPanel{

    private JLabel timeLabel;
    private Timer timeLabelTimer;
    
    
    //Constructor
    public ButtonGamePanel(ActionListener listener) {
        super();
        setPreferredSize(new Dimension(600, 400));
        
        JLabel tittleLabel = new JLabel();
        tittleLabel.setText("Button Game Screen");
        JButton endButton = new JButton();
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
        
        /* Buttons added but need action command
        JButton button1 = new JButton();
        button1.setBorder(new RoundedBorder(50));
        button1.addActionListener(listener);
        JButton button2 = new JButton();
        button2.setBorder(new RoundedBorder(50));
        button2.addActionListener(listener);
        JButton button3 = new JButton();
        button3.setBorder(new RoundedBorder(50));
        button3.addActionListener(listener);
        JButton button4 = new JButton();
        button4.setBorder(new RoundedBorder(50));
        button4.addActionListener(listener);
        JButton button5 = new JButton();
        button5.setBorder(new RoundedBorder(50));
        button5.addActionListener(listener);
        
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5); */
        
    }
    
    
    
    
}
