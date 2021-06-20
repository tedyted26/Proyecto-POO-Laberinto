package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import view.VentanaLaberinto;

/**
 * Clase que controla el menú del juego.
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class ControladorMenu implements ActionListener {

	//atributos
	private VentanaLaberinto ventana;
	private Sistema sistema;

	/**
	 * Constructor de la clase
	 * @param VentanaLaberinto v
	 * @param Sistema s
	 */
	public ControladorMenu(VentanaLaberinto v, Sistema s) {
		this.ventana = v;
		this.sistema = s;
	}


	/**
	 * Método que especifica la acción realizada dependiendo de la señal de la ventana
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//si el comando que recibe es el del botón de cargar laberinto abre el jfilechooser con la carpeta especificada
		if (e.getActionCommand()=="cargar") {
			JFileChooser selector = new JFileChooser(".\\laberintos\\");

			int result = selector.showOpenDialog(ventana);
			if (result == JFileChooser.APPROVE_OPTION) {
				//coge el archivo seleccionado y lo mete en una variable tipo File
				File file = selector.getSelectedFile();
				//abre el fichero, construye el laberinto en un array y lo dibuja en pantalla
				sistema.getLaberinto().abrirLaberintoFichero(file, sistema.ANCHO_LABERINTO);
				sistema.getLaberinto().mostrarLaberinto();
				ventana.dibujarLaberinto(sistema);
				//reinicia el juego para que no se pueda cambiar el laberinto mientras se está en partida
				sistema.reiniciarJuego(sistema);
			}
		}
		//si el comando que recibe es el boton de cargar 1 gato, elimina el segundo (si existe) y
		//reinicia la partida
		else if (e.getActionCommand()=="1Gato") {
			sistema.borrarGato(sistema);
			sistema.reiniciarJuego(sistema);
		}
		//si el comando que recibe es el boton de cargar 2 gatos, añade uno mas al laberinto si
		//el numero anterior de gatos era solo 1 y reincia la partida
		else if (e.getActionCommand()=="2Gatos") {
			sistema.anadirGato(sistema);
			sistema.reiniciarJuego(sistema);
		}
	}
}
