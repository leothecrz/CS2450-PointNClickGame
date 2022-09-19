package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.IOException;

public class MainMenu extends JPanel {

    private BufferedImage pic = null;
    
    public MainMenu(ActionListener listener) {
        setLayout(new FlowLayout());
        
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(listener);
        highScoresButton.setActionCommand("HighScores");
        
        JButton creditsButton = new JButton("Credits");
        creditsButton.addActionListener(listener);
        creditsButton.setActionCommand("Credits");
        
        JButton playButton = new JButton("Play");
        playButton.addActionListener(listener);
        playButton.setActionCommand("Hangman");

        add(highScoresButton);
        add(creditsButton);
        add(playButton);
    }
    
    // adding a hangman picture below the main menu buttons
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            pic = ImageIO.read(new File(" *your own location* + /CS2450-PointNClickGame/hangman.png"));
        } catch (IOException e) {} 
        g.drawImage(pic, 200, 80, 200, 210, this);
    } 
}
