package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel {

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
    
}
