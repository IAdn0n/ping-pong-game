
package gamestates;

import entities.Ball;
import entities.Racket;
import entities.ScoreBoard;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import pingpong.Game;
import static pingpong.Game.GAME_HEIGHT;
import static pingpong.Game.GAME_WIDTH;
import static pingpong.Game.PLAYER_HEIGHT;
import static pingpong.Game.PLAYER_WIDTH;



public class Playing extends State implements Statemethods{
    private Ball ball;
    private Racket player1, player2;
    private ScoreBoard scoreBoard;
    
    public Playing(Game game) {
        super(game);
        initClasses();
    }
    
    private void initClasses() {
        ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, GAME_WIDTH/40, GAME_WIDTH/40);
        
        scoreBoard = new ScoreBoard();
        player1 = new Racket(PLAYER_WIDTH, GAME_HEIGHT/2, PLAYER_WIDTH,PLAYER_HEIGHT, 'b');
        player2 = new Racket(GAME_WIDTH - (PLAYER_WIDTH*2), GAME_HEIGHT/2, PLAYER_WIDTH, PLAYER_HEIGHT, 'r');
        
    }
    
    @Override
    public void update() {
        ball.update(this);
        player1.update();
        player2.update();
        //player1.updateAI(this);
        //player2.updateAI(this);
    }

    @Override
    public void draw(Graphics g) {
        g.clearRect((GAME_WIDTH/2)-((GAME_WIDTH/400)), 0, GAME_WIDTH/200, GAME_HEIGHT);
        scoreBoard.render(g);
        ball.render(g);
        player1.render(g);
        player2.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            //ball buttons
            case KeyEvent.VK_SPACE:
                ball.start();
                break;
            
            //player 1 buttons
            case KeyEvent.VK_W:
                player1.setUp(true);
                break;
            
            case KeyEvent.VK_S:
                player1.setDown(true);
                break;
            
            //player 2  buttons
            case KeyEvent.VK_UP:
                player2.setUp(true);
                break;
            
            case KeyEvent.VK_DOWN:
                player2.setDown(true);
                break;
            
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            //player 1 buttons
            case KeyEvent.VK_W:
                player1.setUp(false);
                break;
            
            case KeyEvent.VK_S:
                player1.setDown(false);
                break;
            
            //player 2  buttons
            case KeyEvent.VK_UP:
                player2.setUp(false);
                break;
            
            case KeyEvent.VK_DOWN:
                player2.setDown(false);
                break;
        }
    }
    
    public Racket getPlayer1() {return player1;}
    public Racket getPlayer2() {return player2;}
    public Ball getBall() {return ball;}
    public ScoreBoard getScoreBoard() {return scoreBoard;}


}
