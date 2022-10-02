
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
import pointandclick.Frames.*;
import pointandclick.Frames.highscoreResources.ScoreTable;


public class PointAndClick extends JFrame {
    
    public static final String SCOREFILEPATH = "Data/highscore.txt"; 
    
    private CardLayout layout;
    private JPanel cards;
    
    private GameHandler gameHandler; 
    private HighScores highScores;

    private Font MarkerFelt; 
    
    /**
     * 
     * Main JFrame constructor. Handles the loading screens 3 second delay before menu. 
     * Handles the panel-switching logic for the main menu 
     * and the associated back buttons.
     * 
     * @param title - tittle shown on the status bar of the JFrame.
     */
    public PointAndClick(String title) {
        super(title); // Sets window title
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        new ScoreTable(SCOREFILEPATH); // file is created if not there already
        
        //Font Registration
        try{
           MarkerFelt = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/MarkerFelt.ttf"));
           GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
           ge.registerFont(MarkerFelt);
        }catch(FontFormatException | IOException ex){
            System.err.println("Font not Found - HighScores Jpanel");
        }
        
        ///Event listeners

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
        
    }
    

    /**
     * Entry Point of PointAndClick Program
     * 
     * @param args - main method default requirement
     */
    public static void main(String[] args) {
        
        new PointAndClick("Point and Click Game").setVisible(true);
        
    }
    
}
