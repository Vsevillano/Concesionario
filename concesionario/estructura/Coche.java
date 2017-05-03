package concesionario.estructura;

import java.io.Serializable;
import java.util.regex.Pattern;

import concesionario.excepciones.ColorNoValidoException;
import concesionario.excepciones.MatriculaNoValidaException;
import concesionario.excepciones.ModeloNoValidoException;

/**
 * Representa a un coche.
 * <p>
 * Un coche tendrá las siguientes características:
 * <ol>
 * <li>Color. Se limitarán los colores a tres: plata, rojo y azul.</li>
 * 
 * 
 * <li>Modelo. Se limitarán los modelos de coches a siete: Córdoba (marca
 * Seat),Toledo (marca Seat),Ibiza (marca Seat), Serie 1 (marca BMW), Serie 2
 * (marca BMW),Serie 3 (marca BMW) y Serie 5 (marca BMW). Para solicitar el
 * modelo al dar de alta al coche podrá implementarse un método pedirModelo que
 * mediante la gestión de un menú, devolverá el modelo indicado.</li>
 * 
 * 
 * <li>Matrícula (única por coche) El formato de las matrículas será el nuevo:
 * combinación de cuatro números (de 0000 a 9999) y tres letras, comenzando por
 * BBB y terminando por ZZZ, excluyendo vocales, la LL, la Ñ y la Q.</li>
 * 
 * 
 * <ol>
 * <li>Matrículas válidas: 0000-BBB, 0000 BBB, 0000BBB, 1234ZZZ.</li>
 * <li>Matrículas inválidas: 000_BBB, 9999-BBQ, 0000-BÑB, 0000-DEF, 0000 bbb,
 * 0000 AAA</li>
 * </ol>
 * </ol>
 * 
 * @author MaríaLourdes
 * 
 */
public class Coche implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Matrícula del coche
	 */
	private String matricula;
	/**
	 * Color del coche
	 */
	private Color color;
	/**
	 * Modelo del coche
	 */
	private Modelo modelo;
	/**
	 * Representación compilada de la matrícua de un coche.
	 * 
	 * <p>
	 * Según el enunciado del examen: El formato de las matrículas será el
	 * nuevo: combinación de cuatro números (de 0000 a 9999) y tres letras,
	 * comenzando por BBB y terminando por ZZZ, excluyendo vocales, la LL, la Ñ
	 * y la Q.</li>
	 * <ol>
	 * <li>Matrículas válidas: 0000-BBB, 0000 BBB, 0000BBB, 1234ZZZ.</li>
	 * <li>Matrículas inválidas: 000_BBB, 9999-BBQ, 0000-BÑB, 0000-DEF, 0000
	 * bbb, 0000 AAA</li>
	 * </ol>
	 */
	static final private Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	// P: Cuántas excepciones se lanzan en Coche?? ¿qué métodos se interrumpen?
	// ¿Dónde se tratan?
	// P: podría simplificarse en Exception?
	/**
	 * Crea un nuevo Coche con los parámetros indicados
	 * 
	 * @param matricula
	 *            Matrícula del nuevo coche
	 * @param color
	 *            Color del nuevo coche
	 * @param modelo
	 *            Modelo del nuevo coche
	 * @throws Exception
	 *             Si el coche no puede crearse, en caso de una matrícula
	 *             inválida, color o modelo.
	 * 
	 * @see Coche#patternMatricula
	 */
	public Coche(String matricula, Color color, Modelo modelo) throws Exception {
		// throws MatriculaNoValidaException, ColorNoValidaException,
		// ModeloNoValidoException {
		super();
		setMatricula(matricula);
		setColor(color);
		setModelo(modelo);
	}

	/**
	 * Crea un nuevo Coche con los parámetros indicados
	 * 
	 * @param matricula
	 *            Matrícula del nuevo coche
	 * @throws MatriculaNoValidaException
	 *             Si el coche no puede crearse, en caso de una matrícula
	 *             inválida.
	 * @see Coche#patternMatricula
	 */
	public Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}

	// P:¿Por qué no se usa instanciarCoche??

	// static Coche instanciarCoche(String matricula, Color color, Modelo
	// modelo) {
	// if (esValida(matricula) && color != null && modelo != null)
	// return new Coche(matricula, color, modelo);
	// return null;
	// }

	// static Coche instanciarCoche(String matricula) {
	// if (esValida(matricula))
	// return new Coche(matricula);
	// return null;
	// }
	/**
	 * Indica si una matrícula es o no válida
	 * 
	 * @param matricula
	 * @return true si la matrícula es válida. false en otro caso
	 * 
	 * @see Coche#patternMatricula
	 */
	public static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}

	/**
	 * Asigna la matrícula del coche
	 * 
	 * @param matricula
	 *            Matrícula a asignar
	 * @throws MatriculaNoValidaException
	 *             Si la matrícula no es válida
	 * @see Coche#patternMatricula
	 */
	private void setMatricula(String matricula)
			throws MatriculaNoValidaException {
		if (esValida(matricula))
			this.matricula = estandarizarMatricula(matricula);
		else
			throw new MatriculaNoValidaException("La matrícula no es válida. ");
	}

	/**
	 * Le cambia el formato a la matrícula del coche. u
	 * 
	 * @param matricula
	 *            Matrícula a estandarizar. Puede tener un espacio o guion para
	 *            separar los 4 números de las tres letras
	 * @return Matrícula sin espacios ni guiones
	 */
	private String estandarizarMatricula(String matricula) {
		assert esValida(matricula);
		return matricula.replaceAll("[ -]", "");
	}

	/**
	 * Obtiene el color del coche
	 * 
	 * @return Color del coche
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Asigna el color del coche
	 * 
	 * @param color
	 *            Color a asignar
	 * @throws ColorNoValidoException
	 *             Si el color es null
	 */
	private void setColor(Color color) throws ColorNoValidoException {
		if (color != null)
			this.color = color;
		else
			throw new ColorNoValidoException("El color no es válido. ");
	}

	/**
	 * * Asigna el modelo del coche
	 * 
	 * @param modelo
	 *            Modelo a asignar
	 * @throws ModeloNoValidoException
	 *             Si el Modelo es null
	 */
	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo != null)
			this.modelo = modelo;
		else
			throw new ModeloNoValidoException("El modelo no es válido. ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/**
	 * Indica si otro coche es "igual a " este. Para ello, se fija en sus
	 * matrículas.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/**
	 * Devuelve una representación del coche en forma de cadena.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCoche [matricula=" + matricula + ", color=" + color
				+ ", modelo=" + modelo + "]";
	}

	public String getMatricula() {
		return matricula;
	}

	public Modelo getModelo() {
		return modelo;
	}

}
