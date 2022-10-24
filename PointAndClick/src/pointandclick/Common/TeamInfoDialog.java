package pointandclick.Common;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 */
public final class TeamInfoDialog extends JDialog implements ActionListener{
    private static final String[] NAMES = {"   Leonardo #0000   ", "   Matthew #1111   ", "   Jasroop #2222   ", "   Noris #3333   ", "   Vivian #4444   "}; // 3 space padding
    private static final String DISPLAY_TITLE = "   Point And Click   ";
    private static final String TERM_DISPLAY = "- Term: Fall 2022 -";
    private static final String WINDOW_TITLE = "Dev Team Information";
        
    public TeamInfoDialog(int X, int Y){
        super();
        this.setTitle(WINDOW_TITLE);
        this.setModal(true);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //this.setPreferredSize(new Dimension(375, 175));
        
        JLabel title = new JLabel();
        title.setText(DISPLAY_TITLE);
        title.setFont(new Font("Marker Felt", Font.PLAIN, 35));
        constraints.fill = GridBagConstraints.CENTER;
        constraints.weightx = 0.3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 7;
        add(title, constraints);
        
        JLabel term = new JLabel();
        term.setText(TERM_DISPLAY);
        term.setFont(new Font("Marker Felt", Font.PLAIN, 20));
        constraints.fill = GridBagConstraints.CENTER;
        constraints.weightx = 0.2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 5;
        constraints.ipady = 30;
        add(term, constraints);
        
        for (int i = 0; i < NAMES.length; i++) {
            JLabel activeLabel = new JLabel(NAMES[i]);
            activeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            activeLabel.setVerticalTextPosition(SwingConstants.CENTER);
            activeLabel.setFont(new Font("Marker Felt", Font.PLAIN, 15));
            
            constraints.fill = GridBagConstraints.CENTER;
            constraints.weightx = 0.2;
            constraints.gridwidth = 1;
            constraints.ipady =0;
            if(i <= 2) {
                constraints.gridx = i;
                constraints.gridy = 2;
            } else {
                if (i == 3) {
                    constraints.gridx = 0;
                } else {
                    constraints.gridx = 2;
                }
                constraints.gridy = 3;
            }
            add(activeLabel, constraints);
        }
        
        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(this);
        constraints.fill = GridBagConstraints.CENTER;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        add(okButton, constraints);
        
        JLabel whiteSpace = new JLabel("-");
        constraints.fill = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 7;
        add(whiteSpace, constraints);
        
        pack();
        setLocation(new Point(X + (getWidth() / 2), Y + (getHeight() / 2)));
        getRootPane().setDefaultButton(okButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        dispose();
    }
}
