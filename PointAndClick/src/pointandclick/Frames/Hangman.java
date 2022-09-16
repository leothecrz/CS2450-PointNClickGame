package pointandclick.Frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hangman extends JPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {}
    
    public Hangman() {
        setLayout(new FlowLayout());
        add(new JLabel("Hangman"));
    }
    
}
