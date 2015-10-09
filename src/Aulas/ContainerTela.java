package Aulas;
import javax.swing.*;


public class ContainerTela extends JFrame {

	
	public ContainerTela(){
		
		add( new Fase());
		setResizable(false); // Definindo para que não possa alterar a tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400); 
		setLocationRelativeTo(null); // Colocando a tela no centro
		setVisible(true);	
	}
	
	
	public static void main(String[] args) {
		new ContainerTela();
	}
}
