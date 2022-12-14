
/**
 *      file: PongPaddle.java
 *      authors: Goofy Goobers Team
 *      class: CS2450 - User Interface Dsng and Prgmng
 * 
 *      assignment: Version 1.0
 * 
 *      purpose: displays the pong game. Handles the game loop, and listens for
 *          key events to pass to its paddles. Handles collision events that 
 *          involve both the ball and paddle. Handles win condition detection and 
 *          board reset.
 */

package pointandclick.Pong;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import pointandclick.Common.RoundedBorder;

public final class PongPanel extends JPanel {
    
    private final static int MILLISECONDS_BETWEEN_FRAMES = 33;
    
    private final Runnable focusGet;

    public Timer gameLoopTimer;
    private Ball pongBall;
    private Paddle paddle1;
    private Paddle paddle2;
    private JButton quitButton;
    private JLabel timeLabel;
    private Timer timeLabelTimer;
    
    private int player1Score;
    private int player2Score;
    private boolean gameRunning;
    
    /**
     * 
     * @param endOfGameListener 
     */
    public PongPanel(ActionListener endOfGameListener){
        super();
        this.setLayout(null);
        this.setSize(new Dimension(600, 400));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setVisible(true);
        
        gameRunning = false;
        player1Score = 0;
        player2Score = 0;
        focusGet = () -> {
            requestFocusInWindow();
        };

        KeyListener pongKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameRunning) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        playPong();
                    }
                } else {
                    paddle1.keyPressed(e);
                    paddle2.keyPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }
        };
        addKeyListener(pongKeyListener);
        
        quitButton = new JButton();
        quitButton.doClick();
        quitButton.setText("Quit");
        quitButton.setActionCommand("PongQuit");
        quitButton.addActionListener(endOfGameListener);
        quitButton.setBorder(new RoundedBorder(15));
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Marker Felt", Font.PLAIN, 12));
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(280, 10, 50, 25);
        quitButton.setFocusable(false);
        add(quitButton);

        // Time
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
        ActionListener timeListener = evt -> {
            String date = format.format(new Date());
            timeLabel.setText(date); 
            timeLabel.setToolTipText("Current time: " + date);
        };
        
        timeLabel = new JLabel("");
        timeLabel.setBounds(410, 12, 200, 25);
        timeLabel.setFont(new Font("Digital-7", Font.BOLD, 18));
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeListener.actionPerformed(null);
        timeLabelTimer = new Timer(1000, timeListener);
        timeLabelTimer.start();
        add(timeLabel);
        
        paddle1 = new Paddle((short)1, 30, 150);
        paddle2 = new Paddle((short)2, 555, 150);
        pongBall = new Ball();
        
        ActionListener gameLoop = evt -> {
            
            java.awt.Point nxtPos = pongBall.getNextPosition();
            java.awt.Point paddleNxtPos;
            
            double yDelta;
            
            if(pongBall.getXVelocity() < 0){ // going RIGHT
                
                paddleNxtPos = paddle1.nextPosition();
                if(nxtPos.y > paddleNxtPos.y && nxtPos.y < (paddleNxtPos.y + paddle1.height ) ){
                    
                    if(nxtPos.x <= (paddleNxtPos.x + paddle1.width) ){
                        
                        yDelta = ( (paddle1.getY() + ((paddle1.getHeight()/2))) ) - pongBall.y;
                        
                        if(pongBall.getXVelocity() > -25){
                            int newXVel = (int)(pongBall.getXVelocity() * (-1.1));
                            pongBall.setXVelocity(newXVel);
                        } else {
                            pongBall.setXVelocity( (int)(pongBall.getXVelocity() * (-0.8)) );
                        }
                        int newYVel = Math.round(-12 * (float)(yDelta/30));
                        pongBall.setYVelocity(newYVel);
                        
                        
                    }
                } else {
                    if(pongBall.x <= (paddleNxtPos.x) ){
                        player2Scored();
                    }
                }
            } else { // going LEFT
                
                paddleNxtPos = paddle2.nextPosition();
                if(nxtPos.y > paddleNxtPos.y && nxtPos.y < (paddleNxtPos.y + paddle2.getHeight() ) ){
                    
                    if(nxtPos.x >= paddleNxtPos.x){
                        
                        yDelta = ( (paddle2.getY() + ((paddle2.getHeight()/2))) ) - pongBall.y;
                        
                        if(pongBall.getXVelocity() < 25){
                            int newXVel = (int)(pongBall.getXVelocity() * (-1.1));
                            pongBall.setXVelocity(newXVel);
                        } else {
                            pongBall.setXVelocity( (int)(pongBall.getXVelocity() * (-0.8)) );
                        }
                        int newYVel = Math.round(-12 * (float)(yDelta/30));
                        pongBall.setYVelocity(newYVel);
                        
                    }
                }else {
                    
                    if(pongBall.x >= (paddleNxtPos.x + paddle2.width)){
                        player1Scored();
                    }
                }
            }
            
            pongBall.tickPass();
            paddle1.tickPass();
            paddle2.tickPass();
            
            repaint();
        };
        
        gameLoopTimer = new Timer(MILLISECONDS_BETWEEN_FRAMES, gameLoop);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Draw ball and paddles
        pongBall.draw(g2);
        paddle1.draw(g2);
        paddle2.draw(g2);
        
        // Draw scores
        g2.setFont(new Font("Digital-7", Font.PLAIN, 72));
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString(String.valueOf(player1Score), 204, 100);
        g2.drawString(String.valueOf(player2Score), 355, 100);

        if (!gameRunning) {
            String text;
            int fontSize;
            if (player1Score == 100) {
                text = "Player 1 wins!";
                fontSize = 48;
            } else if (player2Score == 100) {
                text = "Player 2 wins!";
                fontSize = 48;
            } else {
                text = "Press Space to start game.";
                fontSize = 24;
            }
            g2.setFont(new Font("Digital-7", Font.PLAIN, fontSize));
            g2.drawString(text, (600 - g2.getFontMetrics().stringWidth(text)) / 2, 300);
        }
    }
    
    /**
     * Start pong game
     */
    public void playPong() {
        pongBall.resetBall();
        paddle1.resetPaddle();
        paddle2.resetPaddle();
        gameRunning = true;
        if (player1Score == 100 || player2Score == 100) {
            resetScores();
        }
        gameLoopTimer.start();
    }
    
    /**
     * End pong game, reset paddle and ball
     */
    public void endPong() {
        gameLoopTimer.stop();
        pongBall.resetBall();
        paddle1.resetPaddle();
        paddle2.resetPaddle();
        gameRunning = false;
        repaint();
    }
    
    public void player1Scored(){
        System.out.println("p1 scored");
        player1Score += 10;
        pongBall.resetBall();
        endPong();
    }
    
    public void player2Scored(){
        System.out.println("p2 scored");
        player2Score += 10;
        pongBall.resetBall();
        endPong();
    }

    public void resetScores() {
        player1Score = 0;
        player2Score = 0;
    }

    public void getFocus(){
        //System.err.println(this.requestFocusInWindow());
        SwingUtilities.invokeLater(focusGet);
    }
    
}
