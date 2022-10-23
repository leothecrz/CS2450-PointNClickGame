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
          
    }
    
    /**
     * 
     */
    public void tickPass(){
        Point nxtPosition = getNextPosition();
        
        System.out.println(nxtPosition.toString());
        
        if(nxtPosition.y < 0){
            this.y = 0;
            this.yVelocity *= -1;
        } else if(nxtPosition.y >= 0 && nxtPosition.y <= 360){
            this.y = nxtPosition.y;
        } else {
            this.y = 360;
            this.yVelocity *= -1;
        }
        
        this.x = nxtPosition.x;
        
    }
    
    /**
     * 
     * @param yVel 
     */
    public void addYVelocity(int yVel){
        yVelocity += yVel;
    }
    
    /**
     * 
     * @param xVel 
     */
    public void addXVelocity(int xVel){
        xVelocity += xVel;
    }
    
    /**
     * 
     * @param xVel 
     */
    public void setXVelocity(int xVel) {
        xVelocity = xVel;       
    }
    
    /**
     * 
     * @param yVel 
     */
    public void setYVelocity(int yVel) {
        yVelocity = yVel;
    }
    
    /**
     * 
     * @param goLeft 
     */
    public void resetBall(boolean goLeft){
        this.x = X_SPAWN;
        this.y = Y_SPAWN;
        resetVelocity();
        
        if(goLeft){
            this.xVelocity = 1;
            this.yVelocity = -12;
            
        }else {
            this.xVelocity = 1;
            this.yVelocity = -12;
        
        }
    }
    
    /**
     * 
     */
    public void resetVelocity(){
        this.xVelocity = 0;
        this.yVelocity = 0;
    }
    
    /**
     * 
     * @return 
     */
    public int getYVelocity(){
        return this.yVelocity;
    }
    
    /**
     * 
     * @return 
     */
    public int getXVelocity(){
        return this.xVelocity;
    }
    
    /**
     * 
     * @return 
     */
    public Point getPosition(){
        return new Point(this.x, this.y);
    }
    
    /**
     * 
     * @return 
     */
    public Point getNextPosition(){
        return new Point(x+xVelocity, y+yVelocity);
    }
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x-(this.width/2), y-(this.height/2), this.width, this.height);
    }
    
}
