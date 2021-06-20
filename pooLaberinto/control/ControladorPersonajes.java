package control;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import view.VentanaLaberinto;
/**
 * Clase que controla los movimientos de los personajes
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class ControladorPersonajes implements KeyListener {

	//atributos
	public static final int Quieto = -1;
	public static final int Derecha = 0;
	public static final int Abajo = 1;
	public static final int Izquierda = 2;
	public static final int Arriba = 3;
	
	private int haciaDonde = Quieto;
	
	private VentanaLaberinto v;
	private Graphics g;
	private int anchoCelda;

	/**
	 * Constructor de la clase
	 * @param VentanaLaberinto v
	 * @param int anchoCelda
	 */
	public ControladorPersonajes(VentanaLaberinto v, int anchoCelda) {
		
		this.v = v;
		this.g = v.getLienzo().getGraphics();
		this.anchoCelda = anchoCelda;
	}
	

	/**
	 * Método que, dependiendo de la tecla pulsada que mande la ventana, hace una acción u otra
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) haciaDonde = Izquierda;
		if (e.getKeyCode()==KeyEvent.VK_UP) haciaDonde = Arriba;
		if (e.getKeyCode()==KeyEvent.VK_DOWN) haciaDonde = Abajo;
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) haciaDonde = Derecha;	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}


	//getters y setters necesarios
	public int getAnchoCelda() {
		return anchoCelda;
	}
	public void setAnchoCelda(int anchoCelda) {
		this.anchoCelda = anchoCelda;
	}
	public VentanaLaberinto getV() {
		return v;
	}
	public Graphics getG() {
		return g;
	}


	public int getHaciaDonde() {
		return haciaDonde;
	}
	
	

}
