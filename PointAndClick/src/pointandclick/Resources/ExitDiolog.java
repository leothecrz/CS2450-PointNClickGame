
package pointandclick.Resources;

import javax.swing.JOptionPane;

/**
 * A JOptionPane set for the purpose of displaying an
 * exit question dialog. Uses saved string to format the
 *  dialog box. 0 is Yes. 1 is No.
 */
public final class ExitDiolog extends JOptionPane{

    private final static Object[] CHOICES = { "Yes", "No" };
    private final static String DIOLOGMESSAGE = "Exit the Game?";
    private final static String DIOLOGTITTLE = "Exit Diolog";
    
    public ExitDiolog() {
        super();
    }
    
    /**
     * Display the dialog box. 
     * @return 0 is Yes, 1 is No
     */
    public int getUserChoice(){
        return showOptionDialog(this, DIOLOGMESSAGE, DIOLOGTITTLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, CHOICES, CHOICES[0]);
    }
    
}
