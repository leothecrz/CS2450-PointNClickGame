package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingScreen extends JPanel {
    private JLabel titleLabel;
    private Timer timer;

    public LoadingScreen(ActionListener listener) {
        setLayout(new FlowLayout());
        titleLabel = new JLabel("<html><div style='text-align: center;'>Point and Click Game<br />By: Team Goofy Goobers</div></html>");
        add(titleLabel);

        // Set up timer
        timer = new Timer(3000, listener);
        timer.setInitialDelay(3000); // Trigger timer after 3 seconds
        timer.start();
    }

}
