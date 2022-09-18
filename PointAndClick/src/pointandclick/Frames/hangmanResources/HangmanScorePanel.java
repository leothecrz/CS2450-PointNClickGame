package pointandclick.Frames.hangmanResources;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author LeothEcRz
 */
public class HangmanScorePanel extends JPanel{
    
    /**
     * 
     * @param endAndSkip
     */
    public HangmanScorePanel(ActionListener endAndSkip){
        super();
        setPreferredSize(new Dimension(600, 400));
        
        JButton endButton = new JButton();
        endButton.setText("END");
        endButton.addActionListener(endAndSkip);
        endButton.setActionCommand("End");
        
        add(endButton);
        
    }
    
}
