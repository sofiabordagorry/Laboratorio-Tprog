package presentacion;

import java.awt.EventQueue;
import java.time.LocalDate;

import logica.Factory;
import logica.IOfertaLaboral;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logica.IUsuario;
import logica.ManejadorTipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.Tipo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
	
	private IOfertaLaboral IOL;
	private IUsuario IU;
	
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
		

		//PRUEBAS
//		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
//		Empresa emp = new Empresa("nickEmp1", "nom", "ap", "Jokin@gmail.com", "nomEmp", "desc", "Hola.com");
//		Empresa emp1 = new Empresa("nickEmp2", "nom", "ap", "Jokin2@gmail.com", "nomEmp", "desc", "Hola.com");
//		mu.agregarEmpresa(emp);
//		mu.agregarEmpresa(emp1);
//		ManejadorTipo mt = ManejadorTipo.getInstancia();
//		LocalDate date = LocalDate.of(2023, 1, 23);
//		LocalDate date1 = LocalDate.of(2022, 1, 23);
//		Tipo t = new Tipo("Tipo1", "Desc", 3, 5, 1000, date);
//		Tipo t1 = new Tipo("Tipo2", "Desc", 3, 5, 1000, date1);
//		mt.agregarTipo(t);
//		mt.agregarTipo(t1);
//		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
//		Keyword k = new Keyword("Sheeeeeeeeesh");
//		Keyword k2 = new Keyword("Sheeesh");
//		Keyword k3 = new Keyword("Sheeeeeeeeeshasdfasdf");
//		Keyword k4 = new Keyword("Sheeeshsdddas");
//		Keyword k5 = new Keyword("Sheeeeeeasdfasdf");
//		Keyword k6 = new Keyword("Sheasdfasdfasdfh");
//		Keyword k7 = new Keyword("Sheeeasdfasdfasdfasasdfas");
//		Keyword k8 = new Keyword("Sheasdfasdfasdfh");
//		mol.agregarKeyword(k);
//		mol.agregarKeyword(k3);
//		mol.agregarKeyword(k2);
//		mol.agregarKeyword(k4);
//		mol.agregarKeyword(k5);
//		mol.agregarKeyword(k6);
//		mol.agregarKeyword(k7);
//		mol.agregarKeyword(k8);
//	
		
		creConPaqTipOLInternalFrame = new ConsultaPaqueteDeTiposDeOfertaLaboral(IOL);

		
		creConPaqTipOLInternalFrame.setLocation(25,25);
		creConPaqTipOLInternalFrame.setVisible(false);
		
		crePosAOLInternalFrame = new PostularAOfertaLaboral();
		crePosAOLInternalFrame.setLocation(25,25);
		crePosAOLInternalFrame.setVisible(false);
		
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
		
		creUsrInternalFrame = new RegistrarUsuario();
		creUsrInternalFrame.setLocation(25, 25);
		creUsrInternalFrame.setVisible(false);
		
		creConUsuInternalFrame = new ConsultaUsuario();
		creConUsuInternalFrame.setSize(441, 500);
		creConUsuInternalFrame.setLocation(25,25);
		creConUsuInternalFrame.setVisible(false);
		
		creConOfLabInternalFrame = new ConsultaOfertaLaboral();
		creConOfLabInternalFrame.setLocation(25, 25);
		creConOfLabInternalFrame.setVisible(false);
		
		creModUsuInternalFrame = new ModificarDatosUsuario();
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
		frmAdmTrabajo.setBounds(100, 100, 754, 710);
		frmAdmTrabajo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LocalDate d = LocalDate.of(1900, 1, 1);
		Paquete paqueteTest = new Paquete("nombreTest", "descripcionTest", 5, 0.5f, 500.0f, d);
		ManejadorTipo m = ManejadorTipo.getInstancia();
		m.agregarPaquete(paqueteTest);
		
		Tipo tipoTest = new Tipo("Nombretipo", "Descripciontipo", 5, 5, 5.0f, LocalDate.now());
		PaqueteTipo pqtTest = new PaqueteTipo(5, tipoTest);
		paqueteTest.agregarPaqueteTipo(pqtTest);
		
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
				creConUsuInternalFrame.setVisible(true);
			}
		});
		userMenu.add(itemConsultaUsuario);
		
		JMenuItem itemModifUsuario = new JMenuItem("Modificar datos de Usuario");
		itemModifUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA MODIFICAR DATOS DE USUARIO
				creModUsuInternalFrame.setVisible(true);
			}
		});
		userMenu.add(itemModifUsuario);
		
		JMenuItem itemPostular = new JMenuItem("Postular usuario a oferta laboral");
		itemPostular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA POSTULAR USUARIO
				crePosAOLInternalFrame.setVisible(true);
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
				creConOfLabInternalFrame.setVisible(true);
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
		
//		JMenuItem itemAltaKeyword = new JMenuItem("Agregar keyword");
//		itemAltaKeyword.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// VENTANA PARA AGREGAR KEYWORD
//			}
//		});
//		menuTrabajo.add(itemAltaKeyword);
		
		
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
				creConPaqTipOLInternalFrame.updateComboBox();
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
	}

}
