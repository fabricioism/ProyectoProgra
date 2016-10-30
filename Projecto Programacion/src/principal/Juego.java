package principal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Juego {
	
	package principal;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import medios.TileMap;

public class Juego extends Canvas{
	public static final int anchoVentana= 500;
	public static final int altoVentana= 500;
	private JFrame juego;
	public static HashMap<String, BufferedImage> campos;
	
	private Graphics2D g2D;
	private BufferStrategy dobleBuffer;
	int lastFpsTime; 
	int fps; 
	
	public Juego(){
		ventanaJuego();
		cicloJuego();
		juego.setVisible(true);
	}
	
	public void ventanaJuego(){
		juego=new JFrame("hertz");
		juego.setSize(anchoVentana, altoVentana);
		juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego.setLocationRelativeTo(null);
		juego.setLayout(null);
		juego.setResizable(false);
		juego.getContentPane().add(this);
	}
	
	public void cargarImagenes(){
		try {
			BufferedImage mosaico = ImageIO.read(getClass().getResource("/imagenes/terreno.jpg"));
			campos.put("lava", mosaico.getSubimage(0, 0, 240, 240));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void pintar(){
		g2D = (Graphics2D) dobleBuffer.getDrawGraphics();
		g2D.drawImage(campos.get("lava"), 50, 50, this);
		dobleBuffer.show();
	}
	
	public void cicloJuego(){
		pintar();
	}
	public static void main(String [] args){
		new Juego();
	}
	
}
