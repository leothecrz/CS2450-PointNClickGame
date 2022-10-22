/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointandclick.Pong;

import java.awt.*;
import java.util.Random;
/**
 *
 * @author leothecrz
 */
public final class Ball extends Rectangle{
    
    private final static int BALL_WIDTH = 15;
    private final static int BALL_HEIGHT = BALL_WIDTH;
    
    private final static int X_SPAWN = 293;
    private final static int Y_SPAWN = 193;
    
    private Color fillColor = Color.WHITE;
    
    Random rand;
    
    private int xVelocity;
    private int yVelocity;
    
    public Ball(){
        super(BALL_WIDTH, BALL_HEIGHT);
        this.x = X_SPAWN;
        this.y = Y_SPAWN;
        
        this.xVelocity = 0;
        this.yVelocity = 0;
        
        setLocation(x, y);
   
        //rand = new Random();
        
        //gets either 0 or 1
        //int randXDir = rand.nextInt(2);
        //int randYDir = rand.nextInt(2);
        
        // ball move left when 0 is chosen randomly
        //if(randXDir == 0)
        //    randXDir--;
        //setXDir(randXDir);
        
        // ball move down when 0 is chosen randomly
        //if(randYDir == 0)
        //    randYDir--;
        //setYDir(randYDir);
          
    }
    
    public void tickPass(){
        this.x += this.xVelocity;
    }
    
    public void addYVelocity(int yVel){
        yVelocity += yVel;
    }
    
    public void addXVelocity(int xVel){
        xVelocity += xVel;
    }
    
    public void setXVelocity(int xVel) {
        xVelocity = xVel;       
    }
    
    public void setYVelocity(int yVel) {
        yVelocity = yVel;
    }
    
    
    public void resetBall(boolean goLeft){
        this.x = X_SPAWN;
        this.y = Y_SPAWN;
        resetVelocity();
        
        if(goLeft){
            this.xVelocity = -3;
            
        }else {
            this.xVelocity = 3;
        
        }
    }
    
    public void resetVelocity(){
        this.xVelocity = 0;
        this.yVelocity = 0;
    }
    
    public int getYVelocity(){
        return this.yVelocity;
    }
    
    public int getXVelocity(){
        return this.xVelocity;
    }
    
    
    public Point getPosition(){
        return new Point(this.x, this.y);
    }
    
    //draw ball
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x, y, BALL_WIDTH, BALL_HEIGHT);
    }
    
}
