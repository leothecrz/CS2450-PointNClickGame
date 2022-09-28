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
    }
    
    
    
    
}