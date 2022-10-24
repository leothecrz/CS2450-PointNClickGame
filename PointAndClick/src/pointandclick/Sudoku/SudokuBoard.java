
/**
 *      file: SudokuBoard.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.2
 * 
 *      purpose: handles board interactions and stores state. Can calculate score of the board and
 *          if it was correctly solved.
 */

package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuBoard extends JComponent implements ActionListener {
    private static final int[][] NUMBERS = new int[][]
    {
        {8, 3, 5, 4, 1, 6, 9, 2, 7},
        {2, 9, 6, 8, 5, 7, 4, 3, 1},
        {4, 1, 7, 2, 9, 3, 6, 5, 8},
        {5, 6, 9, 1, 3, 4, 7, 8, 2},
        {1, 2, 3, 6, 7, 8, 5, 4, 9},
        {7, 4, 8, 5, 2, 9, 1, 6, 3},
        {6, 5, 2, 7, 8, 1, 3, 9, 4},
        {9, 8, 1, 3, 4, 5, 2, 7, 6},
        {3, 7, 4, 9, 6, 2, 8, 1, 5},
    };

    private static final boolean[][] GIVEN = new boolean[][]
    {
        {true,  false, false, true,  false, true,  false, false, true },
        {false, false, false, false, false, false, true,  false, false},
        {false, true,  false, false, false, false, true,  true,  false},
        {true,  false, true,  false, true,  false, true,  true,  false},
        {false, false, false, false, true,  false, false, false, false},
        {false, true,  true,  false, true,  false, true,  false, true },
        {false, true,  true,  false, false, false, false, true,  false},
        {false, false, true,  false, false, false, false, false, false},
        {true,  false, false, true,  false, true,  false, false, true },
    };

    private SudokuCell[][] cells;
    private int selectedRow;
    private int selectedColumn;
    private boolean boardMade;

    public SudokuBoard() {
        setLayout(new GridLayout(9, 9));
        boardMade = false;
        setupListeners();
    }

    private void setupListeners() {
        // Set cell value when player presses 0-9 (0 clears the cell)
        Action numberAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1 && selectedColumn != -1)
                    cells[selectedRow][selectedColumn].setUserAnswer(Integer.parseInt(e.getActionCommand()));
            }
        };
        for (int i = 0; i <= 9; i++) {
            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i)), String.valueOf(i));
            getActionMap().put(String.valueOf(i), numberAction);
        }
        
        // Up key moves selection up
        Action moveUP = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cells[selectedRow][selectedColumn].setSelected(false);
                if(selectedRow > 0) {
                    selectedRow -= 1;
                }
                cells[selectedRow][selectedColumn].setSelected(true);
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UPKEY");
        getActionMap().put("UPKEY", moveUP);
        
        // Down key moves selection down
        Action moveDown = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cells[selectedRow][selectedColumn].setSelected(false);
                if(selectedRow < 8) {
                    selectedRow += 1;
                }
                cells[selectedRow][selectedColumn].setSelected(true);

            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWNKEY");
        getActionMap().put("DOWNKEY", moveDown);
        
        // Left key moves selection left
        Action moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cells[selectedRow][selectedColumn].setSelected(false);
                if(selectedColumn > 0) {
                    selectedColumn -= 1;
                }
                cells[selectedRow][selectedColumn].setSelected(true);

            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFTKEY");
        getActionMap().put("LEFTKEY", moveLeft);
        
        // Right key moves selection right
        Action moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cells[selectedRow][selectedColumn].setSelected(false);
                if(selectedColumn < 8) {
                    selectedColumn += 1;
                }
                cells[selectedRow][selectedColumn].setSelected(true);

            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHTKEY");
        getActionMap().put("RIGHTKEY", moveRight);

        // Shift + Z solves board (super duper secret)
        Action solveBoard = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int row = 0; row < 9; row++) {
                    for (int col = 0; col < 9; col++) {
                        SudokuCell cell = cells[row][col];
                        if (cell.given)
                            continue;
                        cell.setUserAnswer(NUMBERS[row][col]);
                    }
                }
            }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK), "solveBoard");
        getActionMap().put("solveBoard", solveBoard);
    }
    
    public void resetBoard() {
        for (SudokuCell[] row : cells) {
            for (SudokuCell cell : row) {
                cell.setSelected(false);
                cell.resetIncorrect();
                if (!cell.given) {
                    cell.setUserAnswer(0);
                }
            }
        }

        selectedRow = 0;
        selectedColumn = 0;
    }

    public void setupBoard() {
        cells = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new SudokuCell(row, col, NUMBERS[row][col], GIVEN[row][col], this);
                add(cells[row][col]);
            }
        }

        selectedRow = 0;
        selectedColumn = 0;
        boardMade = true;
    }

    public boolean isCorrect() {
        boolean correct = true;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = cells[row][col];
                if (!cell.isCorrect()) {
                    correct = false;
                    break;
                }
            }
            if (!correct) break;
        }
        return correct;
    }

    public int calculateScore() {
        int score = 540;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = cells[row][col];
                if (cell.given)
                    continue;
                if (!cell.isCorrect() || cell.getIncorrect())
                    score -= 10;
            }
        }
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SudokuCell cell = (SudokuCell) e.getSource(); // Get the cell that was clicked
        if (cell.given) // Don't select cells that were given
            return;
        if (selectedRow != -1 && selectedColumn != -1)
            cells[selectedRow][selectedColumn].setSelected(false); // Set the selected field of the previously selected cell to false
        selectedRow = cell.row;
        selectedColumn = cell.column;
        cell.setSelected(true);
    }
    
    public boolean getBoardMade() {
        return this.boardMade;
    }
}