package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuPanel extends JPanel {
    private SudokuCell[][] cells;

    public SudokuPanel(ActionListener listener) {
        setLayout(new GridLayout(9, 9));

        cells = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
               cells[row][col] = new SudokuCell();
               add(cells[row][col]);
            }
        }
    }
}
