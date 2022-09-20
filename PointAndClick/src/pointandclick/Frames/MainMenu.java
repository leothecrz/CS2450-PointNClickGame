package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.IOException;
import javax.swing.border.Border;

public class MainMenu extends JPanel {

    private BufferedImage pic = null;
    
    public MainMenu(ActionListener listener) {
        setLayout(null);
        
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(listener);
        highScoresButton.setActionCommand("HighScores");
        highScoresButton.setBounds(450, 180, 120, 40);
        highScoresButton.setBorder(new RoundedBorder(15));
        highScoresButton.setContentAreaFilled(false);
        highScoresButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        add(highScoresButton);
        
        JButton creditsButton = new JButton("Credits");
        creditsButton.addActionListener(listener);
        creditsButton.setActionCommand("Credits");
        creditsButton.setBorder(new RoundedBorder(15));
        creditsButton.setContentAreaFilled(false);
        creditsButton.setBounds(450, 240, 120, 40);
        creditsButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        add(creditsButton);
        
        JButton playButton = new JButton("Play");
        playButton.addActionListener(listener);
        playButton.setActionCommand("Hangman");
        playButton.setBounds(450, 300, 120, 40);
        playButton.setBorder(new RoundedBorder(15));
        playButton.setContentAreaFilled(false);
        playButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        add(playButton);

        add(highScoresButton);
        add(creditsButton);
        add(playButton);
    }
    
    // adding a hangman picture below the main menu buttons
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        try {
            
            pic = ImageIO.read(MainMenu.class.getResource("/icons/icon.png")); 
        } catch (IOException e) {} 
        g.drawImage(pic, 80, 20, 300, 310, this);
    } 
    
    public class RoundedBorder implements Border {

        public int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        @Override
        public boolean isBorderOpaque() {
            return true;
        }


        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
