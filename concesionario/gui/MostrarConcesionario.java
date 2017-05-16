package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.Coche;
import concesionario.estructura.Fichero;

public class MostrarConcesionario extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ListIterator<Coche> it;
	private Coche coche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarConcesionario dialog = new MostrarConcesionario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el coche siguiente
	 */
	private void cocheSiguiente() {
		if (it.hasNext()) {
			coche = it.next();
			if (Fichero.almacen.indexOf(coche) < Fichero.almacen.size() - 1)
				coche = it.next();
		}
		mostrarCoche();
	}

	/**
	 * Muestra el coche anterior
	 */
	private void cocheAnterior() {

		if (it.hasPrevious()) {
			coche = it.previous();
			if (Fichero.almacen.indexOf(coche) > 0)
				coche = it.previous();
		}
		mostrarCoche();

	}

	/**
	 * Comprueba la visibilidad de los botones
	 */
	private void comprobarBotones() {
		if (!it.hasNext())
			btnAdelante.setEnabled(false);
		else
			btnAdelante.setEnabled(true);
		if (!it.hasPrevious())
			btnAtras.setEnabled(false);
		else
			btnAtras.setEnabled(true);
	}

	/**
	 * Mostrar coche
	 */
	private void mostrarCoche() {
		textMatricula.setText(coche.getMatricula());
		comboMarca.setSelectedItem(coche.getModelo().getMarca());
		comboModelo.setSelectedItem(coche.getModelo());
		switch (coche.getColor()) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
			break;
		}
		comprobarBotones();
	}

	/**
	 * Create the dialog.
	 */
	public MostrarConcesionario() {
		setTitle("Mostrar concesionario");
		comboModelo.setEnabled(false);
		comboMarca.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		textMatricula.setEditable(false);
		// Ocultamos botones
		okButton.setVisible(false);
		btnEnviar.setVisible(false);
		btnAdelante.setEnabled(false);
		btnAtras.setEnabled(false);

		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheSiguiente();
			}
		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cocheAnterior();
			}
		});

		cancelButton.setText("Cerrar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	
			it = Fichero.almacen.listIterator();
			coche = it.next();
			cocheAnterior();


	}

}
