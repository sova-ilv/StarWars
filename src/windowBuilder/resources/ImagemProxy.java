package windowBuilder.resources;

import java.awt.Image;

public class ImagemProxy implements Imagem{
	private ImagemReal imagemReal;
    private final String imagem;
    
    public ImagemProxy(String imagem){
        this.imagem = imagem;
    }
    
    @Override
    public Image carregarImagem() {
        
        if(this.imagemReal == null){
            this.imagemReal = new ImagemReal(this.imagem);
        }
        
        return this.imagemReal.carregarImagem();
    }
    
}
