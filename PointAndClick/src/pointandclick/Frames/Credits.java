package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Credits extends JPanel {
    public Credits(ActionListener listener) {
        setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);

        add(new JLabel("Credits"));
        add(backButton);
    }
    
    @Override
    public void paintComponent(Graphics g){
                super.paintComponent(g);
                Font personFont = new Font("Courier New", Font.PLAIN, 20);
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(personFont);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString("Leonardo Davalos, #", 150, 120);
                g2.drawString("Matthew Finerty, #", 150, 180);
                g2.drawString("Jasroop Singh, #", 150, 240);
                g2.drawString("Noris Tang, #", 150, 300);
                g2.drawString("Vivian Trieu, #", 150, 360);
    }
}
