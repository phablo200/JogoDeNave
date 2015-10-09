package Aulas;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Disparo {

	private Image imagem;
	private int x, y;
	private boolean isVisible;
	private int largura, altura;
	private static final int LARGURA_TELA= 500;
	
	private static final int VELOCIDADE = 2;
	
	
	public Disparo(int x, int y){
		this.x=x;
		this.y=y;
		ImageIcon referencia = new ImageIcon("res\\missel.png");
		imagem = referencia.getImage();
		
		this.largura=imagem.getWidth(null);
		this.altura=imagem.getHeight(null);
		
		
		isVisible = true;
	}
	
	
	public void mexer(){
		
		this.x += VELOCIDADE;
		if (this.x > LARGURA_TELA){
			this.isVisible=false;
		}
	}


	public boolean isVisible() {
		return isVisible;
	}


	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public Image getImagem() {
		return imagem;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	
	
	public Rectangle getBounds(){
		return new Rectangle(x,y, this.largura, this.altura);
	}
}
