package windowBuilder.resources;

import java.awt.Image;
import java.awt.Toolkit;

public class ImagemReal implements Imagem {

	static ResourceLoader rl = new ResourceLoader();
	private final String imagem;
    private Image image;
    
    public ImagemReal(String imagem){
        this.imagem = imagem;
    }
    
    @Override
    public Image carregarImagem() {
        if(image == null){
            image = Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(imagem));
        }
        return image;
    
    }
}
