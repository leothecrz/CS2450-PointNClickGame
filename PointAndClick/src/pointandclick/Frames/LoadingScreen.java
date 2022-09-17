package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoadingScreen extends JPanel {
    // private final JLabel titleLabel;
    public LoadingScreen(ActionListener listener) {
        setLayout(new FlowLayout());
        //titleLabel = new JLabel("<html><div style='text-align: center;'>Point and Click Game<br />By: Team Goofy Goobers</div></html>");
        //add(titleLabel);
        
        // Set up timer

        Timer timer = new Timer(3000, listener);
        timer.setInitialDelay(3000); // Trigger timer after 3 seconds
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g){
                super.paintComponent(g);
                Font titleFont = new Font("Marker Felt", Font.BOLD, 25);
                Font teamFont = new Font("Marker Felt", Font.PLAIN, 15);
                Graphics2D g2 = (Graphics2D) g;
                g2.setFont(titleFont);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawString("Point and Click Game", 200, 80);
                g2.setFont(teamFont);
                g2.drawString("Team Goofy Goobers", 230, 300);
   
    }
}
