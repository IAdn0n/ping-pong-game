
package pingpong;

import entities.Ball;
import entities.Racket;
import entities.ScoreBoard;


import java.awt.Graphics;


public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    
    //game-loop
    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    
    public final static float SCALE = 0.5f;
    
    public final static int GAME_WIDTH = 800;
    public final static int GAME_HEIGHT = 600; 
    
    public final static int PLAYER_HEIGHT = GAME_HEIGHT/5;
    public final static int PLAYER_WIDTH = GAME_WIDTH/40;
    
    //entities
    private Ball ball;
    private Racket player1, player2;
    private ScoreBoard scoreBoard;
    
    
    
    
    public Game() {
        initClasses();
        
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        //request input focus
        gamePanel.requestFocus();
        
        
        //run the game
        startGameLoop();
    }
    
    private void initClasses() {
        ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, GAME_WIDTH/40, GAME_WIDTH/40);
        
        scoreBoard = new ScoreBoard();
        player1 = new Racket(PLAYER_WIDTH, GAME_HEIGHT/2, PLAYER_WIDTH,PLAYER_HEIGHT, 'b');
        player2 = new Racket(GAME_WIDTH - (PLAYER_WIDTH*2), GAME_HEIGHT/2, PLAYER_WIDTH, PLAYER_HEIGHT, 'r');
        
    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override //game-loop
    public void run() {
        
        //how long each frame lasts in nano seconds
        // 1 second == 1,000,000,000 nano seconds
        double timePerFrame = 1000000000.0 / FPS_SET; //how many nano seconds for 1 frame
        double timePerUpdate = 1000000000.0 / UPS_SET; //how many nano seconds for 1 update
        
        long previousTime = System.nanoTime();
        
        //fps, ups checker properties
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;

        while(true) {
            //if the duration had passed
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
                //update and check if the game has concluded
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                //render
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            
            //checks the frames/updates per second;
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        
    }
    
    public void update() {
        ball.update(this);
        player1.update();
        player2.update();
        //player1.updateAI(this);
        //player2.updateAI(this);
    }
    
    public void render(Graphics g) {
        g.clearRect((GAME_WIDTH/2)-((GAME_WIDTH/400)), 0, GAME_WIDTH/200, GAME_HEIGHT);
        scoreBoard.render(g);
        ball.render(g);
        player1.render(g);
        player2.render(g);
    }
    
    public Racket getPlayer1() {return player1;}
    public Racket getPlayer2() {return player2;}
    public Ball getBall() {return ball;}
    public ScoreBoard getScoreBoard() {return scoreBoard;}
}
