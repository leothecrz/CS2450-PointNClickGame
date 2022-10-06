package pointandclick.Resources;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 */
public final class TeamInfoDialog extends JDialog implements ActionListener{

    private static final String[] nameArray = {"   Leonardo #0000   ", "   Matthew #1111   ", "   Jasroop #2222   ", "   Noris #3333   ", "   Vivian #4444   "}; // 3 space padding
    private static final String DISPLAYTITTLE = "   Point And Click   ";
    private static final String TERMDISPLAY = "- Term: Fall 2022 -";
    private static final String WINDOWTITTLE = "Dev Team Information";
        
    public TeamInfoDialog(int X, int Y){
        super();
        this.setTitle(WINDOWTITTLE);
        this.setModal(true);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //this.setPreferredSize(new Dimension(375, 175));
        
        JLabel tittle = new JLabel();
        tittle.setText(DISPLAYTITTLE);
        tittle.setFont(new Font("Marker Felt", Font.PLAIN, 35));
            constraints.fill = GridBagConstraints.CENTER;
            constraints.weightx = 0.3;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 7;
        add(tittle, constraints);
        
        JLabel term = new JLabel();
        term.setText(TERMDISPLAY);
        term.setFont(new Font("Marker Felt", Font.PLAIN, 20));
            constraints.fill = GridBagConstraints.CENTER;
            constraints.weightx = 0.2;
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 5;
            constraints.ipady = 30;
        add(term, constraints);
        
        for(int i=0; i<nameArray.length; i++){
            
            JLabel activeLabel = new JLabel(nameArray[i]);
            activeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            activeLabel.setVerticalTextPosition(SwingConstants.CENTER);
            activeLabel.setFont(new Font("Marker Felt", Font.PLAIN, 15));
            
            constraints.fill = GridBagConstraints.CENTER;
            constraints.weightx = 0.2;
            constraints.gridwidth = 1;
            constraints.ipady =0;
            if(i<=2){
                constraints.gridx = i;
                constraints.gridy = 2;
            } else {
                if(i==3){
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
        this.setLocation(new Point(X+(getWidth()/2), Y+(getHeight()/2)) );
        getRootPane().setDefaultButton(okButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        dispose();
    }
}
