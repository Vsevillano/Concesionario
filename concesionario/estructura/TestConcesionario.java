package concesionario.estructura;

import concesionario.estructura.Color;
import concesionario.estructura.Modelo;
import concesionario.excepciones.CocheNoExisteException;
import concesionario.excepciones.MatriculaNoValidaException;
import concesionario.utiles.Menu;
import concesionario.utiles.Teclado;

/**
 * Queremos modelar un concesionario de coches en Java. Nos limitaremos a las
 * siguientes opciones:
 * <ol>
 * <li>Alta de un coche (se pedirá matricula, color y modelo),</li>
 * <li>Baja de un coche (por matrícula)</li>
 * <li>Mostrar un coche (por matrícula)</li>
 * <li>Mostrar concesionario (todos los coches del concesionario)</li>
 * <li>Contar el número de coches en el concesionario</li>
 * <li>Mostrar coches de un color</li>
 * </ol>
 * <p>
 * Lógicamente, no podrá añadirse un coche inválido o ya contenido (No pueden
 * existir dos coches con la misma matrícula en el concesionario) Por cada p que
 * se dé de alta, han de conocerse todas sus características. Ninguna de las
 * características del coche puede ser por defecto.
 * 
 * @author MaríaLourdes
 * 
 */
public class TestConcesionario {
	/**
	 * Menú principal de la aplicación
	 */
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Salir" });
	/**
	 * Menú para seleccionar los colores del coche
	 */
	private static Menu menuColores = new Menu("Colores de los coches",
			Color.AZUL.generarOpcionesMenu());

	/**
	 * Menú para seleccionar los modelos del coche
	 */
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.CORDOBA.generarOpcionesMenu());
	/**
	 * Donde se gestionará el concesionario
	 */
	static Concesionario concesionario = new Concesionario();

	/**
	 * Arranque del programa
	 * 
	 * @param args
	 *            No se utilizan
	 */
	public static void main(String[] args) {
		do {
			switch (menu.gestionar()) {
			case 1:// "Añadir Coche
				annadirCoche();
				break;
			case 2:// Eliminar Coche
				try {
					eliminarCoche();
				} catch (CocheNoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:// Obtener Coche
				getCoche();
				break;
			case 4:// Mostrar lista
				System.out.println(concesionario);
				break;
			case 5:// Contar coches
				System.out.println("Número de coches en el concesionario: "
						+ concesionario.size());
				break;
			case 6:// Mostrar coches de un color
				System.out.println(concesionario.getCochesColor(pedirColor()));
				break;

			default:// Salir
				System.out.println("Aaaaaaaaaaaaaaaaaaaaadios");
				return;
			}
		} while (true);
	}

	/**
	 * Muestra por consola el coche solicitando su la matrícula
	 */
	private static void getCoche() {
		// Coche coche;
		try {
			System.out.println(concesionario.get(Teclado
					.leerCadena("Introduce la matrícula")));
		} catch (MatriculaNoValidaException | CocheNoExisteException e) {
			System.out.println(e.getMessage());
		}
		// if (coche == null)
		// System.out.println("No existe el coche en el concesionario.");
		// else
		// System.out.println(coche);
	}

	/**
	 * Elimina un coche solicitando su la matrícula
	 * @throws CocheNoExisteException 
	 */
	private static void eliminarCoche() throws CocheNoExisteException {
		try {
			if (concesionario.eliminar(Teclado
					.leerCadena("Introduce la matrícula")))
				System.out.println("Coche eliminado");
			else
				System.out
						.println("El coche no se ha podido eliminar. No existe un coche con esa matrícula en el concesionario");
		} catch (MatriculaNoValidaException e) {
			System.out.println(e.getMessage()
					+ "No se ha podido eliminar el coche en el concesionario");
		}
	}

	/**
	 * Añade un coche solicitando sus datos por consola
	 */
	private static void annadirCoche() {

		try {
			concesionario.annadir(Teclado.leerCadena("Introduce la matrícula"),
					pedirColor(), pedirModelo());
			System.out.println("Coche añadido con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage()
					+ "No se ha podido añadir el coche en el concesionario");
		}

		// else
		// System.out.println("No se ha podido añadir");
	}

	/**
	 * Solicita al usuario un modelo de coche Según el enunciado del examen:
	 * 
	 * <pre>
	 * Para solicitar el Modelo al dar de alta al coche se implementará un método
	 * pedirModelo que mediante la gestión de un menú, devolverá el modelo indicado
	 * </pre>
	 * 
	 * @return modelo introducido por el usuario. null si el usuario no
	 *         introduce ninguno válido
	 */
	private static Modelo pedirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] arrModelos = Modelo.CORDOBA.getValues();
		if (opcion == arrModelos.length + 1)
			return null;
		return arrModelos[opcion - 1];
	}

	/**
	 * Solicita al usuario un color de coche. Según el enunciado del examen:
	 * 
	 * <pre>
	 * Para solicitar el color al dar de alta al coche se implementará un método
	 * pedirColor que mediante la gestión de un menú, devolverá el color indicado
	 * </pre>
	 * 
	 * @return color introducido por el usuario. null si el usuario no introduce
	 *         ninguno válido
	 */
	private static Color pedirColor() {
		int opcion = menuColores.gestionar();
		Color[] arrColores = Color.AZUL.getValues();
		if (opcion == arrColores.length + 1)
			return null;
		return arrColores[opcion - 1];
	}
}
