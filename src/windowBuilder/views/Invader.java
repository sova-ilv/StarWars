package windowBuilder.views;

import java.awt.Image;

import windowBuilder.resources.ImagemProxy;

public class Invader extends Sprite {
	private Bomb bomb;
	private final String alienImg = "alien_saucer2.jpg";
	 
	public Invader(){
		//initInvader();
	}

    public void initInvader() {         

        bomb = new Bomb(this.x,this.y);
        ImagemProxy imagemProxy = new ImagemProxy(alienImg);
        Image ii = imagemProxy.carregarImagem();         
        setImage(ii);
    }

    public void move(int direction) {
        
        this.x += direction;
    }

    public Bomb getBomb() {
        
        return bomb;
    }

    public class Bomb extends Sprite {

        //private final String bombImg = "src/windowBuilder/resources/explode1.png";
    	private final String bombImg = "explode1.png";
        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImagemProxy imagemProxy = new ImagemProxy(bombImg);
            Image ii = imagemProxy.carregarImagem(); 
            setImage(ii);

        }

        public void setDestroyed(boolean destroyed) {
        
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
        
            return destroyed;
        }
    }
}