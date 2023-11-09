package presentacion;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import excepciones.UsuariosNoExistenException;
import logica.DTEmpresa;
import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.IOfertaLaboral;
import logica.IUsuario;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {
	private JTextField textNombre;
	private JTextField textNickname;
	private JTextField textApellido;
	private JTextField textCorreo;
	private JTextField textFechaNomEmp;
	private JTextField textNacLink;
	private IUsuario contUsuario;
	private JComboBox<String> comboBoxUsuarios;
	private JComboBox<DTOfertaLaboral> comboBoxOL;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;
	private JLabel lblDescripcion;
	private JLabel lblFechaNomEmp;
	private JLabel lblNacLink;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCorreo;

	public ConsultaUsuario(IUsuario iu, IOfertaLaboral iol, JFrame frmAdmTrabajo) {
        contUsuario = iu;
	
		setTitle("Consulta de Usuario");
		setClosable(true);
		setBounds(100, 100, 441, 486);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{125, 300};
		gridBagLayout.rowHeights = new int[]{10, 10, 10, 10, 10, 10, 0, 0, 100, 10, 10, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
        
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un Usuario");
		GridBagConstraints gbc_lblSeleccioneUnUsuario = new GridBagConstraints();
		gbc_lblSeleccioneUnUsuario.gridwidth = 2;
		gbc_lblSeleccioneUnUsuario.insets = new Insets(20, 0, 5, 0);
		gbc_lblSeleccioneUnUsuario.gridx = 0;
		gbc_lblSeleccioneUnUsuario.gridy = 0;
		getContentPane().add(lblSeleccioneUnUsuario, gbc_lblSeleccioneUnUsuario);
		
		
		comboBoxUsuarios = new JComboBox<String>();
	    comboBoxUsuarios.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	                String selectedItem = (String) comboBoxUsuarios.getSelectedItem();
	                cargarDatos(selectedItem);
	            }
	    });
	    
		GridBagConstraints gbc_comboBoxUsuarios = new GridBagConstraints();
		gbc_comboBoxUsuarios.gridwidth = 2;
		gbc_comboBoxUsuarios.insets = new Insets(0, 20, 5, 20);
		gbc_comboBoxUsuarios.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuarios.gridx = 0;
		gbc_comboBoxUsuarios.gridy = 1;
		getContentPane().add(comboBoxUsuarios, gbc_comboBoxUsuarios);
		
		lblNickname = new JLabel("Nickname:");
		//JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(5, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		getContentPane().add(lblNickname, gbc_lblNickname);
		
		textNickname = new JTextField();
		textNickname.setEditable(false);
		GridBagConstraints gbc_textNickname = new GridBagConstraints();
		gbc_textNickname.insets = new Insets(5, 0, 5, 20);
		gbc_textNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNickname.gridx = 1;
		gbc_textNickname.gridy = 2;
		getContentPane().add(textNickname, gbc_textNickname);
		textNickname.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		//JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(5, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(5, 0, 5, 20);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 3;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellido:");
		//JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(5, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 4;
		getContentPane().add(lblApellido, gbc_lblApellido);
		
		textApellido = new JTextField();
		textApellido.setEditable(false);
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(5, 0, 5, 20);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 1;
		gbc_textApellido.gridy = 4;
		getContentPane().add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);
		
		lblCorreo = new JLabel("Correo:");
		//JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.EAST;
		gbc_lblCorreo.insets = new Insets(5, 0, 5, 5);
		gbc_lblCorreo.gridx = 0;
		gbc_lblCorreo.gridy = 5;
		getContentPane().add(lblCorreo, gbc_lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setEditable(false);
		GridBagConstraints gbc_textCorreo = new GridBagConstraints();
		gbc_textCorreo.insets = new Insets(5, 0, 5, 20);
		gbc_textCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCorreo.gridx = 1;
		gbc_textCorreo.gridy = 5;
		getContentPane().add(textCorreo, gbc_textCorreo);
		textCorreo.setColumns(10);
		 
		lblFechaNomEmp = new JLabel("New label");
		lblFechaNomEmp.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFechaNomEmp = new GridBagConstraints();
		gbc_lblFechaNomEmp.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNomEmp.insets = new Insets(5, 0, 5, 5);
		gbc_lblFechaNomEmp.gridx = 0;
		gbc_lblFechaNomEmp.gridy = 6;
		getContentPane().add(lblFechaNomEmp, gbc_lblFechaNomEmp);
		
		textFechaNomEmp = new JTextField();
		textFechaNomEmp.setEditable(false);
		GridBagConstraints gbc_textFechaNomEmp = new GridBagConstraints();
		gbc_textFechaNomEmp.insets = new Insets(5, 0, 5, 20);
		gbc_textFechaNomEmp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFechaNomEmp.gridx = 1;
		gbc_textFechaNomEmp.gridy = 6;
		getContentPane().add(textFechaNomEmp, gbc_textFechaNomEmp);
		textFechaNomEmp.setColumns(10);
		 
		lblNacLink = new JLabel("New label");
		lblNacLink.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNacLink = new GridBagConstraints();
		gbc_lblNacLink.anchor = GridBagConstraints.EAST;
		gbc_lblNacLink.insets = new Insets(5, 0, 5, 5);
		gbc_lblNacLink.gridx = 0;
		gbc_lblNacLink.gridy = 7;
		getContentPane().add(lblNacLink, gbc_lblNacLink);
		
		textNacLink = new JTextField();
		textNacLink.setEditable(false);
		GridBagConstraints gbc_textNacLink = new GridBagConstraints();
		gbc_textNacLink.insets = new Insets(5, 0, 5, 20);
		gbc_textNacLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNacLink.gridx = 1;
		gbc_textNacLink.gridy = 7;
		getContentPane().add(textNacLink, gbc_textNacLink);
		textNacLink.setColumns(10);
		

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(5, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 8;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		 
		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneDescripcion = new GridBagConstraints();
		gbc_scrollPaneDescripcion.insets = new Insets(0, 0, 5, 20);
		gbc_scrollPaneDescripcion.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDescripcion.gridx = 1;
		gbc_scrollPaneDescripcion.gridy = 8;
		getContentPane().add(scrollPaneDescripcion, gbc_scrollPaneDescripcion);
		

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true); 
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setEditable(false);
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);	
		
		JLabel lblNewLabel_4 = new JLabel("Ofertas Laborales");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(5, 0, 5, 0);
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 9;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		comboBoxOL = new JComboBox<DTOfertaLaboral>();
	    comboBoxOL.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 consultaOfertaLaboral(iu, iol, frmAdmTrabajo);
	            }
	    });
		GridBagConstraints gbc_comboBoxOL = new GridBagConstraints();
		gbc_comboBoxOL.insets = new Insets(0, 20, 20, 20);
		gbc_comboBoxOL.gridwidth = 2;
		gbc_comboBoxOL.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOL.gridx = 0;
		gbc_comboBoxOL.gridy = 10;
		getContentPane().add(comboBoxOL, gbc_comboBoxOL);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 50, 10, 0);
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 11;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario();
            }
        });
        
        lblDescripcion.setVisible(false);
        textAreaDescripcion.setVisible(false);
        scrollPaneDescripcion.setVisible(false);
        lblNacLink.setVisible(false);
        textNacLink.setVisible(false);
        lblFechaNomEmp.setVisible(false);
        textFechaNomEmp.setVisible(false);
	}
	
	public void cargarDatos(String selectedItem) {
		comboBoxOL.removeAllItems();
		DTUsuario u = contUsuario.mostrarInformacionUsuario(selectedItem);
		
		textNickname.setText(u.getNickname());
		textNombre.setText(u.getNombre());
		textApellido.setText(u.getApellido());
		textCorreo.setText(u.getCorreo());
		DTOfertaLaboral[] ofLab = u.getDTOfertasLaborales();
		DTOfertaLaboral[] arrOfertas;
		if(ofLab != null) {
			//arrOfertas = new DTOfertaLaboral[ofLab.length];
//			int i = 0;
//			for(Map.Entry<String, DTOfertaLaboral> entry : u.getDTOfertasLaborales().entrySet()) {
//				arrOfertas[i] = entry.getValue();
//				i++;
//			}
			DefaultComboBoxModel<DTOfertaLaboral> modelOL = new DefaultComboBoxModel<DTOfertaLaboral>(ofLab);
			comboBoxOL.setModel(modelOL);
		}
		


		
        if(u instanceof DTPostulante) {
        	scrollPaneDescripcion.setVisible(false);
        	textAreaDescripcion.setVisible(false);
        	lblDescripcion.setVisible(false);
        	lblFechaNomEmp.setVisible(true);
        	textFechaNomEmp.setVisible(true);
        	lblNacLink.setVisible(true);
        	lblFechaNomEmp.setText("Fecha de Nacimiento:");
        	lblNacLink.setText("Nacionalidad:");
        	textNacLink.setVisible(true);
        	DTPostulante selectedPost = (DTPostulante) u;
        	textFechaNomEmp.setText(selectedPost.getFechaDeNacimiento().toString());
        	textNacLink.setText(selectedPost.getNacionalidad());
        }else if(u instanceof DTEmpresa) {
        	scrollPaneDescripcion.setVisible(true);
        	textAreaDescripcion.setVisible(true);
        	lblDescripcion.setVisible(true);
        	lblNacLink.setVisible(true);
        	textNacLink.setVisible(true);
        	lblFechaNomEmp.setVisible(false);
        	textFechaNomEmp.setVisible(false);
        	lblNacLink.setText("Link:");
        	DTEmpresa selectedEmp = (DTEmpresa) u;
        	textNacLink.setText(selectedEmp.getLink());
        	textAreaDescripcion.setText(selectedEmp.getDescripcion());
        }
	}
	
    public boolean cargarUsuarios() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        try {
        	DTUsuario[] us = contUsuario.listarUsuarios();
	        for(DTUsuario u : us) {
	        	model.addElement(u.getNickname());
	        }
	        comboBoxUsuarios.setModel(model);
        }catch(UsuariosNoExistenException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Usuario",
					JOptionPane.ERROR_MESSAGE);
        	return false;
        }
       return true;
    }
    
    public void consultaOfertaLaboral(IUsuario iu, IOfertaLaboral iol, JFrame frmAdmTrabajo) {
    	DTOfertaLaboral dtselectedOL = (DTOfertaLaboral) comboBoxOL.getSelectedItem();
    	if(dtselectedOL != null) {
	    	ConsultaOfertaLaboral creConOfLabInternalFrame = new ConsultaOfertaLaboral(iu, iol); 
	    	creConOfLabInternalFrame.setLocation(25,25);
			frmAdmTrabajo.getContentPane().add(creConOfLabInternalFrame);
	    	creConOfLabInternalFrame.referenceConsultaUsuario(dtselectedOL.getNombre(), dtselectedOL.getDTEmpresa());
	    	creConOfLabInternalFrame.setVisible(true);
    	}
    }
    
    public void limpiarFormulario() {
    	textNombre.setText("");
    	textNickname.setText("");
    	textApellido.setText("");
    	textCorreo.setText("");
    	textFechaNomEmp.setText("");
    	textNacLink.setText("");
    	textAreaDescripcion.setText("");
    	comboBoxOL.setSelectedIndex(-1);
    }

 }

