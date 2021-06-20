package model;
/**
 * Clase que reune las caracteristicas y los métodos de la celda
 * @author Teodora Nikolaeva Nikolova 21955169
 * @date 24/5/2020
 *
 */
public class Celda {
	
	private boolean pared = false;
	private int numeroVisitas = 0;
	
	/**
	 * devuelve true o false dependiendo de si es una pared o no
	 * @return boolean pared
	 */
	public boolean isPared() {
		return pared;
	}
	/**
	 * Constructor
	 * @param boolean pared
	 */
	public Celda (boolean pared) {
		this.pared = pared;
	}
	/**
	 * Marca una celda como explorada si el personaje ha pasado por ahí 3 veces
	 * @return int numeroVisitas
	 */
	public boolean haSidoExplorada() {
		return numeroVisitas>=3;
	}
	/**
	 * Contador de las veces que ha sido visitada la celda
	 */
	public void visitada() {
		this.numeroVisitas++;
	}
	/**
	 * Configura cómo se debe imprimir la celda dependiendo de si es pared o no
	 */
	public String toString() {
		
		if (isPared()) {
			return "#";
		} else {
			return " ";
		}
	}

}
