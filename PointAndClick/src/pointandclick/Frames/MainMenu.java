package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

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
    
    // adding a picture below the buttons
    
    /* @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            pic = ImageIO.read(new File(Location of the picture, probably from source));
        } catch (IOException e) {} // getting some errors with catch statement
        g.drawImage(pic, 100, 100, 200, 200, this);
    } */
}
