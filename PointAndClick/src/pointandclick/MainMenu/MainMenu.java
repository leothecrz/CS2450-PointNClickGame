
/**
 *      file: PointAncClick.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: The Main menu panel that is displayed.
 *          Holds 3 action buttons. High scores. Credits. Play
 */

package pointandclick.MainMenu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import pointandclick.PointAndClick;
import pointandclick.Common.RoundedBorder;

import java.io.IOException;

public class MainMenu extends JPanel {

    private BufferedImage pic;
    
    public MainMenu(ActionListener mainMenuListener) {
        setLayout(null);
        
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(mainMenuListener);
        highScoresButton.setActionCommand("HighScores");
        highScoresButton.setBounds(450, 180, 120, 40);
        highScoresButton.setBorder(new RoundedBorder(15));
        highScoresButton.setContentAreaFilled(false);
        highScoresButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        highScoresButton.setToolTipText("Go to the highscore panel.");
        add(highScoresButton);
        
        JButton creditsButton = new JButton("Credits");
        creditsButton.addActionListener(mainMenuListener);
        creditsButton.setActionCommand("Credits");
        creditsButton.setBorder(new RoundedBorder(15));
        creditsButton.setContentAreaFilled(false);
        creditsButton.setBounds(450, 240, 120, 40);
        creditsButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        creditsButton.setToolTipText("Go to the credits panel.");
        add(creditsButton);
        
        JButton playButton = new JButton("Play");
        playButton.addActionListener(mainMenuListener);
        playButton.setActionCommand("GameHandler");
        playButton.setBounds(450, 300, 120, 40);
        playButton.setBorder(new RoundedBorder(15));
        playButton.setContentAreaFilled(false);
        playButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        playButton.setToolTipText("Start the minigames.");
        add(playButton);
        
        JButton playPongButton = new JButton("Play PONG");
        playPongButton.addActionListener(mainMenuListener);
        playPongButton.setActionCommand("Pong");
        playPongButton.setBounds(450, 120, 120, 40);
        playPongButton.setBorder(new RoundedBorder(15));
        playPongButton.setContentAreaFilled(false);
        playPongButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        playPongButton.setToolTipText("Start Pong");
        add(playPongButton);

        add(highScoresButton);
        add(creditsButton);
        add(playButton);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            pic = ImageIO.read(new File(PointAndClick.fixFilePath("Images/menuIcon.png"))); 
            g.drawImage(pic, 80, 20, 300, 310, this);
        } catch (IOException e) {
            System.err.println("MainMenu: image not found");
        } 
        
    } 
}

