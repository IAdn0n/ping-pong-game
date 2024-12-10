
package entities;

import java.awt.Color;
import java.awt.Graphics;

import pingpong.Game;
import static utilz.HelpMethods.*;


public class Racket extends Entity{
    
    private boolean up, down;
    
    
    //shafts attributes
    private final float playerSpeed = ((float)Game.GAME_WIDTH / 100) * Game.SCALE;
    
    public Racket(float x, float y, int width, int height, char color) {
        super(x, y, width, height);
   
        this.color = (color == 'r')? Color.red : Color.blue;        
    }
   
    
    public void update() {
        updatePos();
    }
    public void render(Graphics g) {     
        
        g.setColor(color);
        g.fillRect((int)this.x, (int)this.y, this.width, this.height);
        
    }
    
    public void updatePos() {
        int ySpeed = 0;
        if (down) ySpeed += playerSpeed;
        if (up) ySpeed -= playerSpeed;
       
        //check the racket is at the edge of the screen
        if (down || up) {
            if (CanMoveHere(this.y + ySpeed, this.height)) this.y += ySpeed;
        }
    }
    
    //sets the middle of the racket to a certain position
    public void setMiddle(float y) {this.y = y-(height/2);}
    public void setUp(boolean up) {this.up = up;}
    public void setDown(boolean down) {this.down = down;}
}
