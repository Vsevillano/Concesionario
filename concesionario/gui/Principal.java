package concesionario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario.estructura.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Principal extends JFrame implements Serializable {

	private final Filtro filtro = new Filtro(".obj", "Objeto");
	private AcercaDe acercaDe;
	private AnnadirCoche annadir;
	private BorrarCoche borrarCoche;
	private MostrarConcesionario mostrarConcesionario;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser filechooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Sin_titulo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Fichero.almacen.isModificado()) {
					Object[] options = { "Aceptar", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null, "No ha guardado, ¿Desea Guardar?", "!!",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if (respuesta == 0) {
						// Si respondemos Aceptar
						guardarComoFile();
						Fichero.almacen = new Concesionario();
						setTitle("Sin_titulo");
						Fichero.almacen.setModificado(false);
					} else if (respuesta == 1) {
						// Si respondemos No
						Fichero.almacen = new Concesionario();
						setTitle(filechooser.getSelectedFile().getName());
						Fichero.almacen.setModificado(false);
					} else {
					}
				} else {
					Fichero.almacen = new Concesionario();
					setTitle("Sin_titulo");
					Fichero.almacen.setModificado(false);
				}
			}

		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.almacen.isModificado()) {
					Object[] options = { "Aceptar", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null, "No ha guardado, ¿Desea Guardar?", "!!",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if (respuesta == 0) {
						guardarComoFile();
					} else if (respuesta == 1) {
						try {
							abrirArchivo();
						} catch (IOException | ClassNotFoundException ex) {
							JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
					}
				} else {
					try {
						abrirArchivo();
					} catch (IOException | ClassNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTitle().equalsIgnoreCase("Sin_titulo")) {
					guardarComoFile();
					Fichero.almacen.setModificado(false);

				} else {
					try {
						Fichero.guardar(Fichero.almacen, filechooser.getSelectedFile());
						Fichero.almacen.setModificado(false);

					} catch (IOException ex) {
					}
				}
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComoFile();
				Fichero.almacen.setModificado(false);
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.almacen.isModificado()) {
					Object[] options = { "Aceptar", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null, "No ha guardado, ¿Desea Guardar?",
							"No has guardado!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
					if (respuesta == 0) {
						guardarComoFile();
					} else if (respuesta == 1) {
						System.exit(0);
					} else {
					}
				} else {
					System.exit(0);
				}
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnEdicion = new JMenu("Coche");
		mnEdicion.setMnemonic('C');
		menuBar.add(mnEdicion);

		JMenuItem mntmAadir = new JMenuItem("A\u00F1adir");
		mntmAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				annadir = new AnnadirCoche();
				annadir.setVisible(true);
			}
		});
		mnEdicion.add(mntmAadir);

		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCoche = new BorrarCoche();
				borrarCoche.setVisible(true);
			}
		});
		mnEdicion.add(mntmBorrar);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mostrarConcesionario = new MostrarConcesionario();
					mostrarConcesionario.setVisible(true);
				} catch (IndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Concesinario vacio!", "?", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnEdicion.add(mntmMostrarConcesionario);

		JMenu mnBuscar_1 = new JMenu("Buscar");
		mnBuscar_1.setMnemonic('B');
		menuBar.add(mnBuscar_1);

		JMenuItem mntmPorMatricula = new JMenuItem("Por matricula");
		mntmPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarPorMatricula mostrarPorMatricula = new MostrarPorMatricula();
				mostrarPorMatricula.setVisible(true);
			}

		});
		mnBuscar_1.add(mntmPorMatricula);

		JMenuItem mntmPorColor = new JMenuItem("Por color");
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarPorColor mostrarPorColor = new MostrarPorColor();
				mostrarPorColor.setVisible(true);
			}
		});
		mnBuscar_1.add(mntmPorColor);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmACercaDe = new JMenuItem("Acerca del Concesionario");
		mntmACercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmVerAyuda = new JMenuItem("Ver la Ayuda");
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      VentanaAuxiliar.getVentanaAuxiliarUnica();
			 }
		});
		mnAyuda.add(mntmVerAyuda);
		mnAyuda.add(mntmACercaDe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);
		if (filechooser.showDialog(filechooser, "Abrir Archivo") == filechooser.APPROVE_OPTION) {
			Fichero.abrir(filechooser.getSelectedFile());
			setTitle(filechooser.getSelectedFile().getName());
			Fichero.almacen.setModificado(false);
		}
	}

	private void guardarComoFile() {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);

		if (filechooser.APPROVE_OPTION == filechooser.showDialog(filechooser, "Guardar")) {
			filechooser.setAcceptAllFileFilterUsed(false);
			// Si el archivo existe, informamos de ello y en funcion de la
			// respuesta ...
			if (filechooser.getSelectedFile().exists()) {

				Object[] options = { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(null, "El archivo ya existe, ¿Desea Sobreescribir?",
						"Guardando", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
				// Si la respuesta es Si, guardamos el archivo con el nombre
				if (respuesta == 0) {
					try {
						Fichero.guardarComo(Fichero.almacen, filechooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
					}
					// Si la respuesta es No, informaremos de que no se ha
					// guardado
				} else {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			// Si el archivo no existe guardamos
			else {
				try {
					Fichero.guardar(Fichero.almacen, filechooser.getSelectedFile());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			setTitle(filechooser.getSelectedFile().getName());
			Fichero.almacen.setModificado(false);
		}
	}

}
