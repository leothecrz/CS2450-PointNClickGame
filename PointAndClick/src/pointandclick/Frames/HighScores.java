package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HighScores extends JPanel {
    public HighScores(ActionListener listener) {
        setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(listener);

        add(new JLabel("High Scores"));
        add(backButton);
    }
}
