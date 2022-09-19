package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Credits extends JPanel {
    public Credits(ActionListener listener) {
        setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);
        add(backButton);
    }
    
   @Override
    public void paintComponent(Graphics g){
                super.paintComponent(g);
                Font personFont = new Font("Marker Felt", Font.PLAIN, 18);
                Font creditsFont = new Font("Marker Felt", Font.BOLD, 35);
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(creditsFont);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString("Credits", 245,70);
                g2.setFont(personFont);
                g2.drawString("Leonardo Davalos, #", 165, 100);
                g2.drawString("Matthew Finerty, #", 165, 160);
                g2.drawString("Jasroop Singh, #", 165, 220);
                g2.drawString("Noris Tang, #", 165, 280);
                g2.drawString("Vivian Trieu, #", 165, 340);
    }
}
