package presentacion;

import java.awt.EventQueue;


import logica.Factory;
import logica.IOfertaLaboral;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import publicar.WebServices;
import logica.IUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
	

	private IUsuario IU;
	private IOfertaLaboral IOL;
	
	private AceptarORechazarOL creAcepRechOLInternalFrame;
    private AltaOfertaLaboral creAltOLInternalFrame;
    private CrearPaqueteTiposPublicacionOfertasLaborales crePaqTipPubOLInternalFrame;
	private AgregarTipoPublicacionOfertaLaboralPaquete creAltTipPubOLPaqInternalFrame;
	private AltaTipoPublicacionOfertaLaboral creAltTipPubOLInternalFrame;
	private PostularAOfertaLaboral crePosAOLInternalFrame;
    private RegistrarUsuario creUsrInternalFrame;
    private ConsultaPaqueteDeTiposDeOfertaLaboral creConPaqTipOLInternalFrame;
	
	private ConsultaUsuario creConUsuInternalFrame;
	private ConsultaOfertaLaboral creConOfLabInternalFrame;
	private ModificarDatosUsuario creModUsuInternalFrame;

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
		Factory fac = Factory.getInstance();
		IOL = fac.getIOfertaLaboral();
		IU = fac.getIUsuario();
		WebServices p = new WebServices();
        p.publicar();
		 
		creConPaqTipOLInternalFrame = new ConsultaPaqueteDeTiposDeOfertaLaboral(IOL);
		creConPaqTipOLInternalFrame.setLocation(25,25);
		creConPaqTipOLInternalFrame.setVisible(false);
		
		crePosAOLInternalFrame = new PostularAOfertaLaboral(IU);
		crePosAOLInternalFrame.setLocation(25,25);
		crePosAOLInternalFrame.setVisible(false);
		
		creAcepRechOLInternalFrame = new AceptarORechazarOL(IU, IOL);
		creAcepRechOLInternalFrame.setLocation(25, 25);
		creAcepRechOLInternalFrame.setVisible(false);
		
		creAltOLInternalFrame = new AltaOfertaLaboral(IU, IOL);
		creAltOLInternalFrame.setLocation(25, 25);
		creAltOLInternalFrame.setVisible(false);
		
		crePaqTipPubOLInternalFrame = new CrearPaqueteTiposPublicacionOfertasLaborales(IOL);
		crePaqTipPubOLInternalFrame.setLocation(25, 25);
		crePaqTipPubOLInternalFrame.setVisible(false);
		
		creAltTipPubOLInternalFrame = new AltaTipoPublicacionOfertaLaboral(IOL);
		creAltTipPubOLInternalFrame.setLocation(25,25);
		creAltTipPubOLInternalFrame.setVisible(false);
		
		creAltTipPubOLPaqInternalFrame = new AgregarTipoPublicacionOfertaLaboralPaquete(IOL);
		creAltTipPubOLPaqInternalFrame.setLocation(25,25);
		creAltTipPubOLPaqInternalFrame.setVisible(false);
		
		creUsrInternalFrame = new RegistrarUsuario(IU);
		creUsrInternalFrame.setLocation(25, 25);
		creUsrInternalFrame.setVisible(false);
		
		creConUsuInternalFrame = new ConsultaUsuario(IU,IOL,frmAdmTrabajo);

		creConUsuInternalFrame.setSize(441, 500);
		creConUsuInternalFrame.setLocation(25,25);
		creConUsuInternalFrame.setVisible(false);
		
		creConOfLabInternalFrame = new ConsultaOfertaLaboral(IU, IOL);
		creConOfLabInternalFrame.setLocation(25, 25);
		creConOfLabInternalFrame.setVisible(false);
		
		creModUsuInternalFrame = new ModificarDatosUsuario(IU);
		creModUsuInternalFrame.setLocation(25,25);
		creModUsuInternalFrame.setVisible(false);
		

		frmAdmTrabajo.getContentPane().setLayout(null);
		
		frmAdmTrabajo.getContentPane().add(creAltOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(crePaqTipPubOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(creAltTipPubOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(creAltTipPubOLPaqInternalFrame);
		frmAdmTrabajo.getContentPane().add(crePosAOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(creUsrInternalFrame);
		frmAdmTrabajo.getContentPane().add(creConPaqTipOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(creAcepRechOLInternalFrame);
		frmAdmTrabajo.getContentPane().add(creConUsuInternalFrame);
		frmAdmTrabajo.getContentPane().add(creConOfLabInternalFrame);
		frmAdmTrabajo.getContentPane().add(creModUsuInternalFrame);
		frmAdmTrabajo.getContentPane().add(creAltTipPubOLPaqInternalFrame);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmTrabajo = new JFrame();
		frmAdmTrabajo.setTitle("Administrador Trabajo.uy");
		frmAdmTrabajo.setBounds(100, 100, 900, 900);
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
				if(creConUsuInternalFrame.cargarUsuarios()) {
					creConUsuInternalFrame.setVisible(true);
				}

			}
		});
		userMenu.add(itemConsultaUsuario);
		
		JMenuItem itemModifUsuario = new JMenuItem("Modificar datos de Usuario");
		itemModifUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA MODIFICAR DATOS DE USUARIO
				if(creModUsuInternalFrame.cargarUsuarios()) {
					creModUsuInternalFrame.setVisible(true);
				}
			}
		});
		userMenu.add(itemModifUsuario);
		
		JMenuItem itemPostular = new JMenuItem("Postular usuario a oferta laboral");
		itemPostular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA POSTULAR USUARIO
				if(crePosAOLInternalFrame.cargarEmpresas()) {
					if(crePosAOLInternalFrame.cargarPostulantes()) {
						crePosAOLInternalFrame.setVisible(true);
					}	
				}
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
				if(creAltOLInternalFrame.cargarEmpresas()) 
					if (creAltOLInternalFrame.cargarTiposPubOL()) 
						if(creAltOLInternalFrame.cargarKeywords())
							creAltOLInternalFrame.setVisible(true);
			}
		});
		menuTrabajo.add(itemRegistrarOF);
		
		JMenuItem itemConsultaOF = new JMenuItem("Consultar oferta laboral");
		itemConsultaOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA CONSULTAR OFERTA LABORAL

				if(creConOfLabInternalFrame.cargarEmpresas()){
					creConOfLabInternalFrame.setVisible(true);
				}
			}
		});
		menuTrabajo.add(itemConsultaOF);
		
		JMenuItem itemAltaTipoOF = new JMenuItem("Agregar tipo de oferta laboral");
		itemAltaTipoOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR TIPO DE OFERTA LABORAL
				creAltTipPubOLInternalFrame.setVisible(true);//No se si va aca este
			}
		});
		menuTrabajo.add(itemAltaTipoOF);
		
		JMenuItem itemAcepRechOL = new JMenuItem("Aceptar o Rechazar Oferta Laboral");
		itemAcepRechOL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR KEYWORD
				if(creAcepRechOLInternalFrame.cargarEmpresas()){
					creAcepRechOLInternalFrame.setVisible(true);
				}
			}
		});
		menuTrabajo.add(itemAcepRechOL);
		
		
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
				if (creConPaqTipOLInternalFrame.updateComboBox())
					creConPaqTipOLInternalFrame.setVisible(true);
			}
		});
		menuPaquete.add(itemConsultarPaquete);
		
		JMenuItem itemAgregarTipoPaq = new JMenuItem("Agregar tipo a paquete");
		itemAgregarTipoPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR TIPO DE PAQUETE\
				if(creAltTipPubOLPaqInternalFrame.cargarPaquetes())
					if(creAltTipPubOLPaqInternalFrame.cargarTipos()) 
						creAltTipPubOLPaqInternalFrame.setVisible(true);
				
			}
		});
		menuPaquete.add(itemAgregarTipoPaq);
		
		JMenu mnNewMenu = new JMenu("Datos");
		mainMenu.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cargar datos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarDatos c = new CargarDatos();
				c.ingresarUsuarios();
				c.ingresarPaquetes();
				c.ingresarTipos();
				c.ingresarPaqueteTipos();
				c.ingresarKeywords();
				c.ingresarOfertasLaborales();
				c.ingresarKeywordsOfertas();
				c.ingresarPostulaciones();
				c.ingresarComprasPaquetes();
				c.cargarSeguidores();
				c.cargarResultadosPost();
        		JOptionPane.showMessageDialog(frmAdmTrabajo, "Datos cargados con éxito", "Administrador Trabajo.uy",
        				JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
	}
}
