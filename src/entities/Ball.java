
package entities;

import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import pingpong.Game;
import static utilz.HelpMethods.*;

public class Ball extends Entity{

    //the x vector and y vector for movement
    private float vecX = 0, vecY = 0; 
    
    public Ball(float x, float y, int width, int height) {
        super(x, y, width, height);
        color = Color.white;
    }
    
    //the ball goes to the center of the screen and vector resets to 0, 0
    public void reset() {
        x = Game.GAME_WIDTH/2;
        y = Game.GAME_HEIGHT/2;
        vecX = 0;
        vecY = 0;
        color = Color.white;
    }
    
    //a method to inital the balls vectors
    public void start() {
        if (vecX == 0 && vecY == 0) {
            vecX = 3;
            vecY = 0;
        }
    }
    
    public void update(Playing playing) {
        //update x and y positions
        nextYMovement(this, vecY);
        nextXMovement(this, vecX, playing);
    }
    public void render(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect((int)this.x, (int)this.y, this.width, this.height);
        
    }
    
    public float getVecX() {return vecX;}
    public float getVecY() {return vecY;}
    
    public void setVector(float vecX, float vecY) {
        this.vecX = vecX;
        this.vecY = vecY;
    }

}
