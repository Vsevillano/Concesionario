package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.Fichero;
import concesionario.estructura.Marca;
import concesionario.estructura.Modelo;

public class MostrarConcesionario extends VentanaPadre {

	private final JPanel contentPanel = new JPanel();
	private int indiceCoche = 0;

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
	
	private void mostrarCoche(int indiceCoche) {
		textMatricula.setText(Fichero.almacen.get(indiceCoche).getMatricula());
		comboMarca.setSelectedItem(Fichero.almacen.get(indiceCoche).getModelo().getMarca());
		comboModelo.setSelectedItem(Fichero.almacen.get(indiceCoche).getModelo());
		switch (Fichero.almacen.get(indiceCoche).getColor()) {
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

	private Object[] getModelo(JComboBox cBMarca) {
		Marca marca = (Marca) cBMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca) {
				modelos.add(m);
			}
		}
		return modelos.toArray();
	}
	
    private void comprobarBotones() {
        if (indiceCoche + 1 >= Fichero.almacen.size()) {
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
		
		cancelButton.setText("Cerrar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		mostrarCoche(indiceCoche);
		comprobarBotones();
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}
