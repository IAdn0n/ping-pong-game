
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pingpong.Game;
import static utilz.LoadSave.GetAtlas;


public class ScoreBoard {
    BufferedImage[] digits = new BufferedImage[10];
    //scores for player1 and player2
    int score1, score2;
    
    int imageIndex[][] = {{0, 0}, {0, 0}};
    final int WIDTH = ((Game.GAME_WIDTH/2) - (2*Game.PLAYER_WIDTH)) / 4;
    final int HEIGHT = Game.GAME_HEIGHT /5;
    
    public ScoreBoard() {
        String fileName = "digit_", extension = ".png";
        for (int i = 0; i < 10; i++) {
            //load the images into an array
            digits[i] = GetAtlas(fileName+i+extension);
        }
    }
    
    //method to increment player's score
    //if winner == 1 meaning player 1 scored
    //else if winner == -1 meaning player 2 scored
    public void increment(int winner) {
        if (winner == 1) {
            score1++;
            incrementImage(0);
        }
        
        else if (winner == -1) {
            score2++;
            incrementImage(1);
        }
    }
    
    //update the image
    private void incrementImage(int index) {
        imageIndex[index][0]++;
        if (imageIndex[index][0] == 10) {
            imageIndex[index][0] = 0;
            imageIndex[index][1] = (imageIndex[index][1] + 1) % 10;
        }
    }
    
    public void render(Graphics g) {
        int mid = Game.GAME_WIDTH/2;
        int gap = 2;
        //score board for player 1     
        g.drawImage(digits[imageIndex[0][0]], mid-WIDTH-gap, Game.GAME_HEIGHT/200, WIDTH, HEIGHT, null);
        g.drawImage(digits[imageIndex[0][1]], (mid-WIDTH*2)-gap, Game.GAME_HEIGHT/200, WIDTH, HEIGHT, null);
        
        //score board for player 2
        g.drawImage(digits[imageIndex[1][0]], mid+WIDTH+gap, Game.GAME_HEIGHT/200, WIDTH, HEIGHT, null);
        g.drawImage(digits[imageIndex[1][1]], mid+gap, Game.GAME_HEIGHT/200, WIDTH, HEIGHT, null);
    }
}
