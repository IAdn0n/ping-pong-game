
package utilz;

import entities.Ball;
import entities.Racket;
import pingpong.Game;


public class HelpMethods {
    public final static float DIRECTION_CHANGE_EDGE = 0.25f;
    public final static float DIRECTION_CHANGE = DIRECTION_CHANGE_EDGE / 2;
    //method that check if it is allowed to move to a specific location
    // COLLISION DETECTION
    public static boolean CanMoveHere(float y, float height) {
        
        return !(y < 0 || y + height > Game.GAME_HEIGHT);
        
    }   
    
    //method to check if we can move or if we hit the ceiling/floor
    public static void nextYMovement(Ball ball, float speed) {
        if (ball.getY() + speed > Game.GAME_HEIGHT-20) {
            //hug the wall
            ball.setY(Game.GAME_HEIGHT-20);
            redirect(ball, 1, -1, 0);
        }
        else if (ball.getY() + speed < 0) {
            ball.setY(0);
            redirect(ball, 1, -1, 0);
        }
        else 
            ball.setY(ball.getY() + speed);
    }
    
    //method to check if we hit the racket or didnt
    public static void nextXMovement(Ball ball, float speed, Game game) {
        
        float nextX = ball.getX() + speed;

        
        //passed the player1's racket
        if (nextX <= game.getPlayer1().getX()) {
            game.getScoreBoard().increment(-1);
            ball.reset();
        }

        //hit the player1 racket
        else if (nextX <= game.getPlayer1().getX()+game.getPlayer1().getWidth()) {
            if (HitPlayer1(ball, game.getPlayer1())) {
                ball.setColor(game.getPlayer1().getColor());
                return;
            }
        }
        //passed the player2's racket
        else if (nextX+ball.getWidth() >= game.getPlayer2().getX() + game.getPlayer2().getWidth()){
            game.getScoreBoard().increment(1);
            ball.reset();
        }
        
        //hit the player2's racket
        else if (nextX+ball.getWidth() >= game.getPlayer2().getX()) {
            if (HitPlayer2(ball, game.getPlayer2())) {
                ball.setColor(game.getPlayer2().getColor());
                return;
            }
        }

        
        ball.setX(ball.getX() +speed);
    }
    
    
    //check the Y-axis to check if the ball hit the racket according to y-axis
    private static boolean HitPlayer1(Ball ball, Racket player) {
        float nextPos = ball.getY();
        //racket points for player1
        float point1 = player.getY();
        float point2= point1 + player.getHeight();
        
        //if upper left corner or lower left corner of the ball hit the racket
        if ((nextPos >= point1 && nextPos <= point2) || (nextPos+ball.getHeight() >= point1 && nextPos+ball.getHeight() <= point2)) {
            //make the ball hug the racket and the redirect about x-axis
            ball.setX(player.getX()+player.getWidth());
            
            float midPoint = ball.getY() + (ball.getHeight()/2);
            redirect(ball, -1, 1, changeInDirection(midPoint, player.getY(), player.getHeight()));
            return true;
        }
        
        return false;
       
    }
    
    //same as HitPlayer1 but for player2
    private static boolean HitPlayer2(Ball ball, Racket player) {
        float nextPos = ball.getY();
        
        float point1 = player.getY();
        float point2 = point1 + player.getHeight();
        
        //if upper right corner or lower right corner of ball hit the racket
        if ((nextPos >= point1 && nextPos <= point2) || (nextPos+ball.getHeight() >= point1 && nextPos+ball.getHeight() <= point2)) {
            //make the ball hug the racket and the redirect about x-axis
            ball.setX(player.getX()-ball.getWidth());
            
            float midPoint = ball.getY() + (ball.getHeight()/2);
            redirect(ball, -1, 1, changeInDirection(midPoint, player.getY(), player.getHeight()));
            return true;
        }
        
        return false;
    }
    
    private static float changeInDirection(float midPoint, float y, int height) {
        if (midPoint <= y + (height/4)) //first quarter
            return -1 * DIRECTION_CHANGE_EDGE;
        else if (midPoint <= y + (height/2)) //second quarter
            return -1 * DIRECTION_CHANGE;
        else if (midPoint > y + (height/2)) //third quarter
            return DIRECTION_CHANGE;
        else  //forth quarter
            return DIRECTION_CHANGE_EDGE;
        
    }
    
    
    //redirects balls vector
    private static void redirect(Ball ball, int x, int y, float yChange) {
        float xChange = 0;
        
        //if it hits the 1st or 2nd quarter
        if (yChange < 0) {
            //if going down we increment x else decrement x
            xChange = (y > 0)? yChange :  -1 * yChange;
        }
        //3rd and 4th quarter
        else if (yChange > 0) {
            xChange = (y > 0)? -1 * yChange: yChange;
        }
        ball.setVector((ball.getVecX() * x) + xChange, (ball.getVecY() * y) + yChange);
    }
    
}
