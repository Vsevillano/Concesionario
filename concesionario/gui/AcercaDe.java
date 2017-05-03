package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setTitle("Acerca del Concesionario");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnGuiDesarrolladoPor = new JTextPane();
		txtpnGuiDesarrolladoPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnGuiDesarrolladoPor.setEditable(false);
		txtpnGuiDesarrolladoPor.setText("\t\r\n\r\n\r\n\tGUI desarrollado por:\r\n\t\t\r\n\t\tVictoriano Sevillano Vega\r\n\r\n\r\n\tCopyright 2017. IES Gran Capit\u00E1n (C\u00F3rdoba)\r\n\r\n\r\n\r\n\r\nM\u00E1s informaci\u00F3n en http://moodle.iesgrancapitan.org");
		txtpnGuiDesarrolladoPor.setBounds(0, 0, 442, 240);
		contentPanel.add(txtpnGuiDesarrolladoPor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
