package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase que hereda de Personaje, describe las características y comportamientos del gato
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class Gato extends Personaje {
	
	/**
	 * constructor del gato con coodenadas
	 * @param x
	 * @param y
	 */
	public Gato(int x, int y) {
		super(x, y);
	}
	
	/**
	 * configura cómo se tiene que imprimir el gato
	 */
	public String toString () {
		
		return "Gato: "+super.toString();
	}

	/**
	 * dibuja el gato con una imágen sacada del directorio y con las dimensiones de la celda
	 */
	public void dibujar() {
		File file = new File (".\\imagenes\\cat.png");
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			laberinto.getCp().getG().drawImage(img, (x * laberinto.getCp().getAnchoCelda()) + laberinto.getCp().getAnchoCelda() / 4,
					(y * laberinto.getCp().getAnchoCelda()) + laberinto.getCp().getAnchoCelda() / 4 ,laberinto.getCp().getV().getLienzo());
		} catch (IOException e) {
			//si da error y no se puede leer archivo imprime el error
			e.printStackTrace();
		}	
	}
	
	/**
	 * mueve el gato por el mapa. Primero lo borra de su ubicación actual, mueve sus coordenadas y vuelve
	 * a dibujarlo en la localización nueva
	 */
	public void mover() {

		this.borrar();
		this.moverAleatorio();
		this.dibujar();
	}

	/**
	 * patrón de movimiento del gato. Coge un numero aleatorio y lo convierte en una direccion
	 */
	public void moverAleatorio() {

		int movimientoAleatorio = (int) (Math.random() * 10 % 4);
		while (!this.direccionPosible(movimientoAleatorio)) {
			movimientoAleatorio = (movimientoAleatorio + 1) % 4;
		} 
		this.moverEnUnaDireccion(movimientoAleatorio);
	}
	

}
