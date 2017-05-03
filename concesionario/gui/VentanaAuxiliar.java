package concesionario.gui;

import javax.swing.JDialog;

public class VentanaAuxiliar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Ayuda getVentanaAuxiliarUnica() {
		Ayuda vtnUnica = new Ayuda();
		vtnUnica.setVisible(true);
		return vtnUnica;
	}
}