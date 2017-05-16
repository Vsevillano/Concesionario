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
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame implements Serializable {

	private final Filtro filtro = new Filtro(".obj", "Objeto");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser filechooser = new JFileChooser();
	private Ayuda ayuda = null;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				salir();
			}
		});
		filechooser.setSelectedFile(new File("*.obj"));
		setTitle("Sin_titulo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});

		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComoFile();
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnEdicion = new JMenu("Coche");
		mnEdicion.setMnemonic('C');
		menuBar.add(mnEdicion);

		JMenuItem mntmAadir = new JMenuItem("A\u00F1adir");
		mntmAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnadirCoche annadir = new AnnadirCoche();
				annadir.setVisible(true);
			}
		});
		mnEdicion.add(mntmAadir);

		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarCoche borrarCoche = new BorrarCoche();
				borrarCoche.setVisible(true);
			}
		});
		mnEdicion.add(mntmBorrar);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MostrarConcesionario mostrarConcesionario = new MostrarConcesionario();
					mostrarConcesionario.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException  e) {
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
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmVerAyuda = new JMenuItem("Ver la Ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ayuda == null) {
					ayuda = new Ayuda();
					ayuda.setVisible(true);
				} else {
					ayuda.setVisible(true);
				}
			}
		});
		mnAyuda.add(mntmVerAyuda);
		mnAyuda.add(mntmACercaDe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	/**
	 * Crea un nuevo concesionario y comprueba los cambios del anterior
	 */
	private void nuevo() {
		if (Fichero.almacen.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				// Si respondemos Aceptar
				guardarComoFile();
				Fichero.almacen = new Concesionario();
				setTitle("Sin_titulo");
				Fichero.almacen.setModificado(false);
			} else if (respuesta == JOptionPane.NO_OPTION) {
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

	/**
	 * Abre un archivo a partir de un filechoose
	 * 
	 * @throws HeadlessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);
		if (filechooser.showDialog(filechooser, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
			Fichero.abrir(filechooser.getSelectedFile());
			setTitle(filechooser.getSelectedFile().getName());
			Fichero.almacen.setModificado(false);
		}
	}

	/**
	 * Abre un nuevo concesionario y comprueba si esta modificado
	 */
	private void abrir() {
		if (Fichero.almacen.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComoFile();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				try {
					abrirArchivo();
				} catch (IOException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// No hacemos nada en cado de cancelar
			}
		} else {
			try {
				abrirArchivo();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Guarda un archivo
	 */
	private void guardar() {
		if (getTitle().equalsIgnoreCase("Sin_titulo")) {
			guardarComoFile();
		} else {
			try {
				Fichero.guardar(Fichero.almacen, filechooser.getSelectedFile());
				Fichero.almacen.setModificado(false);
				setTitle(filechooser.getSelectedFile().getName());
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * Guarda un archivo y comprueba si existe
	 */
	private void guardarComoFile() {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == filechooser.showDialog(filechooser, "Guardar")) {
			filechooser.setAcceptAllFileFilterUsed(false);
			// Si el archivo existe, informamos de ello y en funcion de la
			// respuesta ...
			if (filechooser.getSelectedFile().exists()) {

				int respuesta = JOptionPane.showConfirmDialog(filechooser,
						"El archivo ya existe, ¿Desea sobreescribir?", "!!", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				// Si la respuesta es Si, guardamos el archivo con el nombre
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						Fichero.guardarComo(Fichero.almacen, filechooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
						Fichero.almacen.setModificado(false);
						setTitle(filechooser.getSelectedFile().getName());
					} catch (IOException ex) {
						ex.printStackTrace();
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
					Fichero.almacen.setModificado(false);
					setTitle(filechooser.getSelectedFile().getName());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Sale del programa comprobando cambios
	 */
	private void salir() {
		if (Fichero.almacen.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComoFile();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else {
			}
		} else {
			System.exit(0);
		}
	}

}
