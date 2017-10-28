package windowBuilder.views;

import java.awt.Image;


public abstract class Sprite implements Cloneable {

	    private boolean visible;
	    private Image image;
	    //private String image;
	    protected int x;
	    protected int y;
	    protected boolean dying;
	    protected int dx;
	    
	    public Object clone() {
	        Object clone = null;
	        
	        try {
	           clone = super.clone();
	           
	        } catch (CloneNotSupportedException e) {
	           e.printStackTrace();
	        }
	        
	        return clone;
	  }

	    public Sprite() {
	    
	        visible = true;
	    }

	    public void die() {
	    
	        visible = false;
	    }

	    public boolean isVisible() {
	    
	        return visible;
	    }

	    protected void setVisible(boolean visible) {
	    
	        this.visible = visible;
	    }

	    public void setImage(Image image2) {
	    
	        this.image = image2;
	    }

	    public Image getImage() {
	    
	        return image;
	    }

	    public void setX(int x) {
	    
	        this.x = x;
	    }

	    public void setY(int y) {
	    
	        this.y = y;
	    }

	    public int getY() {
	    
	        return y;
	    }

	    public int getX() {
	    
	        return x;
	    }

	    public void setDying(boolean dying) {
	    
	        this.dying = dying;
	    }

	    public boolean isDying() {
	    
	        return this.dying;
	    }
	
}
