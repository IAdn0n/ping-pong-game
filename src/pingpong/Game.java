
package pingpong;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
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
    private Playing playing;
    private Menu menu;
    
    
    
    
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
        
        menu = new Menu(this);
        playing = new Playing(this);
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
        switch(Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }
    
    public void render(Graphics g) {

        switch(Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }
    
    public void windowFocusLost() {
       
    }
    
    public Menu getMenu() {return menu;}
    public Playing getPlaying() {return playing;}
    
}
