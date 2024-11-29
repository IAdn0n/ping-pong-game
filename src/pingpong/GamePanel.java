
package pingpong;

import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;
import java.awt.Color;

import static pingpong.Game.GAME_WIDTH;
import static pingpong.Game.GAME_HEIGHT;

public class GamePanel extends JPanel{
    private Game game;
    private MouseInputs mouseInputs;
    
    public GamePanel(Game game) {
        this.game = game;
        setBackground(Color.black);
        setPanelSize();
        
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
    //determine the size of the window
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT); 
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println(GAME_WIDTH + " : " + GAME_HEIGHT);       
    }
    
   

    //Method that let us paint on a panel
    //gets called everytime we use repaint() method (GAME-LOOP)
    @Override
    public void paintComponent(Graphics g) {
        //clear the screen
        super.paintComponent(g);  
        
        game.render(g);
    }
    
    //getter for game
    public Game getGame() {return game;}
}
