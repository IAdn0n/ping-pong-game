
package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pingpong.GamePanel;

public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;
    
    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            //player 1 buttons
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(false);
                break;
            
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(false);
                break;
            
            //player 2  buttons
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(false);
                break;
            
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            //ball buttons
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getBall().start();
                break;
            
            //player 1 buttons
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer1().setUp(true);
                break;
            
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer1().setDown(true);
                break;
            
            //player 2  buttons
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayer2().setUp(true);
                break;
            
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayer2().setDown(true);
                break;
            
                
        }
    }
}
