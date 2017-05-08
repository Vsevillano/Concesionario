package concesionario.estructura;

import java.io.Serializable;
import java.util.ArrayList;

import concesionario.excepciones.CocheNoExisteException;
import concesionario.excepciones.CocheYaExisteException;
import concesionario.excepciones.MatriculaNoValidaException;

/**
 * Representa un concesionario de coches.
 * 
 * Lógicamente, no podrá añadirse un coche inválido almacén del concesinario)
 * 
 * Han de conocerse todas sus características Ninguno de los valores puede ser
 * por defecto
 * 
 * @author MaríaLourdes
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Almacén de los coches del concesionario
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capitán";
	/**
	 * Bandera que me indica si el concesionario ha sido modificado
	 */
	private boolean modificado = false;

	/**
	 * 
	 * @return
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * 
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * Añade un coche al concesinario
	 * 
	 * @param matricula
	 *            Matrícula del coche a añadir
	 * @param color
	 *            Color del coche a añadir
	 * @param modelo
	 *            Modelo del coche a añadir
	 * @throws Exception
	 *             Si no se ha podido añadir el coche al concesionario, porque
	 *             ya hay otro con la misma matrícula o porque faltan datos
	 */
	public void annadir(String matricula, Color color, Modelo modelo) throws Exception {
		Coche coche = new Coche(matricula, color, modelo);
		if (!almacen.contains(coche))
			almacen.add(coche);
		else
			throw new CocheYaExisteException("El coche ya existe en el concesionario. ");
	}

	/**
	 * Elimina un coche del concesinario
	 * 
	 * @param matricula
	 *            Matrícula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Si la matrícula no es válida en su formato
	 * @return true si se ha eliminado. false en otro caso
	 */
	public boolean eliminar(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		return almacen.remove(new Coche(matricula));
	}

	/**
	 * Devuelve el número de coches en el almacén del concesionario
	 * 
	 * @return Número de coches en el almacén del concesionario
	 */
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almacén indicado por la matrícula
	 * 
	 * @param matricula
	 *            Matrícula a buscar
	 * @return Coche contenido en el almacén. null si no existe
	 * @throws MatriculaNoValidaException
	 *             Si la matrícula no es válida
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		try {
			return almacen.get(almacen.indexOf(new Coche(matricula)));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CocheNoExisteException("El coche no está en el concesionario.");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	/**
	 * Obtiene los coches por el color
	 * 
	 * @param color
	 * @return
	 */
	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

	/**
	 * 
	 * @return
	 */
	public String[][] toArray() {
		String[][] array = new String[almacen.size()][3];
		for (int i = 0; i < array.length; i++) {
			array[i][0] = almacen.get(i).getMatricula();
			array[i][1] = String.valueOf(almacen.get(i).getColor());
			array[i][2] = String.valueOf(almacen.get(i).getModelo());
		}
		return array;
	}

	/**
	 * Obtiene un coche por indice
	 * 
	 * @param indice
	 * @return
	 */
	public Coche get(int indice) {
		return almacen.get(indice);
	}

	/**
	 * Comprueba que la matricula sea valida
	 * 
	 * @param text
	 * @return
	 */
	public boolean checkMatricula(String text) {
		return Coche.esValida(text);
	}
}
