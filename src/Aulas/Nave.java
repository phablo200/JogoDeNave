package Aulas;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.ImageIcon;

public class Nave {
	
	private int x, y; //Coordenadas
	private int dx, dy; //Manipular as coordenadas
	private int altura, largura;
	private Image imagem; //Colocar Imagem no fundo
	private List<Disparo> disparos;
	private boolean isVisivel;
	private static int contador;
	ImageIcon referencia= null;
	private int HP;
	private int NV;
	private int XP;
	


	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public Nave(){
		referencia = new ImageIcon("res\\nave.gif");
		imagem= referencia.getImage();
		disparos = new ArrayList<Disparo>();
		altura=imagem.getHeight(null);
		largura=imagem.getWidth(null);
		HP= 20000;
		this.x = 100;
		this.y = 100;
		this.NV = 1;
		
	}
	
	public void Nivel(){
		if(NV == 0){
			setNV(1);
		}
		
		if(NV ==1){
			setHP(20000);
		}
		
		if(NV ==2){
			setHP(50000);
		}
	}
	
	
	
	public int getNV() {
		return NV;
	}


	public void setNV(int nV) {
		NV = nV;
	}


	public void setHP(int hP) {
		HP = hP;
	}


	public int getHP() {
		return HP;
	}


	public void mexer(){
		
		x+=dx;
		y+=dy;
		
		
		if (x < -2){
			x=-1;
		}
		else if (x > 463){
			x=462;
		}
		
		if (y < 2){
			y=3;
		}
		
		else if (y > 344){
			
			y=343;
		}
		
		
	}
	public List<Disparo> getDisparos() {
		return disparos;
	}


	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
	}
	public Image getImagem() {
		return imagem;
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}


	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}


	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	
	public int getAltura() {
		return altura;
	}


	public void setAltura(int altura) {
		this.altura = altura;
	}


	public int getLargura() {
		return largura;
	}


	public void setLargura(int largura) {
		this.largura = largura;
	}


	public void atira(){
		this.disparos.add(new Disparo(x+largura, y+altura/2));
		
		
		
	}
	
	
	public void keyPressed(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = -2;		
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 2;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = -2;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 2;
		}
		
		if(codigo == KeyEvent.VK_SPACE){
			atira();
			this.contador+=1;
			ImageIcon icon =new ImageIcon("res\\inimigo_1.gif");
			
			imagem=icon.getImage();
			
		}
		
	}
	
	public void keyReleased(KeyEvent tecla){
	
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;		
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		if(codigo == KeyEvent.VK_SPACE){
			atira();
			this.contador+=1;
			
			
			imagem=referencia.getImage();
			
		}
		
		
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y, largura, altura);
	}
	}
	
	

