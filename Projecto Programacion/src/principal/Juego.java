package principal;

import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import medios.Jugador;


public class Juego extends Canvas {
	private JFrame ventana;
	private BufferStrategy dobleBuffer;
	private Graphics2D g2D;

	private boolean jugando  = false;

	public static final int ANCHO_VENTANA = 700;
	public static final int ALTO_VENTANA = 500;

	int lastFpsTime; 
	int fps; 
		
	public static HashMap<String,BufferedImage> terreno = new HashMap<String,BufferedImage>();
	//public static HashMap<String,BufferedImage> orillas = new HashMap<String, BufferedImage>();

	public Juego(){
		cargarImagenes();

		incializarVentana();

		createBufferStrategy(2); 
		dobleBuffer = getBufferStrategy(); 
		jugando = true;

		cicloPrincipal();
	}

	public void incializarVentana(){
		
		ventana = new JFrame(); 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA); 
		ventana.setLocationRelativeTo(null); 
		ventana.setTitle("Establezca el titulo de su conveniencia"); 
		ventana.getContentPane().add(this); 
		ventana.setVisible(true); 
	}




	public void cargarImagenes(){

		try {
			BufferedImage superficie = ImageIO.read(getClass().getResource("/imagenes/terreno.jpg"));
			BufferedImage bordes = ImageIO.read(getClass().getResource("/imagenes/orillas.jpg"));
			terreno.put("cesped", superficie.getSubimage(0, 0,192, 192));
			terreno.put("lava", superficie.getSubimage(192, 0, 192, 192));
			terreno.put("orillaH", bordes.getSubimage(32, 128, 32, 32));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private void pintar(){
        g2D = (Graphics2D)dobleBuffer.getDrawGraphics(); 
     
        			g2D.drawImage(terreno.get("cesped"), 30, 30, this);
        			g2D.drawImage(terreno.get("lava"), 50, 50, this);
        			g2D.drawImage(terreno.get("orillaH"), 50, 50, this);



        dobleBuffer.show(); 	}

	

	public void cicloPrincipal(){
	   long lastLoopTime = System.nanoTime();
       final int TARGET_FPS = 60;
       final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

       while (jugando){
    	   long now = System.nanoTime();
    	   long updateLength = now - lastLoopTime;
    	   lastLoopTime = now;
    	  
    	   lastFpsTime += updateLength;
    	   fps++;
    	   if (lastFpsTime >= 1000000000){
    		   System.out.println("(FPS: "+fps+")");
    		   lastFpsTime = 0;
    		   fps = 0;
    	   }
    	   pintar();
    	   try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );} //Puede sustituir el valor de la pausa por un valor fijo
    	   catch(Exception e){};
       }
    }

    public static void main(String[] args){
		new Juego(); 	
		}
    
}
