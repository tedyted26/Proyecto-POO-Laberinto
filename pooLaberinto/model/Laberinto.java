package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import control.ControladorPersonajes;
import control.Sistema;

/**
 * Clase que constuye un laberinto a partir de arrays de celdas y describe sus funciones
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class Laberinto {
	
	//atributos
	public static PrintWriter stdOut = new PrintWriter(System.out, true);
	public static PrintWriter stdErr = new PrintWriter(System.err, true);

	private int anchoLaberinto;
	private Celda maze[][];
	
	private Vector<Personaje> listaGatos = new Vector <Personaje> ();
	private Personaje raton;
	private ControladorPersonajes cp;
	
	private Scanner scLector;
	
	/**
	 * añade un gato a la lista de gatos
	 * @param Personaje p
	 */
	public void addGato (Personaje p) {
		
		listaGatos.add(p);
		p.laberinto=this;
		
	}
	/**
	 * añade un raton al laberinto. Si ya existe lo sobreescribe
	 * @param Raton r
	 */
	public void addRaton(Raton r) {
		// TODO Auto-generated method stub
		this.raton=r;
		r.laberinto=this;
		
		}	
	/**
	 * crea un laberinto desde un array ya definido 
	 * @param anchoLaberinto
	 */
	public void crearLaberintoAMano (int anchoLaberinto){
	
		this.anchoLaberinto = anchoLaberinto;
		
		Celda p = new Celda (true);
		Celda v = new Celda (false);
		
		maze = new Celda [][] { {v, p, p, v, p, p, v, v, p, v},
								{v, v, p, v, v, v, v, p, p, v},
								{p, v, v, v, p, p, v, v, v, v},
								{v, v, v, v, p, p, v, p, v, p},
								{p, v, p, v, v, p, v, p, v, p},
								{p, v, p, p, v, v, v, v, v, v},
								{v, v, v, v, v, p, v, p, p, v},
								{v, p, p, v, p, p, v, p, p, v},
								{v, p, v, v, v, v, v, v, v, v},
								{v, v, v, p, p, p, v, p, p, v}};
	}
	
	/**
	 * crea un laberinto a partir de un archivo txt
	 * @param File archivo
	 * @param int anchoLaberinto
	 */
	public void abrirLaberintoFichero (File archivo, int anchoLaberinto) {
		//inicializa maze como un array de celdas con las dimensiones pasadas por parametro
		maze = new Celda [anchoLaberinto][anchoLaberinto];
		//crea dos celas, una representando a una pared y la otra un espacio vacio
		Celda p = new Celda (true);
		Celda v = new Celda (false);
		try {
			//se elige el archivo y se recorre linea a linea con un lector
			scLector = new Scanner (new FileReader(archivo));
			//mientras tenga una linea que leer se recorre la linea leida, se pasa a un array de caracteres,
			//y se compara cada caracter con un espacio en blanco o una #
			while (scLector.hasNext()){
				for (int f=0; f<anchoLaberinto; f++) {
					char[] array=scLector.nextLine().toCharArray();
					for (int c=0; c<anchoLaberinto; c++) {
						//dependiendo del tipo de caracter, se asigna una p o una v a ese espacio de maze,
						//replicando el array creado en el método anterior
						if (array[c]==' ') {
							maze[f][c]=v;
						}
						else 
							maze[f][c]=p;
					}

				}

			}
		} catch (FileNotFoundException e) {
			//si no se encuentra el archivo salta un mensaje y su descripcion
			System.out.println("No se encontro el archivo");
			e.printStackTrace();
		}
	}
	
	/**
	 * muestra el archivo leido por consola
	 */
	public void mostrarLaberinto(){
		
		for (int f=0; f<anchoLaberinto; f++) {
			for (int c=0; c<anchoLaberinto; c++) {
				System.out.print(maze [f][c]);
			}
			System.out.println();
		}
	}
	
	/**
	 * mueve el personaje por el laberinto
	 */
	public void moverPersonajes () {
		
		raton.mover();
		for (Personaje p: this.listaGatos) {	
			p.mover();
		}
	}
	
	/**
	 * dibuja los personajes en el laberinto
	 */
	public void dibujarPersonajes () {
		
		raton.dibujar();
		for (Personaje p: this.listaGatos) {	
			p.dibujar();
		}
	}

	/**
	 * si el raton ha encontrado la salida especificada devuelve true
	 * @return boolean 
	 */
	public boolean encontradaSalida () {
		
		return (raton.x==(Sistema.ANCHO_LABERINTO-1) && raton.y==(Sistema.ANCHO_LABERINTO-1));
		
	}
	
	/**
	 * si el raton ha sido comido por el gato (se encuentran en la misma posición) devuelve true
	 * @return
	 */
	public boolean ratonComido () {
		
		boolean comido=false;
		for (Personaje g: this.listaGatos) {	
			comido = (comido || (raton.x==g.x && raton.y==g.y));
		}
		return comido; 
	}
	//getters y setters necesarios
	public Celda[][] getMaze() {
		return maze;
	}
	public void setMaze(Celda[][] maze) {
		this.maze = maze;
	}
	public Vector<Personaje> getListaGatos() {
		return listaGatos;
	}
	public void setListaGatos(Vector<Personaje> listaGatos) {
		this.listaGatos = listaGatos;
	}
	public Personaje getRaton() {
		return raton;
	}
	public void setRaton(Personaje raton) {
		this.raton = raton;
	}
	public int getAnchoLaberinto() {
		return anchoLaberinto;
	}
	public void setAnchoLaberinto(int anchoLaberinto) {
		this.anchoLaberinto = anchoLaberinto;
	}
	public ControladorPersonajes getCp() {
		return cp;
	}
	public void setCp(ControladorPersonajes cp) {
		this.cp = cp;
	}
	
	
	


}
