package pointandclick.Sudoku;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import pointandclick.Common.RoundedBorder;

public class SudokuPanel extends JPanel {
    
    private SudokuBoard sudokuBoard;
    private int sudokuScore;
    private JLabel timeLabel;
    private Timer timeLabelTimer;

    public SudokuPanel(ActionListener listener) {
        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        sudokuScore = 0;
        sudokuBoard = new SudokuBoard();
        sudokuBoard.setBounds(20, 10, 342, 342);
        sudokuBoard.setToolTipText("Sudoku Board");
        add(sudokuBoard);
        
        ActionListener sudokuListener = evt -> {
            ActionEvent endGame = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SudokuEnd");
            
            switch (evt.getActionCommand()) {
                case "SudokuQuit" -> {
                    this.sudokuScore = 0;
                    listener.actionPerformed(endGame);
                    break;
                }
                case "SudokuSubmit" -> {
                    if (sudokuBoard.isCorrect()) {
                        this.sudokuScore = sudokuBoard.calculateScore();
                        listener.actionPerformed(endGame);
                    } else {
                        JOptionPane.showMessageDialog(this, "Your answer is incorrect. Please try again.", "Try Again", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                default -> {
                    throw new AssertionError();
                }
            }
        };
        

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(sudokuListener);
        quitButton.setActionCommand("SudokuQuit");
        quitButton.setBorder(new RoundedBorder(15));
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(450, 240, 120, 40);
        quitButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        quitButton.setToolTipText("End Sudoku Game.");
        add(quitButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(sudokuListener);
        submitButton.setActionCommand("SudokuSubmit");
        submitButton.setBounds(450, 180, 120, 40);
        submitButton.setBorder(new RoundedBorder(15));
        submitButton.setContentAreaFilled(false);
        submitButton.setFont(new Font("Marker Felt", Font.PLAIN, 15));
        submitButton.setToolTipText("Submit answer.");
        add(submitButton);
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            String date = format.format(new Date());
            timeLabel.setText(date); 
            timeLabel.setToolTipText("Current time: " + date);
        };
        
        timeLabel = new JLabel("");
        timeLabel.setBounds(400, 16, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        add(timeLabel);
    }
    
    public int getScore() {
        return this.sudokuScore;
    }

    public void reset() {
        if(sudokuBoard.getBoardMade()) {
            sudokuBoard.resetBoard();
        } else {
            sudokuBoard.setupBoard();
        }
    }
}
