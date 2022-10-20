/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointandclick.Pong;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author leothecrz
 */
public final class Ball extends Rectangle{
    
    private static int BALL_WIDTH = 15;
    private static int BALL_HEIGHT = BALL_WIDTH;
    
    private Color fillColor = Color.WHITE;
    
    int x;
    int y;
    double xVelocity;
    double yVelocity;
    
    public Ball(int x, int y){
        super(BALL_WIDTH, BALL_HEIGHT);
        this.x = x;
        this.y = y;
        setLocation(x, y);
    }
    
    public Color getFillColor(){
        return this.fillColor;
    }
    
}
