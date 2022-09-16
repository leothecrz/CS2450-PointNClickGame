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
}
