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

import medios.Jugador;


public class Juego extends Canvas implements KeyListener {
	private JFrame ventana;
	private BufferStrategy dobleBuffer;
	private Graphics2D g2D;

	private boolean jugando  = false;

	public static final int ancho =500;
	public static final int alto = 500;

	int lastFpsTime; 
	int fps; 
	private Jugador jugador;
		
	public static HashMap<String,BufferedImage> imagenes = new HashMap<String,BufferedImage>();
	//public static HashMap<String,BufferedImage> orillas = new HashMap<String, BufferedImage>();
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean arriba;
	public static boolean abajo;
	public Juego(){
		cargarImagenes();
		inicializarObjetos();
		incializarVentana();

		createBufferStrategy(2); 
		dobleBuffer = getBufferStrategy(); 
		jugando = true;
		this.requestFocus(); 
		this.addKeyListener(this);
		cicloPrincipal();
	}

	public void incializarVentana(){
		
		ventana = new JFrame(); 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ventana.setSize(ancho, alto); 
		ventana.setLocationRelativeTo(null); 
		ventana.setTitle("Establezca el titulo de su conveniencia"); 
		ventana.getContentPane().add(this); 
		ventana.setVisible(true); 
	}

	public void inicializarObjetos(){
		{jugador=new Jugador("yo", 250, 250, 3, "caminandoI");}
	}



	public void cargarImagenes(){

		try {
			BufferedImage superficie = ImageIO.read(getClass().getResource("/imagenes/terreno.jpg"));
			BufferedImage bordes = ImageIO.read(getClass().getResource("/imagenes/orillas.jpg"));
			BufferedImage hertz = ImageIO.read(getClass().getResource("/imagenes/hertz.png"));
			imagenes.put("caminandoI", hertz.getSubimage(0, 0, 20, 39));
			imagenes.put("cesped", superficie.getSubimage(0, 0,192, 192));
			imagenes.put("lava", superficie.getSubimage(192, 0, 192, 192));
			imagenes.put("orillaH", bordes.getSubimage(32, 128, 32, 32));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private void pintar(){
        g2D = (Graphics2D)dobleBuffer.getDrawGraphics(); 
        for (int i=0; i<ancho; i++){
        	g2D.drawImage(imagenes.get("cesped"), 32*i, 0, this);
        	g2D.drawImage(imagenes.get("cesped"),32*i,192,this);
        	g2D.drawImage(imagenes.get("cesped"),32*i,384,this);
        }
        			/*g2D.drawImage(terreno.get("lava"), 50, 50, this);
        			g2D.drawImage(terreno.get("orillaH"), 50, 50, this);*/

        jugador.pintar(g2D, this);
        dobleBuffer.show(); 	}

	
	public void actualizar(){
		jugador.mover();
	}
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
    	   actualizar();
    	   try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );} //Puede sustituir el valor de la pausa por un valor fijo
    	   catch(Exception e){};
       }
    }

    public static void main(String[] args){
		new Juego(); 	
		}

	@Override
	public void keyPressed(KeyEvent arg0) {
		 switch(arg0.getKeyCode()){
     	case KeyEvent.VK_LEFT:
     		izquierda=true;
     		break;
     	case KeyEvent.VK_RIGHT:
     		derecha=true;
     		break;
     	case KeyEvent.VK_UP:
     		arriba=true;
     		break;
     	case KeyEvent.VK_DOWN:
     		abajo=true;
     		break;
		
	}
		 
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		 switch(arg0.getKeyCode()){
	        case KeyEvent.VK_LEFT:
	    		izquierda=false;
	    		break;
	    	case KeyEvent.VK_RIGHT:
	    		derecha=false;
	    		break;
	    	case KeyEvent.VK_UP:
	    		arriba=false;
	    		break;
	    	case KeyEvent.VK_DOWN:
	    		abajo=false;
	    		break;
     }

		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
