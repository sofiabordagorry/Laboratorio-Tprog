package presentacion;

import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logica.Empresa;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulacion;
import logica.Postulante;
import logica.Tipo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
	
	private IUsuario IU;
	private IOfertaLaboral IOL;
	
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
		
		Factory fab = Factory.getInstance();
		IU = fab.getIUsuario();
		IOL = fab.getIOfertaLaboral();
		
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 ManejadorOfertaLaboral iMOL = ManejadorOfertaLaboral.getInstance();
		 Empresa u1 = new Empresa("nickname", "nombre", "apellido", "correo", "NombreEmpresa", "descripcion", "link");
		 LocalDate d = LocalDate.now();
		 Postulante u2 = new Postulante("nickname2", "nombre2", "apellido2", "correo2", d,"nacionalidad");

		 Tipo t1 = new Tipo("NOMBRE TIPO", "DESCRIPCION TIPO", 20, 10, 111.1f, d);
		 Map<String, Keyword> mK1 = new HashMap<>();
		 Keyword k1 = new Keyword("KEYWORD1");
		 Keyword k2 = new Keyword("KEYWORD2");
		 mK1.put(k1.getNombre(), k1);
		 mK1.put(k2.getNombre(), k2);
		 //para probar
		 OfertaLaboral of1 = new OfertaLaboral("Oferta Laboral", "Descripcion", "CIUDAD", "DEPARTAMENTO", "HORARIO", 700.5f, d, 4045.66f, t1, mK1, u1);
		 Postulacion p1 = new Postulacion(d, "Curriculum vitae reducido kjdshfghdksj;hgfdskjhg;fdshjglkjdsfhgkljhfdsklghdfslkghsdfklhglkfdsjghlkjdsfhglkdfsjhglkfdhsgklhsdflkgjhdfskljghldsfkjhgklsdfjhgkljsdfgh", "Motivacionksajlhdfkljsdgfkjdfshgklhsdfkjghfsdlkghksjdfhgklshdfghdsfklhgklsdfhglksfdhglkshdfglkhdsfkljghdsflkghsdfkljghdfkslhgfdklsjhgksdfhgkjdsfhgkljfdshglksfdjhgklsd", u2, of1);
		 p1.setOfertaLaboral(of1);
		 p1.setPostulante(u2);
		 of1.agregarPostulacion(p1);
		 u2.agregarPostulacion(p1);
		 //System.out.println(u1.getOfertasLaborales().size());
		 iMOL.agregarOfertaLaboral(of1);
		 iMU.agregarPostulante(u2);
		 iMU.agregarEmpresa(u1);
		 u1.agregarOfertaLaboral(of1);
		 //Empresa emp = iMU.buscarEmpresa("nickname");
		 //System.out.println(emp.getOfertasLaborales().size());
		
		creConPaqTipOLInternalFrame = new ConsultaPaqueteDeTiposDeOfertaLaboral();
		creConPaqTipOLInternalFrame.setLocation(25,25);
		creConPaqTipOLInternalFrame.setVisible(false);
		
		crePosAOLInternalFrame = new PostularAOfertaLaboral();
		crePosAOLInternalFrame.setLocation(25,25);
		crePosAOLInternalFrame.setVisible(false);
		
		creAltOLInternalFrame = new AltaOfertaLaboral();
		creAltOLInternalFrame.setLocation(25, 25);
		creAltOLInternalFrame.setVisible(false);
		
		crePaqTipPubOLInternalFrame = new CrearPaqueteTiposPublicacionOfertasLaborales();
		crePaqTipPubOLInternalFrame.setLocation(25, 25);
		crePaqTipPubOLInternalFrame.setVisible(false);
		
		creAltTipPubOLInternalFrame = new AltaTipoPublicacionOfertaLaboral();
		creAltTipPubOLInternalFrame.setLocation(25,25);
		creAltTipPubOLInternalFrame.setVisible(false);
		
		creAltTipPubOLPaqInternalFrame = new AgregarTipoPublicacionOfertaLaboralPaquete();
		creAltTipPubOLPaqInternalFrame.setLocation(25,25);
		creAltTipPubOLPaqInternalFrame.setVisible(false);
		
		creUsrInternalFrame = new RegistrarUsuario();
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
				creModUsuInternalFrame.setVisible(true);
				creModUsuInternalFrame.cargarUsuarios();
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
				creConPaqTipOLInternalFrame.setVisible(true);
			}
		});
		menuPaquete.add(itemConsultarPaquete);
		
		JMenuItem itemAgregarTipoPaq = new JMenuItem("Agregar tipo a paquete");
		itemAgregarTipoPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// VENTANA PARA AGREGAR TIPO DE PAQUETE
				creAltTipPubOLPaqInternalFrame.setVisible(true);
			}
		});
		menuPaquete.add(itemAgregarTipoPaq);
	}

}
