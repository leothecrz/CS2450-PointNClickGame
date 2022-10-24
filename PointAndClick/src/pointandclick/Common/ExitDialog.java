package pointandclick.Common;

import javax.swing.JOptionPane;

/**
 * A JOptionPane set for the purpose of displaying an
 * exit question dialog. Uses saved string to format the
 *  dialog box. 0 is Yes. 1 is No.
 */
public final class ExitDialog extends JOptionPane {
    private final static Object[] CHOICES = { "Yes", "No" };
    private final static String DIALOG_MESSAGE = "Exit the game?";
    private final static String DIALOG_TITLE = "Exit Game";
    
    public ExitDialog() {
        super();
    }
    
    /**
     * Display exit dialog box. 
     * @return True if user selected yes
     */
    public boolean shouldExit() {
        return showOptionDialog(this, DIALOG_MESSAGE, DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, CHOICES, CHOICES[0]) == 0;
    }
}
