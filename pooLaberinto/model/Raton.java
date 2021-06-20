package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Raton extends Personaje {
	
	static private Raton singleton=null;
	
	/**
	 * constructor del raton a partir de su posicion, es privado por estar utilizando el patron singleton
	 * @param int x
	 * @param int y
	 */
	private Raton(int x, int y) {
		super(x, y);
	}
	
	/**
	 * metodo que llama al constructor solo si no se ha creado instancia de ratón aún
	 * @param int x
	 * @param int y
	 * @return Raton singleton
	 */
	static public Raton getSingleton(int x, int y) {
		if (singleton==null) {
			singleton=new Raton(x, y);
		}
		return singleton;
	}

	/**
	 * metodo heredado de personaje. Imprime el personaje por consola
	 */
	public String toString () {		
		return "Raton: "+super.toString();
	}
	
	/**
	 * dibuja el raton utilizando una imagen del directorio
	 */
	public void dibujar () {
		File file = new File (".\\imagenes\\mouse.png");
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			laberinto.getCp().getG().drawImage(img, (x * laberinto.getCp().getAnchoCelda()) + laberinto.getCp().getAnchoCelda() / 4,
					(y * laberinto.getCp().getAnchoCelda()) + laberinto.getCp().getAnchoCelda() / 4 ,laberinto.getCp().getV().getLienzo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void mover() {
		if (this.direccionPosible(laberinto.getCp().getHaciaDonde())) {
			this.borrar();
			this.moverEnUnaDireccion(laberinto.getCp().getHaciaDonde());
			this.dibujar();
		}
	}
}