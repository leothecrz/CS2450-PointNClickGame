package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuPanel extends JPanel {
    private SudokuBoard sudokuBoard;

    public SudokuPanel(ActionListener listener) {
        sudokuBoard = new SudokuBoard();
        add(sudokuBoard);
    }
}
