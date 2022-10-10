
/**
 *      file: PointAncClick.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: Entry point into the program and the main JFrame.
 *          Central Hub for handling interactions in the Menu Menu.
 *          Sets the program size restrictions and spawn point.
 */

package pointandclick;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import pointandclick.Common.*;
import pointandclick.MainMenu.*;

public class PointAndClick extends JFrame {
    public static final String SCORE_FILE_PATH = "Data/highscore.txt"; 
    public static ActionListener panelSwitcher;
    
    private CardLayout layout;
    private JPanel cards;
    
    private GameHandler gameHandler; 
    private HighScores highScores;
    
    /**
     * Main JFrame constructor. Handles the loading screens 3 second delay before menu. 
     * Handles the panel-switching logic for the main menu 
     * and the associated back buttons.
     * 
     * @param title - tittle shown on the status bar of the JFrame.
     */
    public PointAndClick(String title) {
        super(title); // Sets window title
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Font Registration
        try {
           GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
           ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fixFilePath("Fonts/MarkerFelt.ttf"))));
        } catch (FontFormatException | IOException ex){
            System.err.println("Font not found: MarkerFelt");
        }
        
        /// Event listeners
        // Splash screen listener
        ActionListener loadingScreenListener = evt -> {
            // Triggers after 3 seconds
            layout.show(cards, "MainMenu"); // Show the main menu
            ((Timer)evt.getSource()).stop(); // Stop the timer
        };

        // Main menu listener
        ActionListener mainMenuListener = evt -> {
            // Show the frame specified in the action command (see MainMenu class)
            // Action command should be the name of a class/frame
            
            if(evt.getActionCommand().equals(gameHandler.getClass().getSimpleName())){
                gameHandler.startGame();
            }
            
            if(evt.getActionCommand().equals(highScores.getClass().getSimpleName())){ // update highscore table to latest
                highScores.getScoreTable().loadScores();
            }
            
            layout.show(cards, evt.getActionCommand());
            
        };
        
        // Back button listener
        // Gets triggered when the back button in the HIGHSCORES or CREDITS screen gets pressed
        ActionListener backButtonListener = evt -> {
            layout.show(cards, "MainMenu");
        };
       
        // Create components
        LoadingScreen loadingScreen = new LoadingScreen(loadingScreenListener);
        MainMenu mainMenu = new MainMenu(mainMenuListener);
        highScores = new HighScores(backButtonListener);
        Credits credits = new Credits(backButtonListener);
        gameHandler = new GameHandler(backButtonListener); // gamePanel

        // Create the panel that contains the other frames
        layout = new CardLayout();
        cards = new JPanel(layout);
        
        cards.add(loadingScreen, loadingScreen.getClass().getSimpleName());
        cards.add(mainMenu, mainMenu.getClass().getSimpleName());
        cards.add(highScores, highScores.getClass().getSimpleName());
        cards.add(credits, credits.getClass().getSimpleName());
        cards.add(gameHandler, gameHandler.getClass().getSimpleName());
        
        add(cards);
        
        setSize(600, 400); // sets window size to 600W x 400H
        setResizable(false); // Forces Window to ALWAYS remain 600x400
        setLocationRelativeTo(null); // Centers window on the screen
        
        //Keybinds
        Action escapeKeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("ESCAPE KEY PRESSED");
                ExitDialog exitPopUp = new ExitDialog();
                if (exitPopUp.shouldExit()) {
                    dispose();
                    System.exit(0); // Will Brute Force Close regardless of what other threads are doing.
                }
            }
        };
        cards.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escapeKeyAction");
        cards.getActionMap().put("escapeKeyAction", escapeKeyAction);
        
        Action f1KeyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("The F1 Key was pressed");
                TeamInfoDialog TID = new TeamInfoDialog(getX(), getY());
                TID.setVisible(true);
            }
        };
        cards.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "f1KeyAction");
        cards.getActionMap().put("f1KeyAction", f1KeyAction);
    }
    

    /**
     * Entry Point of PointAndClick Program
     * 
     * @param args - main method default requirement
     */
    public static void main(String[] args) {
        new ScoreTable(SCORE_FILE_PATH); // file is created if not there already
        new PointAndClick("Point and Click Game").setVisible(true);
    }

    // Fixes relative file paths when running this program in Visual Studio Code
    public static String fixFilePath(String path) {
        return !(new File(path)).getAbsolutePath().contains("PointAndClick") ? ("PointAndClick/" + path) : path;
    }
    
}
