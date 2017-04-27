package windowBuilder.views;

import java.awt.Image;
import java.awt.event.KeyEvent;


import windowBuilder.resources.ResourceLoader;

public class Player extends Sprite implements Config {

    private final int START_Y = 280;
    private final int START_X = 270;


    //private final String playerImg = "src/windowBuilder/resources/player.png";
    private final String playerImg = "player.png";
    private int width;
    int score = 0;
    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        
        //ImageIcon ii = new ImageIcon(playerImg);
        //width = ii.getImage().getWidth(null);

        Image ii = ResourceLoader.getImage(playerImg);
        width = ii.getWidth(null);
        setImage(ii);

        //setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    //x and y are instance variables inherited from Sprite.
    
    //move is horizontal only using the left or right arrow keys    
    public void move() {
        
        x += dx;
        
        if (x <= 2) {
            x = 2;
        }
        
        if (x >= SPACE_WIDTH - 2 * width) {
            x = SPACE_WIDTH - 2 * width;
        }
    }

    //When I get a left or right arrow keys pressed
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {        
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {        
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {        
            dx = 0;
        }
    }
    public int getScore(){
        return score;

    }
    public void addScore(){
        score++;
    }
    public void setScore(int i){
        score = i;
    }
}
