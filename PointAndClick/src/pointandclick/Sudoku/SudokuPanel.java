package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pointandclick.Common.RoundedBorder;

public class SudokuPanel extends JPanel {
    private SudokuBoard sudokuBoard;

    public SudokuPanel(ActionListener listener) {
        // Can't get absolute positioning to work D: (probably just dumb and forgetting something small)
        // setLayout(null);

        sudokuBoard = new SudokuBoard();
        add(sudokuBoard);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(listener);
        quitButton.setActionCommand("SudokuQuit");
        quitButton.setBorder(new RoundedBorder(15));
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(450, 240, 120, 40);
        quitButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        add(quitButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(listener);
        submitButton.setActionCommand("SudokuSubmit");
        submitButton.setBounds(450, 180, 120, 40);
        submitButton.setBorder(new RoundedBorder(15));
        submitButton.setContentAreaFilled(false);
        submitButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        add(submitButton);
    }

    public int calculateScore() {
        return sudokuBoard.calculateScore();
    }

    public void reset() {
        sudokuBoard.setupBoard();
    }
}
