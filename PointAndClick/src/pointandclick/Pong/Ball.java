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
    
    private static int BALL_WIDTH = 15;
    private static int BALL_HEIGHT = BALL_WIDTH;
    
    
    private Color fillColor = Color.WHITE;
    
    Random rand;
    int x;
    int y;
    double xVelocity;
    double yVelocity;
    
    public Ball(int x, int y){
        super(BALL_WIDTH, BALL_HEIGHT);
        this.x = x;
        this.y = y;
        setLocation(x, y);
   
        rand = new Random();
        
        //gets either 0 or 1
        int randXDir = rand.nextInt(2);
        int randYDir = rand.nextInt(2);
        
        // ball move left when 0 is chosen randomly
        if(randXDir == 0)
            randXDir--;
        setXDir(randXDir);
        
        // ball move down when 0 is chosen randomly
        if(randYDir == 0)
            randYDir--;
        setYDir(randYDir);
       
    }
    
    //set x direction
    public void setXDir(int xDir) {
        xVelocity = xDir; 
        
        // not fully completed yet
    }
    
    //set y direction
    public void setYDir(int yDir) {
        yVelocity = yDir;
        
        // not fully completed yet
    }
    
    //draw ball
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x, y, BALL_WIDTH, BALL_HEIGHT);
    }
    
}
