package Aulas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
	
	private Image fundo;
	private Nave nave;
	private Timer timer;
	private boolean emJogo;
	private int contador;
	private List<Inimigo> inimigos;
	
	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } };
	
	
	public Fase(){
		
		setDoubleBuffered(true); // Manter o Jogo bem
		setFocusable(true); // colocar o Nosso objeto em foco
		
		
		addKeyListener(new TecladoAdapter()); // Adicionar uma classe para os eventos keyPressed(apertar o teclado) e keyRealessed (Soltar o teclado)
		
		ImageIcon referencia = new ImageIcon("res\\fundo.png");// Setando Imagem de fundo
		fundo = referencia.getImage();
		
		nave = new Nave(); //Criando a nave
		timer = new Timer(5, this); //encaixando o timer
		timer.start();
		
		this.emJogo=true;
		
		InicializaInimigos();
		
	}
	
	
	public void InicializaInimigos(){
		inimigos = new ArrayList<Inimigo>();
		
		for(int i =0; i<coordenadas.length; i++){
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
		}
	}
	
	
	// Metodo que pinta na tela
	public void paint(Graphics g){
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		
		
		if(emJogo){
		graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
		
		List<Disparo> disparos =nave.getDisparos();
		
		
		for (Disparo m: disparos){
			
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
		}
		for (Inimigo m: inimigos){
			
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
		}
		graficos.setColor(Color.WHITE);
		graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);
		
		graficos.drawString("HP:" + nave.getHP(), 5, 25);
		
		
		
		}else{
			ImageIcon fimJogo = new ImageIcon("res\\game_over.jpg");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			
		}
		
		
		g.dispose();
		
		
	

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(inimigos.size()==0){
			emJogo=false;
		}
		
		List<Disparo> disparos = nave.getDisparos();
		
		
		
		for(int i =0; i<disparos.size(); i++){
			
			Disparo m = (Disparo) disparos.get(i);
			
			
			if(m.isVisible()){
				m.mexer();
			}else{
				disparos.remove(i);
			}
			
		}
		
for(        int i =0; i<inimigos.size(); i++){
			
			Inimigo m = (Inimigo) inimigos.get(i);
			
			
			if(m.isVisible()){
				m.mexer();
			}else{
				inimigos.remove(i);
			}
			
		}
		
		
		nave.mexer();
		checarColisoes();
		repaint();
		passaDeNivel();
	}
	
	
	public void checarColisoes(){
		
		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaDisparo;
		
		
		for(int i =0; i< inimigos.size(); i++){
			Inimigo tempInimigo = inimigos.get(i);
			
			
			formaInimigo=tempInimigo.getBounds();
			
			if(formaNave.intersects(formaInimigo)){
				nave.setHP(nave.getHP()-50);
				if (nave.getHP() < 0){
				nave.setVisivel(false);
				tempInimigo.setVisible(false);
				emJogo=false;
				}
			}
			
		}
		
		
		
		List<Disparo> disparos = nave.getDisparos();
		
		for(int i =0; i<disparos.size(); i++){
			
			Disparo tempDisparo = disparos.get(i);
			formaDisparo = tempDisparo.getBounds();
			
			
			for(int j =0; j< inimigos.size(); j++){
				Inimigo tempinimigo= inimigos.get(j);
				
				
				formaInimigo=tempinimigo.getBounds();
				
				
				if(formaDisparo.intersects(formaInimigo)){
					contador+=1;
					nave.setXP(contador);
					System.out.println(nave.getXP());
					tempinimigo.setVisible(false);
					tempDisparo.setVisible(false);
					
				}
				
			}
			
		}
		
	}
	
	public void passaDeNivel(){
		if (nave.getXP() ==5){
			nave.setNV(2);
			nave.Nivel();
			System.out.println(nave.getNV());
		}
	}
	
	
	private class TecladoAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			nave.keyReleased(e);
		}
		
		
		
		
	}
	
	
	
	
	

}
