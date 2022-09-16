/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pointandclick;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pointandclick.Frames.*;

/**
 *
 * @author leothecrz
 */
public class PointAndClick extends JFrame {
    private CardLayout layout;
    private JPanel cards;

    public static void main(String[] args) {
        // Create a 600x400 window
        PointAndClick pac = new PointAndClick("Point and Click Game");
        pac.setSize(600, 400);
        pac.setLocationRelativeTo(null); // Centers window on the screen
        pac.setVisible(true); // Show window
    }

    // Maybe keep all panel-switching logic in this class just to make things easier?
    public PointAndClick(String title) {
        super(title); // Sets window title
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        ///region Event listeners

        // Gets triggered after 3 seconds
        ActionListener loadingScreenListener = evt -> {
            // Triggers after 3 seconds
            layout.show(cards, "MainMenu"); // Show the main menu
            ((Timer)evt.getSource()).stop(); // Stop the timer
        };

        // Gets triggered when a button in the main menu gets pressed
        ActionListener mainMenuListener = evt -> {
            // Show the frame specified in the action command (see MainMenu class)
            // Action command should be the name of a class/frame
            layout.show(cards, evt.getActionCommand());
        };

        // Gets triggered when the back button in the high scores or credits screen gets pressed
        ActionListener backButtonListener = evt -> {
            layout.show(cards, "MainMenu");
        };
        ///endregion

        // Create components
        LoadingScreen loadingScreen = new LoadingScreen(loadingScreenListener);
        MainMenu mainMenu = new MainMenu(mainMenuListener);
        HighScores highScores = new HighScores(backButtonListener);
        Credits credits = new Credits(backButtonListener);
        Hangman hangman = new Hangman();
         
        // Create the panel that contains the other frames
        layout = new CardLayout();
        cards = new JPanel(layout);
        cards.add(loadingScreen, loadingScreen.getClass().getSimpleName());
        cards.add(mainMenu, mainMenu.getClass().getSimpleName());
        cards.add(highScores, highScores.getClass().getSimpleName());
        cards.add(credits, credits.getClass().getSimpleName());
        cards.add(hangman, hangman.getClass().getSimpleName());
        add(cards);
    }
    
}
