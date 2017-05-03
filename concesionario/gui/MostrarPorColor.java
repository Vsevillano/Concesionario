package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.Coche;
import concesionario.estructura.Color;
import concesionario.estructura.Fichero;
import concesionario.estructura.Marca;
import concesionario.estructura.Modelo;

public class MostrarPorColor extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<Coche> copia = new ArrayList<Coche>();
	private int indiceCoche = 0;

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

	private void mostrarCoche(int indiceCoche) {
		textMatricula.setText(copia.get(indiceCoche).getMatricula());
		comboMarca.setSelectedItem(copia.get(indiceCoche).getModelo().getMarca());
		comboModelo.setSelectedItem(copia.get(indiceCoche).getModelo());
		switch (copia.get(indiceCoche).getColor()) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
		}
	}

	private Color seleccionarColor() {
		if (rdbtnRojo.isSelected()) {
			return Color.ROJO;
		} else if (rdbtnPlata.isSelected()) {
			return Color.PLATA;
		} else {
			return Color.AZUL;
		}
	}

	private void comprobarBotones() {
		if (indiceCoche + 1 >= copia.size()) {
			btnAdelante.setEnabled(false);
		} else {
			btnAdelante.setEnabled(true);
		}
		if (indiceCoche - 1 == -1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarPorColor() {
		cancelButton.setText("Cerrar");
		setTitle("Mostrar por color");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					copia = Fichero.almacen.getCochesColor(seleccionarColor());
					mostrarCoche(indiceCoche);
					comprobarBotones();
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCoche(++indiceCoche);
				comprobarBotones();
			}
		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCoche(--indiceCoche);
				comprobarBotones();
			}
		});
	}

}
