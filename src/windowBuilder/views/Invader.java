package windowBuilder.views;

import javax.swing.ImageIcon;

public class Invader extends Sprite {
	private Bomb bomb;
    private final String alienImg = "src/windowBuilder/resources/alien_saucer2.jpg"; //C:\SER215Labs\SpaceInvaders\src\windowBuilder\resources

    public Invader(int x, int y) {

        initInvader(x, y);
    }

    private void initInvader(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(alienImg);
        setImage(ii.getImage());
    }

    public void move(int direction) {
        
        this.x += direction;
    }

    public Bomb getBomb() {
        
        return bomb;
    }

    public class Bomb extends Sprite {

        private final String bombImg = "src/windowBuilder/resources/explode1.png";
        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        private void initBomb(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bombImg);
            setImage(ii.getImage());

        }

        public void setDestroyed(boolean destroyed) {
        
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
        
            return destroyed;
        }
    }
}
