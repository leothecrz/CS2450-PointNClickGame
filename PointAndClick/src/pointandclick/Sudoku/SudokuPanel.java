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
    private boolean hasBeenSubmitted;
    private JLabel timeLabel;
    private Timer timeLabelTimer;

    public SudokuPanel(ActionListener listener) {
        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        this.sudokuScore = 0;
        this.hasBeenSubmitted = false;
        this.sudokuBoard = new SudokuBoard();
        sudokuBoard.setBounds(20, 10, 342, 342);
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
                    if(!hasBeenSubmitted){
                        hasBeenSubmitted = true;
                        this.sudokuScore = calculateScore();
                        if(sudokuScore < 540){
                            // Inform the player that they made an error in the puzzle.
                        }
                    } else {
                        if (calculateScore() == 540){
                            listener.actionPerformed(endGame);
                        } else {
                            JOptionPane.showMessageDialog(this, "Answer is incorrect.", "Try Again", JOptionPane.ERROR_MESSAGE);
                        }
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
            timeLabel.setText(format.format(new Date())); 
        };
        
        timeLabel = new JLabel("");
        timeLabel.setBounds(400, 16, 200, 25);
        timeLabel.setFont(new Font("Marker Felt", Font.BOLD, 15));
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        add(timeLabel);
    }
    
    public int getGameScore(){
        return this.sudokuScore;
    }

    private int calculateScore() {
        return sudokuBoard.calculateScore();
    }

    public void reset() {
        if(sudokuBoard.getBoardMade()){
            sudokuBoard.resetBoard();
        } else {
            sudokuBoard.setupBoard();
        }
        hasBeenSubmitted = false;
    }
}
