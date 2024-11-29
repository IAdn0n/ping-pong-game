
package pingpong;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
            
        //terminate the program ones you close the window
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //assemble the window
        jframe.add(gamePanel);
         
        jframe.setResizable(false);
        //create a window with prefered size of its component (JPanel)
        jframe.pack();
        //put the window on the center
        jframe.setLocationRelativeTo(null);
        
        
        
        //show window screen
        jframe.setVisible(true);
        
    }
    
    //method that terminates the window
    public void closeWindow() {
        jframe.dispose();   
    }
}
