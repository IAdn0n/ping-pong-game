
package entities;

import java.awt.Color;


public abstract class Entity {
    //x and y are the position on an entity
    protected float x, y;
    //width and height are the size of the entity(hitbox)
    protected int width, height;
    
     //colors
    protected Color color;
    
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
    }
    
    public float getY() {return y;}
    public float getX() {return x;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Color getColor() {return color;}
    
    public void setY(float y) {this.y = y;}
    public void setX(float x) {this.x = x;} 
    public void setColor(Color color) {this.color = color;}
    
}
