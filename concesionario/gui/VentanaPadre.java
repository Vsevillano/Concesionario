package concesionario.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import concesionario.estructura.Fichero;
import concesionario.estructura.Marca;
import concesionario.estructura.Modelo;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaPadre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField textMatricula;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	protected JRadioButton rdbtnPlata;
	protected JRadioButton rdbtnRojo;
	protected JRadioButton rdbtnAzul;
	protected JButton btnAdelante;
	protected JButton btnAtras;
	protected JComboBox<Marca> comboMarca;
	protected JComboBox<Modelo> comboModelo;
	protected JButton btnEnviar;
	protected JButton okButton;
	protected JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaPadre dialog = new VentanaPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene un array de modelos a partir de la marca del combobox
	 * 
	 * @param cBMarca
	 * @return
	 */
	private Object[] getModelo(JComboBox<Marca> cBMarca) {
		Marca marca = (Marca) cBMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca) {
				modelos.add(m);
			}
		}
		return modelos.toArray();
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPadre() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(10, 11, 422, 210);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(32, 22, 67, 23);
		contentPanel.add(lblMatricula);

		textMatricula = new JTextField();
		textMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!Fichero.almacen.checkMatricula(textMatricula.getText())) {
					textMatricula.setForeground(java.awt.Color.RED);
					textMatricula.setText(textMatricula.getText().toUpperCase());
				} 
			}

			@Override
			public void focusGained(FocusEvent e) {
				textMatricula.setForeground(java.awt.Color.BLACK);
			}
		});
		textMatricula.setBounds(109, 23, 157, 20);
		contentPanel.add(textMatricula);
		textMatricula.setColumns(10);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(32, 69, 46, 14);
		contentPanel.add(lblColor);

		rdbtnPlata = new JRadioButton("Plata");
		buttonGroup.add(rdbtnPlata);
		rdbtnPlata.setBounds(103, 65, 58, 23);
		contentPanel.add(rdbtnPlata);

		rdbtnRojo = new JRadioButton("Rojo");
		buttonGroup.add(rdbtnRojo);
		rdbtnRojo.setBounds(181, 65, 58, 23);
		contentPanel.add(rdbtnRojo);

		rdbtnAzul = new JRadioButton("Azul");
		buttonGroup.add(rdbtnAzul);
		rdbtnAzul.setBounds(255, 65, 58, 23);
		contentPanel.add(rdbtnAzul);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(34, 122, 46, 14);
		contentPanel.add(lblMarca);

		comboMarca = new JComboBox<Marca>();
		comboMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboModelo.setModel(new DefaultComboBoxModel(getModelo(comboMarca)));
			}
		});
		comboMarca.setBounds(90, 118, 107, 22);
		contentPanel.add(comboMarca);
		comboMarca.setModel(new DefaultComboBoxModel(Marca.values()));

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(34, 161, 46, 14);
		contentPanel.add(lblModelo);

		comboModelo = new JComboBox(new DefaultComboBoxModel(Modelo.getModelo()));
		comboModelo.setBounds(90, 157, 107, 22);
		contentPanel.add(comboModelo);

		btnEnviar = new JButton("Buscar");
		btnEnviar.setBounds(271, 111, 126, 38);
		contentPanel.add(btnEnviar);

		btnAtras = new JButton("<");
		btnAtras.setBounds(270, 154, 58, 30);
		contentPanel.add(btnAtras);

		btnAdelante = new JButton(">");
		btnAdelante.setBounds(339, 154, 58, 30);
		contentPanel.add(btnAdelante);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 220, 442, 41);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				okButton = new JButton("OK");
				okButton.setBounds(253, 11, 77, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(340, 11, 77, 23);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
