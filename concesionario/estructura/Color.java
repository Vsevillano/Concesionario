package concesionario.estructura;

/**
 * Representa los colores. Según el enunciado del examen:
 * 
 * <pre>
 * Se limitarán los colores a tres: plata, rojo y azul. Para solicitar el color
 * al dar de alta al coche podrá implementarse un método pedirColor que mediante
 * la gestión de un menú, devolverá el color indicado
 * </pre>
 * 
 * @author María Lourdes Magarín Corvillo
 * 
 */
public enum Color {
	/**
	 * El color plata
	 */
	PLATA,
	/**
	 * El color rojo
	 */
	ROJO,
	/**
	 * El color azul
	 */
	AZUL;
	
	
	/**
	 * Almacena los colores posibles
	 */
	private static final Color[] VALUES = Color.values();

	/**
	 * Genera las opciones del menú
	 * 
	 * @return Opciones del menú, incluyendo "Salir"
	 */
	public String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Color color : getValues()) {
			opcionesMenu[i++] = color.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	/**
	 * Devuelve VALUES
	 * 
	 * @return VALUES
	 * @see Color#VALUES
	 */
	public Color[] getValues() {
		return VALUES;
	}

}
