
package pointandclick.Pong;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import pointandclick.Common.RoundedBorder;

/**
 *
 * @author leothecrz
 */
public final class PongPanel extends JPanel{
    
    private final static int MILLISECONDS_BETWEEN_FRAMES = 33;
    
    private final Runnable focusGet;

    public Timer gameLoopTimer;
    private Ball pongBall;
    private Paddle paddle1;
    private Paddle paddle2;
    
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
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }
        };
        addKeyListener(pongKeyListener);
        
        JButton quitButton = new JButton();
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
        
        pongBall.draw(g2);
        paddle1.draw(g2);
        paddle2.draw(g2);
        
        g2.setFont(new Font("MarkerFelt", Font.PLAIN, 72));
        g2.setColor(Color.LIGHT_GRAY);
        
        g2.drawString(String.valueOf(player1Score), 210, 100);
        g2.drawString(String.valueOf(player2Score), 360, 100);

        
    }
    
    /**
     * 
     */
    public void playPong(){
        pongBall.resetBall(true);
        paddle1.resetPaddle();
        paddle2.resetPaddle();
        gameRunning = true;
        gameLoopTimer.start();
        
    }
    
    /**
     * 
     */
    public void endPong(){
        gameLoopTimer.stop();
    }
    
    public void player1Scored(){
        System.out.println("p1 scored");
        player1Score += 10;
        pongBall.resetBall(true);
    }
    
    public void player2Scored(){
        System.out.println("p2 scored");
        player2Score += 10;
        pongBall.resetBall(false);
    }
    
    /**
     * 
     */
    public void getFocus(){
        //System.err.println(this.requestFocusInWindow());
        SwingUtilities.invokeLater(focusGet);
        
    }
    
}
