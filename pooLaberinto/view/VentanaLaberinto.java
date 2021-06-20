package view;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import control.ControladorMenu;
import control.Sistema;

/**
 * Clase que construye todos los elementos gráficos del juego
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class VentanaLaberinto extends JFrame {
	
	//atributos
	private static final long serialVersionUID = 1L;
	private int anchoCelda;
	private Canvas lienzo;
	private JMenuBar menuBar;
	private JMenu menu, menuSeleccionar;
	private JMenuItem cargarLaberinto, seleccionar1Gato, seleccionar2Gatos;

	/**
	 * Métood que identifica el action listener de los componentes
	 * @param ControladorMenu control
	 */
	public void setControlador(ControladorMenu control) {
		cargarLaberinto.addActionListener(control);
		seleccionar1Gato.addActionListener(control);
		seleccionar2Gatos.addActionListener(control);

	}
	/**
	 * Constructor de la ventana
	 * @param int anchoCelda
	 * @param int anchoLaberinto
	 */
	public VentanaLaberinto (int anchoCelda, int anchoLaberinto) {
		//crea la barra del menú en la parte superior de la ventana y lo añade
		menuBar=new JMenuBar();
		this.getContentPane().add(menuBar, BorderLayout.NORTH);
		//crea el primer y unico menu de la ventana, el que contiene el desplegable
		menu = new JMenu("Cargar");
		menuSeleccionar = new JMenu ("Seleccionar número de gatos");
		
		//unico botón u opción dentro del menu
		cargarLaberinto=new JMenuItem("Cargar Laberinto");
		cargarLaberinto.setActionCommand("cargar");
		menu.add(cargarLaberinto);
		
		seleccionar1Gato=new JMenuItem("1");
		seleccionar1Gato.setActionCommand("1Gato");
		
		seleccionar2Gatos=new JMenuItem("2");
		seleccionar2Gatos.setActionCommand("2Gatos");
		
		menuSeleccionar.add(seleccionar1Gato);
		menuSeleccionar.add(seleccionar2Gatos);

		menuBar.add(menu);
		menuBar.add(menuSeleccionar);
		//le da valor a las dimensiones de la celda
		this.anchoCelda = anchoCelda;
		
		//inicializa el canvas en el que se van a dibujar los elementos y lo añade a la ventana
		lienzo = new Canvas();
		this.getContentPane().add(lienzo, BorderLayout.CENTER);
		
		//configuraciones de la ventana (tamaño, visibilidad, si se puede variar el tamaño y cómo se cierra)
		this.setSize(anchoCelda*anchoLaberinto+20,anchoCelda*anchoLaberinto+50); 
		//20 y 50 es para dejar sitio al Marco de la ventana
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Método que dibuja el laberinto celda por celda incluyendo una imagen en las celdas que son pared.
	 * @param Sistema s
	 */
	public void dibujarLaberinto(Sistema s) {
		//borra el laberinto anterior (si hay)
		borrarLaberinto(s);
		//crea dos for que recorren el laberinto elegido según su longitud
		int alto = s.getLaberinto().getMaze().length;
		int ancho = s.getLaberinto().getMaze()[0].length;
		for (int f = 0; f < alto; f++) {
			for (int c = 0; c < ancho; c++) {
				//si se encuentra con una celda que es pared, la dibuja utilizando una imagen en el directorio
				if (s.getLaberinto().getMaze()[f][c].isPared()) {
					File file = new File (".\\imagenes\\wall.png");
					BufferedImage img;
					try {
						img = ImageIO.read(file);
						lienzo.getGraphics().drawImage(img, anchoCelda * f, anchoCelda * c, lienzo);
					} catch (IOException e) {
						// si no consigue encontrar la imagen, carga la textura por defecto
						lienzo.getGraphics().setColor(Color.BLACK);
						lienzo.getGraphics().fillRect(anchoCelda * f, anchoCelda * c, anchoCelda, anchoCelda);
					}	
					
				}
			}
		}
	}
	
	/**
	 * Método que borra las celdas del laberinto una por una.
	 * @param Sistema s
	 */
	public void borrarLaberinto(Sistema s) {
		//igual que el método de crear, recorre un array con las dimensiones del laberinto pero en vez de
		//sustituir las paredes por imágenes sustituye todas las celdas por el color de fondo original
		int alto = s.getLaberinto().getMaze().length;
		int ancho = s.getLaberinto().getMaze()[0].length;
		for (int f = 0; f < alto; f++) {
			for (int c = 0; c < ancho; c++) {
				lienzo.getGraphics().clearRect(anchoCelda * f, anchoCelda * c, anchoCelda, anchoCelda);	
			}
		}
	}
	
	//Getters y setters necesarios
	public int getAnchoCelda() {
		return anchoCelda;
	}
	public void setAnchoCelda(int anchoCelda) {
		this.anchoCelda = anchoCelda;
	}
	public Canvas getLienzo() {
		return lienzo;
	}
	public JMenuItem getCargarLaberinto() {
		return cargarLaberinto;
	}
	
	
}
