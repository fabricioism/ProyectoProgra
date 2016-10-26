package principal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Juego {
	
	private JFrame juego;
	private HashMap<String, BufferedImage>  terreno;
	
	public Juego(){
		terreno= new HashMap<String, BufferedImage>();
		try {
			terreno.put("lava", ImageIO.read(getClass().getResource("/imagenes/terreno.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		ventanaJuego();
		juego.setVisible(true);
		
	}
	
	public void ventanaJuego(){
		juego=new JFrame("hertz");
		juego.setSize(1000, 700);
		juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego.setLocationRelativeTo(null);
		juego.setLayout(null);
		juego.setResizable(false);
	}
	
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(terreno.get("lava"), null, 45, 50);
	}
	public static void main(String[] args) {
		new Juego();

	}

}
