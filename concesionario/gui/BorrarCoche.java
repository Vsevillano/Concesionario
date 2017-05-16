package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.Coche;
import concesionario.estructura.Fichero;
import concesionario.excepciones.CocheNoExisteException;
import concesionario.excepciones.MatriculaNoValidaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrarCoche extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int indiceCoche = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrarCoche dialog = new BorrarCoche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarCoche(int indiceCoche) {
		try {
			textMatricula.setForeground(java.awt.Color.BLACK);
			Coche coche = Fichero.almacen.get(textMatricula.getText());
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
			}
			Object[] options = { "Eliminar", "Cancelar" };
			int respuesta = JOptionPane.showOptionDialog(null, "Se va a eliminar el coche, ¿Esta seguro?", "¿SEGURO?",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0 && Fichero.almacen.eliminar(textMatricula.getText())) {
				JOptionPane.showMessageDialog(contentPanel, "Coche eliminado", "Info", JOptionPane.INFORMATION_MESSAGE);
				textMatricula.setText("");
				Fichero.almacen.setModificado(true);
			}
		} catch (MatriculaNoValidaException e) {
			textMatricula.setForeground(java.awt.Color.RED);
		} catch (CocheNoExisteException e) {
			JOptionPane.showMessageDialog(null, "No existe ningun coche con esa matricula!", "Aceptar",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrarCoche() {
		cancelButton.setText("Cerrar");
		comboModelo.setEnabled(false);
		comboMarca.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		setTitle("Borrar coche");
		okButton.setVisible(false);
		btnAdelante.setVisible(false);
		btnAtras.setVisible(false);

		btnEnviar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				mostrarCoche(indiceCoche);
			}
		});
		btnEnviar.setText("Buscar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}
