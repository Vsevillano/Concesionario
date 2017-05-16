package concesionario.estructura;

import java.util.ArrayList;


/**
 * Representa los modelos. Según el enunciado del examen:
 * 
 * <pre>
 *  Se limitarán los modelos de coches a siete: Córdoba (marca Seat),
 *  Toledo (marca Seat),Ibiza (marca Seat), Serie 1 (marca BMW), Serie 2 (marca BMW),
 *  Serie 3 (marca BMW) y Serie 5 (marca BMW).
 * </pre>
 * 
 * @author MaríaLourdes
 * 
 */
public enum Modelo {
	/**
	 * El modelo Serie 1
	 */
	SERIE1(Marca.BMW),
	/**
	 * El modelo Serie 2
	 */
	SERIE2(Marca.BMW),
	/**
	 * El modelo Serie 3
	 */
	SERIE3(Marca.BMW),
	/**
	 * El modelo Serie 5
	 */
	SERIE5(Marca.BMW),

	/**
	 * El modelo Córdoba
	 */
	CORDOBA(Marca.SEAT),

	/**
	 * El modelo Ibiza
	 */
	IBIZA(Marca.SEAT),

	/**
	 * El modelo Toledo
	 */
	TOLEDO(Marca.SEAT);
	/**
	 * Marca del modelo
	 */
	private Marca marca;

	/**
	 * Crea un modelo con la marca indicada
	 * 
	 * @param marca
	 *            Marca del nuevo modelo
	 */
	private Modelo(Marca marca) {
		this.marca = marca;
	}

	/**
	 * Obtiene la marca del modelo
	 * 
	 * @return Marca del modelo
	 */
	public Marca getMarca() {
		return marca;
	}

	/**
	 * Devuelve una representación del modelo en forma de cadena.
	 */
	public String toString() {
		return name();

	}

	// Para el menú-------------------------------------------------
	/**
	 * Almacena los modelos posibles
	 */
	private static final Modelo[] VALUES = values();

	/**
	 * Genera las opciones del menú
	 * 
	 * @return Opciones del menú, incluyendo "Salir"
	 */
	public String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length + 1];
		int i = 0;
		for (Modelo modelo : VALUES) {
			opcionesMenu[i++] = modelo.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	/**
	 * Devuelve VALUES
	 * 
	 * @return VALUES
	 * @see Modelo#VALUES
	 */
	public Modelo[] getValues() {
		return VALUES;
	}
	// -------------------------------------------------
	
	/**
	 * Obtiene un array de modelos de BMW
	 * @return
	 */
	public static Object[] getModelo() {
		ArrayList<Modelo> arrayModelo = new ArrayList<Modelo>();
		for (Modelo modelo : Modelo.values()) {
			if (modelo.getMarca() == Marca.BMW) {
			arrayModelo.add(modelo);
			}
		}
		return  arrayModelo.toArray();
	}
}
