package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Principal {
	
    private RegistrarUsuario creUsrInternalFrame;
<<<<<<< HEAD
    
    private AltaTipoPublicacionOfertaLaboral altTipOLInternalFrame;
    
    private AltaDeKeywords altKWInternalFrame;
    
    private AgregarTipoPublicaciónOfertaLaboralPaquete agrTPPaqInternalFrame;
=======
    private AltaOfertaLaboral creAltOLInternalFrame;
    private CrearPaqueteTiposPublicacionOfertasLaborales crePaqTipPubOLInternalFrame;
>>>>>>> c6094ae4273be77fd9c2d6858ea30d65670c2131

	private JFrame frmAdmTrabajo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmAdmTrabajo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
		
		creUsrInternalFrame = new RegistrarUsuario();
<<<<<<< HEAD
		creUsrInternalFrame.setResizable(false);
		creUsrInternalFrame.setMaximizable(false);
		creUsrInternalFrame.setBounds(62, 50, 240, 204);
		creUsrInternalFrame.setVisible(false);
		
		altTipOLInternalFrame = new AltaTipoPublicacionOfertaLaboral();
		altTipOLInternalFrame.setResizable(false);
		altTipOLInternalFrame.setBounds(62, 50, 444, 314);
		altTipOLInternalFrame.setMaximizable(false);
		altTipOLInternalFrame.setAlignmentX(Component.LEFT_ALIGNMENT);
		altTipOLInternalFrame.setVisible(false);
		
		
		
		
		agrTPPaqInternalFrame = new AgregarTipoPublicaciónOfertaLaboralPaquete();
		agrTPPaqInternalFrame.setVisible(false);
		agrTPPaqInternalFrame.setResizable(false);
		agrTPPaqInternalFrame.setBounds(62, 50, 468, 283);
		agrTPPaqInternalFrame.setMaximizable(false);
		
		altKWInternalFrame = new AltaDeKeywords();
		altKWInternalFrame.setVisible(false);
		altKWInternalFrame.setResizable(false);
		altKWInternalFrame.setBounds(62, 50, 444, 204);
		altKWInternalFrame.setMaximizable(false);
		
		
		
		frmAdmTrabajo.getContentPane().setLayout(null);
		frmAdmTrabajo.getContentPane().add(creUsrInternalFrame);
		frmAdmTrabajo.getContentPane().add(altTipOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(altKWInternalFrame);
		frmAdmTrabajo.getContentPane().add(agrTPPaqInternalFrame);
		
=======
		creUsrInternalFrame.setBounds(20, 27, 360, 150);
		creUsrInternalFrame.setVisible(false);
		
		creAltOLInternalFrame = new AltaOfertaLaboral();
		creAltOLInternalFrame.setBounds(20, 23, 567, 562);
		creAltOLInternalFrame.setVisible(false);
		
		crePaqTipPubOLInternalFrame = new CrearPaqueteTiposPublicacionOfertasLaborales();
		crePaqTipPubOLInternalFrame.setBounds(20, 22, 659, 260);
		crePaqTipPubOLInternalFrame.setVisible(false);
		frmAdmTrabajo.getContentPane().setLayout(null);
		
		frmAdmTrabajo.getContentPane().add(creUsrInternalFrame);
		frmAdmTrabajo.getContentPane().add(creAltOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(crePaqTipPubOLInternalFrame);
>>>>>>> c6094ae4273be77fd9c2d6858ea30d65670c2131
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmTrabajo = new JFrame();
		frmAdmTrabajo.setTitle("Administrador Trabajo.uy");
<<<<<<< HEAD
		frmAdmTrabajo.setBounds(100, 100, 572, 476);
=======
		frmAdmTrabajo.setBounds(100, 100, 754, 710);
>>>>>>> c6094ae4273be77fd9c2d6858ea30d65670c2131
		frmAdmTrabajo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mainMenu = new JMenuBar();
		frmAdmTrabajo.setJMenuBar(mainMenu);
		
		// Menú de usuarios
		
		JMenu userMenu = new JMenu("Usuarios");
		mainMenu.add(userMenu);
		
		JMenuItem itemRegistro = new JMenuItem("Registro de Usuario");
		itemRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA REGISTRAR USUARIO
				// EJEMPLO:
				creUsrInternalFrame.setVisible(true);
			}
		});
		userMenu.add(itemRegistro);
		
		JMenuItem itemConsultaUsuario = new JMenuItem("Consulta de Usuario");
		itemConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA CONSULTAR USUARIO
			}
		});
		userMenu.add(itemConsultaUsuario);
		
		JMenuItem itemModifUsuario = new JMenuItem("Modificar datos de Usuario");
		itemModifUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA MODIFICAR DATOS DE USUARIO
			}
		});
		userMenu.add(itemModifUsuario);
		
		JMenuItem itemPostular = new JMenuItem("Postular usuario a oferta laboral");
		itemPostular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA POSTULAR USUARIO
			}
		});
		userMenu.add(itemPostular);
		
		// Menú de trabajos
		
		JMenu menuTrabajo = new JMenu("Trabajos");
		mainMenu.add(menuTrabajo);
		
		JMenuItem itemRegistrarOF = new JMenuItem("Alta de oferta laboral");
		itemRegistrarOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA REGISTRAR OFERTA LABORAL
				creAltOLInternalFrame.setVisible(true);
			}
		});
		menuTrabajo.add(itemRegistrarOF);
		
		JMenuItem itemConsultaOF = new JMenuItem("Consultar oferta laboral");
		itemConsultaOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA CONSULTAR OFERTA LABORAL
			}
		});
		menuTrabajo.add(itemConsultaOF);
		
		JMenuItem itemAltaTipoOF = new JMenuItem("Agregar tipo de oferta laboral");
		itemAltaTipoOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR TIPO DE OFERTA LABORAL
				
				altTipOLInternalFrame.setVisible(true);
			}
		});
		menuTrabajo.add(itemAltaTipoOF);
		
		JMenuItem itemAltaKeyword = new JMenuItem("Agregar keyword");
		itemAltaKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR KEYWORD
				altKWInternalFrame.setVisible(true);
			}
		});
		menuTrabajo.add(itemAltaKeyword);
		
		
		// Menú de paquetes
		
		JMenu menuPaquete = new JMenu("Paquetes");
		mainMenu.add(menuPaquete);
		
		JMenuItem itemCrearPaquete = new JMenuItem("Crear paquete");
		itemCrearPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA CREAR PAQUETE
				crePaqTipPubOLInternalFrame.setVisible(true);
			}
		});
		menuPaquete.add(itemCrearPaquete);
		
		JMenuItem itemConsultarPaquete = new JMenuItem("Consultar paquete");
		itemConsultarPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA CONSULTAR PAQUETE
			}
		});
		menuPaquete.add(itemConsultarPaquete);
		
		JMenuItem itemAgregarTipoPaq = new JMenuItem("Agregar tipo a paquete");
		itemAgregarTipoPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR TIPO DE PAQUETE
				agrTPPaqInternalFrame.setVisible(true);
			}
		});
		menuPaquete.add(itemAgregarTipoPaq);
	}
}
