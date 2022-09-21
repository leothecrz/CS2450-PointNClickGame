
/**
 *      file: LoadingScreen.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: A 3 second splash screen that is displayed
 *          at the start of the program.
 */

package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoadingScreen extends JPanel {
    
    /**
     * Sets up a JPanel with a Java Swing Timer to notify
     * PointAndClick to swap to menu.
     * @param listener 
     */
    public LoadingScreen(ActionListener listener) {
        setLayout(new FlowLayout());
        
        
        // Set up timer
        Timer timer = new Timer(3000, listener);
        timer.setInitialDelay(3000); // Trigger timer after 3 seconds
        timer.setRepeats(false);
        timer.start();
    }
    
    /**
     * Splash screen design is drawn directly onto the panel
     * @param g - Graphics g to be cast to Graphics2D as g2
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Font titleFont = new Font("Marker Felt", Font.BOLD, 60);
        Font teamFont = new Font("Marker Felt", Font.PLAIN, 30);
        //Font gameFont = new Font("Marker Felt", Font.PLAIN, 50);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(titleFont);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("Point and Click Game", 40, 80);
        g2.setFont(teamFont);
        g2.drawString("Team Goofy Goobers", 175, 320);
        // g2.setFont(gameFont);
        // g2.drawString("Hangman", 200, 200);
   
    }
}
