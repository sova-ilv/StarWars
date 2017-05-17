package windowBuilder.views;

import java.awt.Image;
import java.awt.event.KeyEvent;

import windowBuilder.resources.ImagemProxy;


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
        
    	ImagemProxy imagemProxy = new ImagemProxy(playerImg);
        Image ii = imagemProxy.carregarImagem(); 
        width = ii.getWidth(null);
        setImage(ii);

        setX(START_X);
        setY(START_Y);
    }
    
    public void move() {
        
        x += dx;
        
        if (x <= 2) {
            x = 2;
        }
        
        if (x >= SPACE_WIDTH - 2 * width) {
            x = SPACE_WIDTH - 2 * width;
        }
    }

    
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