
/**
 *      file: Credits.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: Credits Panel. A Panel that displays the
 *          names of those who designed and programed this
 *          software.
 */

package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pointandclick.Frames.commonResources.RoundedBorder;

public class Credits extends JPanel {
    
    public Credits(ActionListener listener) {
        setLayout(null);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);
        backButton.setFont(new Font("Marker Felt", Font.PLAIN, 20));
        backButton.setBounds(480,310,80,30);
        backButton.setBorder(new RoundedBorder(15));
        backButton.setContentAreaFilled(false);
        add(backButton);
    }
    
    /**
     * Override of the painComponent method of the JPanel Class.
     * Draws credits on the panel.
     * @param g - Graphics g to be cast to Graphics2D as g2
     */
   @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Font personFont = new Font("Marker Felt", Font.PLAIN, 18);
        Font creditsFont = new Font("Marker Felt", Font.BOLD, 35);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(creditsFont);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("Credits", 245,50);
        g2.setFont(personFont);
        g2.drawString("Leonardo Davalos, #", 165, 100);
        g2.drawString("Matthew Finerty, #", 165, 160);
        g2.drawString("Jasroop Singh, #", 165, 220);
        g2.drawString("Noris Tang, 4234", 165, 280);
        g2.drawString("Vivian Trieu, 9553", 165, 340);
    }
}
