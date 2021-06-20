package model;

import control.ControladorPersonajes;
import view.VentanaLaberinto;

/**
 * Clase abstracta de la que derivan los personajes del juego. Describe características y funciones
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public abstract class Personaje {

	public int x, y;
	
	public Laberinto laberinto;
	public int ultimoMovimientoRealizado = ControladorPersonajes.Quieto;
	public VentanaLaberinto ventana;

	// Constructor
	public Personaje(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	// Métodos abstractos
	public abstract void dibujar ();
	public abstract void mover ();
	
	// Métodos implementados 
	/**
	 * manera de imprimir el personaje por pantalla
	 */
	public String toString () {
		
		return "("+x+","+y+")";
	}
	
	/**
	 * borrar el personaje
	 * cambia el cuadro en el que se encuentra por un rectangulo con el color de fondo
	 */
	public void borrar () {		
		laberinto.getCp().getG().clearRect(x*laberinto.getCp().getAnchoCelda(), y*laberinto.getCp().getAnchoCelda(), 
				laberinto.getCp().getAnchoCelda(), laberinto.getCp().getAnchoCelda());
	}
	
	/**
	 * devuelve si es posible ir en una direccion determinada
	 * @param int direccion
	 * @return boolean
	 */
	public boolean direccionPosible (int direccion) {
		
		if (direccion == ControladorPersonajes.Derecha){
			return (x < laberinto.getAnchoLaberinto() - 1 && !laberinto.getMaze()[x + 1][y].isPared());
		} else if (direccion == ControladorPersonajes.Abajo) {
			return (y < laberinto.getAnchoLaberinto() - 1 && !laberinto.getMaze()[x][y + 1].isPared());
		} else if (direccion == ControladorPersonajes.Izquierda) {
			return (x > 0 && !laberinto.getMaze()[x - 1][y].isPared());
		} else if (direccion == ControladorPersonajes.Arriba) {
			return (y > 0 && !laberinto.getMaze()[x][y - 1].isPared());
		} else {
			return false;
		}
	}
	
	/**
	 * mueve el personaje en una direccion determinada según el parametro dado
	 * @param int direccion
	 */
	public void moverEnUnaDireccion (int direccion) {
		
		if (direccion == ControladorPersonajes.Derecha){
				x = x + 1;
				System.out.println(this+" - Movido derecha");
		} else if (direccion == ControladorPersonajes.Abajo) {
				y = y + 1;
				System.out.println(this+" - Movido abajo");
		} else if (direccion == ControladorPersonajes.Izquierda) {
				x = x - 1;
				System.out.println(this+" - Movido izquierda");
		} else if (direccion == ControladorPersonajes.Arriba) {
				y = y - 1;
				System.out.println(this+" - Movido arriba");
		} 
		ultimoMovimientoRealizado = direccion;
	}
}
