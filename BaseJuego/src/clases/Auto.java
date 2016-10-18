package clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import juego.Juego;

public class Auto {
	private String nombreJugador;
	private int x;
	private int y;
	private int velocidad;
	private int anchoImagen;
	private int altoImagen;
	private String llaveImagen;
	private int estadoVehiculo=0; //0: Vehiculo en velocidad normal, 1: Furioso

	private int iteradorImagen=0;
	public Auto(String nombreJugador, int x, int y, int velocidad, String llaveImagen) {
		this.nombreJugador = nombreJugador;
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.llaveImagen = llaveImagen;
	}
	public String getNombreJugador() {
		return nombreJugador;
	}
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getAnchoImagen() {
		return anchoImagen;
	}
	public void setAnchoImagen(int anchoImagen) {
		this.anchoImagen = anchoImagen;
	}
	public int getAltoImagen() {
		return altoImagen;
	}
	public void setAltoImagen(int altoImagen) {
		this.altoImagen = altoImagen;
	}
	public String getLlaveImagen() {
		return llaveImagen;
	}
	public void setLlaveImagen(String llaveImagen) {
		this.llaveImagen = llaveImagen;
	}

	public void pintar(Graphics2D g2D, BufferedImage imagen, ImageObserver observer){
		g2D.drawImage(imagen, x, y, observer);
	}

	public void mover(){
		if(estadoVehiculo==0){
			if (iteradorImagen>=5){
				iteradorImagen=0;
				this.llaveImagen="auto2";
			}else{
				this.llaveImagen="auto";
			}
		}
		if(estadoVehiculo==1){
			if (iteradorImagen>=5){
				iteradorImagen=0;
				this.llaveImagen="auto_rapido_furioso";
			}else{
				this.llaveImagen="auto_rapido_furioso2";
			}
		}
		iteradorImagen++;
		//System.out.println("Iterador Imagen:"+iteradorImagen);
		if (x>Juego.ANCHO_VENTANA)
			x=-200;
		else
			x+=velocidad;
	}
	public int getEstadoVehiculo() {
		return estadoVehiculo;
	}
	public void setEstadoVehiculo(int estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}
}

