
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

package pointandclick.MainMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingScreen extends JPanel {
    
    /**
     * Sets up a JPanel with a Java Swing Timer to notify
     * PointAndClick to swap to menu.
     * @param loadingScreenListener
     */
    public LoadingScreen(ActionListener loadingScreenListener) {        
        // Set up timer
        Timer timer = new Timer(3000, loadingScreenListener);
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(new Color(0,0,0));
        g2.setFont(new Font("Marker Felt", Font.BOLD, 60));
        g2.drawString("Point and Click Game", 40, 150);
        
        g2.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        g2.drawString("- Team Goofy Goobers -", 175, 250);
    }
}
