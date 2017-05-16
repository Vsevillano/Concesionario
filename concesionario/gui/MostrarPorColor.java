package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.Coche;
import concesionario.estructura.Color;
import concesionario.estructura.Fichero;

public class MostrarPorColor extends VentanaPadre {

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
			MostrarPorColor dialog = new MostrarPorColor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	private Color seleccionarColor() {
		if (rdbtnAzul.isSelected()) {
			return Color.AZUL;
		} else if (rdbtnPlata.isSelected()) {
			return Color.PLATA;
		} else {
			return Color.ROJO;
		}

	}

	/**
	 * Muestra el coche siguiente
	 */
	private void cocheSiguiente() {
		if (it.hasNext()) {
			coche = it.next();
			if (Fichero.almacen.indexOf(coche) < Fichero.almacen.getCochesColor(seleccionarColor()).size() - 1)
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
			if (Fichero.almacen.getCochesColor(seleccionarColor()).indexOf(coche) > 0)
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
	 * Create the dialog.
	 */
	public MostrarPorColor() {
		cancelButton.setText("Cerrar");
		btnAdelante.setEnabled(true);
		btnAtras.setEnabled(true);

		cancelButton.setText("Cerrar");
		setTitle("Mostrar por color");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					it = Fichero.almacen.getCochesColor(seleccionarColor()).listIterator();
					coche = it.next();
					mostrarCoche();
					btnAtras.setEnabled(false);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "No hay coches del color seleccionado!", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		okButton.setVisible(false);
		comboModelo.setEnabled(false);
		comboMarca.setEnabled(false);
		textMatricula.setEditable(false);
		textMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!Fichero.almacen.checkMatricula(textMatricula.getText())) {
					textMatricula.setForeground(java.awt.Color.BLACK);
				}
			}

		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

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
	}

}
