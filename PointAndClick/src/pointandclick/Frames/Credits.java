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
                Font titleFont = new Font("Marker Felt", Font.BOLD, 30);
                Font teamFont = new Font("Marker Felt", Font.PLAIN, 20);
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(titleFont);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString("Point and Click Game", 200, 80);
                g2.setFont(teamFont);
                g2.drawString("Team Goofy Goobers", 230, 300);
   
    }
}
