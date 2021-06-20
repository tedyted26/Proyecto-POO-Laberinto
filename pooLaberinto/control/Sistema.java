package control;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Laberinto;
import model.Raton;
import model.Gato;
import view.VentanaLaberinto;

/**
 * Clase main, inicializa el juego
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class Sistema {
	
	//atributos
	public final static int ANCHO_CELDA=40;
	public final static int ANCHO_LABERINTO=10;
	private Laberinto laberinto;

	public static void main(String[] args) {
		
		// Creo el Sistema
		Sistema sistema = new Sistema ();
		
		//Creo el laberinto
		sistema.laberinto = new Laberinto();
		sistema.laberinto.crearLaberintoAMano(Sistema.ANCHO_LABERINTO);
		sistema.laberinto.mostrarLaberinto();
		
		//Creo la ventana para mostrar el laberinto y lo dibujo
		VentanaLaberinto v = new VentanaLaberinto(Sistema.ANCHO_CELDA, Sistema.ANCHO_LABERINTO);
		JOptionPane.showMessageDialog(v, "Que comience la batalla");
		v.dibujarLaberinto(sistema); //Paso Sistema para que luego la ventana encuentre el laberinto a dibujar
		
		//Creo el raton y el gato en esquinas opuestas y los añado al laberinto
		Raton r=Raton.getSingleton(0, 0);
		Gato g = new Gato(Sistema.ANCHO_LABERINTO-1,Sistema.ANCHO_LABERINTO-1);
		sistema.laberinto.addGato (g);
		sistema.laberinto.addRaton (r);
		
		//Creo el controlador de Personajes y el controlador del menú y se los paso a la ventana
		ControladorMenu controlmenu=new ControladorMenu(v, sistema);
		ControladorPersonajes controlador = new ControladorPersonajes(v, Sistema.ANCHO_CELDA);
		v.addKeyListener(controlador);
		v.setControlador(controlmenu);
		sistema.laberinto.setCp(controlador);
		
		//Dibujo a los personajes
		sistema.laberinto.dibujarPersonajes();

		//Inicio el bucle del juego, solo se para si cierras la ventana
		while (3>2) {
			//cada vez que se acaba la partida, se reinicia la posición del gato y el ratón para jugar 
			//multiples partidas una detras de otra 
			sistema.reiniciarJuego(sistema);
			try {
			File file = new File (".\\sonidos\\gs_start.wav");
			sistema.reproducirAudio(file);
			}catch (Exception e) {
				System.out.println("Archivo no encontrado");
			}
			//como las posiciones están cambiadas, vuelve a entrar en este bucle hasta que el raton haya salido
			//o se lo hayan comido
			while (!sistema.laberinto.encontradaSalida() && !sistema.laberinto.ratonComido()){
				sistema.laberinto.moverPersonajes();
				try {
					Thread.sleep(250); // Hago espero 250 milisegundos entre un movimiento y otro porque si no va muy rápido
				} catch (Exception e) {
					System.err.println("Falló el bucle del juego");
				}
			}
			if (!sistema.laberinto.ratonComido()) {
				//se reproducen aplausos y salta un mensaje de que el raton ha ganado
				File file = new File (".\\sonidos\\win.wav");
				sistema.reproducirAudio(file);
				JOptionPane.showMessageDialog(v, "El ratón salió. Reinicia la partida.");
			} else {
				g.dibujar();
				//se reproduce un audio de pacman muriendo y salta un mensaje de que el raton ha perdido
				File file = new File (".\\sonidos\\gs_pacmandies.wav");
				sistema.reproducirAudio(file);
				JOptionPane.showMessageDialog(v,"El Gato se comio al ratón. Reinicia la partida");

			}
		}
	}
	/**
	 * Método que reinicia el juego
	 * @param Sistema sistema
	 */
	public void reiniciarJuego(Sistema sistema) {
		//borra el gato y el raton de esa posición, los cambia de sitio y los vuelve a dibujar
		sistema.laberinto.getRaton().borrar();
		for (int i=0; i<sistema.laberinto.getListaGatos().size(); i++) {
			sistema.laberinto.getListaGatos().get(i).borrar();
		}
		
		sistema.laberinto.getRaton().x=0;
		sistema.laberinto.getRaton().y=0;
		
		sistema.laberinto.getListaGatos().get(0).x=Sistema.ANCHO_LABERINTO-1;
		sistema.laberinto.getListaGatos().get(0).y=Sistema.ANCHO_LABERINTO-1;
		 
		if(sistema.laberinto.getListaGatos().size()==2) {
			sistema.laberinto.getListaGatos().get(1).x=Sistema.ANCHO_LABERINTO-1;
			sistema.laberinto.getListaGatos().get(1).y=1;
			}
		
		sistema.laberinto.dibujarPersonajes();
	}
	//getters y setters
	public Laberinto getLaberinto() {
		return laberinto;
	}
	
	/**
	 * metodo que añade una nueva instancia de un gato al laberinto y lo dibuja en 
	 * una posicion diferente al primer gato
	 * @param sistema
	 */
	public void anadirGato(Sistema sistema) {
		if(sistema.laberinto.getListaGatos().size()==1) {
			Gato g2 =new Gato (Sistema.ANCHO_LABERINTO-1,1);
			sistema.laberinto.addGato(g2);
			sistema.laberinto.getListaGatos().get(1).dibujar();
			}
	}
	
	/**
	 * metodo que borra el gato del array de gatos y lo borra de la pantalla
	 * @param sistema
	 */
	public void borrarGato(Sistema sistema) {
		if(sistema.laberinto.getListaGatos().size()==2) {
		sistema.laberinto.getListaGatos().get(1).borrar();
		sistema.laberinto.getListaGatos().remove(1);
		}
	}
	/**
	 * metodo que reproduce un audio desde un fichero pasado por parametro
	 * @param file
	 */
	public void reproducirAudio(File file) {
		Clip sonido;
		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(file));
			sonido.start();
		} catch (Exception tipoError) {
			System.out.println("" + tipoError);
		}
	}

}
