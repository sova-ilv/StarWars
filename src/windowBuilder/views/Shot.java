package windowBuilder.views;

import java.awt.Image;

import windowBuilder.resources.ImagemProxy;

public class Shot extends Sprite {

    //private final String shotImg = "src/windowBuilder/resources/missile-weapon.png";
	private final String shotImg = "missile-weapon.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        //ImageIcon ii = new ImageIcon(shotImg);
        //setImage(ii.getImage());

    	ImagemProxy imagemProxy = new ImagemProxy(shotImg);
        Image ii = imagemProxy.carregarImagem();  
        setImage(ii);
        
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}
