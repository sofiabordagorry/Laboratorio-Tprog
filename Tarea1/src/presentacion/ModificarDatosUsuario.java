package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import excepciones.UsuariosNoExistenException;
import logica.DTEmpresa;
import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.IUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ModificarDatosUsuario extends JInternalFrame {
	private IUsuario contUsuario;
	private JComboBox<String> comboBoxUsuarios;
	private JLabel lblDescripcion;
	private JLabel lblFechaNomEmp;
	private JLabel lblNacLink;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;
	private JTextField textNombre;
	private JTextField textNickname;
	private JTextField textApellido;
	private JTextField textCorreo;
	private JTextField textFechaNomEmp;
	private JTextField textNacLink;
	private JDateChooser dateChooser;
	
	public ModificarDatosUsuario(IUsuario IU) {
		contUsuario = IU;
		
		setTitle("Modificar Datos de Usuario");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 441, 399);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{125, 0, 0, 300};
		gridBagLayout.rowHeights = new int[]{10, 10, 10, 10, 10, 10, 0, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un Usuario");
		GridBagConstraints gbc_lblSeleccioneUnUsuario = new GridBagConstraints();
		gbc_lblSeleccioneUnUsuario.gridwidth = 4;
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
		gbc_comboBoxUsuarios.gridwidth = 4;
		gbc_comboBoxUsuarios.insets = new Insets(0, 50, 5, 50);
		gbc_comboBoxUsuarios.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuarios.gridx = 0;
		gbc_comboBoxUsuarios.gridy = 1;
		getContentPane().add(comboBoxUsuarios, gbc_comboBoxUsuarios);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.gridwidth = 3;
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(5, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		getContentPane().add(lblNickname, gbc_lblNickname);
		
		textNickname = new JTextField();
		textNickname.setEditable(false);
		GridBagConstraints gbc_textNickname = new GridBagConstraints();
		gbc_textNickname.insets = new Insets(5, 0, 5, 50);
		gbc_textNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNickname.gridx = 3;
		gbc_textNickname.gridy = 2;
		getContentPane().add(textNickname, gbc_textNickname);
		textNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 3;
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(5, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(5, 0, 5, 50);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 3;
		gbc_textNombre.gridy = 3;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.gridwidth = 3;
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(5, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 4;
		getContentPane().add(lblApellido, gbc_lblApellido);
		
		textApellido = new JTextField();
		textApellido.setEditable(false);
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(5, 0, 5, 50);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 3;
		gbc_textApellido.gridy = 4;
		getContentPane().add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.gridwidth = 3;
		gbc_lblCorreo.anchor = GridBagConstraints.EAST;
		gbc_lblCorreo.insets = new Insets(5, 0, 5, 5);
		gbc_lblCorreo.gridx = 0;
		gbc_lblCorreo.gridy = 5;
		getContentPane().add(lblCorreo, gbc_lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setEditable(false);
		GridBagConstraints gbc_textCorreo = new GridBagConstraints();
		gbc_textCorreo.insets = new Insets(5, 0, 5, 50);
		gbc_textCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCorreo.gridx = 3;
		gbc_textCorreo.gridy = 5;
		getContentPane().add(textCorreo, gbc_textCorreo);
		textCorreo.setColumns(10);
		
		lblFechaNomEmp = new JLabel("New label");
		lblFechaNomEmp.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFechaNomEmp = new GridBagConstraints();
		gbc_lblFechaNomEmp.gridwidth = 3;
		gbc_lblFechaNomEmp.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNomEmp.insets = new Insets(5, 0, 5, 5);
		gbc_lblFechaNomEmp.gridx = 0;
		gbc_lblFechaNomEmp.gridy = 6;
		getContentPane().add(lblFechaNomEmp, gbc_lblFechaNomEmp);
		
		textFechaNomEmp = new JTextField();
		textFechaNomEmp.setEditable(false);
		GridBagConstraints gbc_textFechaNomEmp = new GridBagConstraints();
		gbc_textFechaNomEmp.insets = new Insets(5, 0, 5, 50);
		gbc_textFechaNomEmp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFechaNomEmp.gridx = 3;
		gbc_textFechaNomEmp.gridy = 6;
		getContentPane().add(textFechaNomEmp, gbc_textFechaNomEmp);
		textFechaNomEmp.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
        gbc_dateChooser.insets = new Insets(0, 0, 5, 50);
        gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
        gbc_dateChooser.gridx = 3;
        gbc_dateChooser.gridy = 6;
        getContentPane().add(dateChooser, gbc_dateChooser);
		
		
		lblNacLink = new JLabel("New label");
		lblNacLink.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNacLink = new GridBagConstraints();
		gbc_lblNacLink.gridwidth = 3;
		gbc_lblNacLink.anchor = GridBagConstraints.EAST;
		gbc_lblNacLink.insets = new Insets(5, 0, 5, 5);
		gbc_lblNacLink.gridx = 0;
		gbc_lblNacLink.gridy = 7;
		getContentPane().add(lblNacLink, gbc_lblNacLink);
		
		textNacLink = new JTextField();
		textNacLink.setEditable(false);
		GridBagConstraints gbc_textNacLink = new GridBagConstraints();
		gbc_textNacLink.insets = new Insets(5, 0, 5, 50);
		gbc_textNacLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNacLink.gridx = 3;
		gbc_textNacLink.gridy = 7;
		getContentPane().add(textNacLink, gbc_textNacLink);
		textNacLink.setColumns(10);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.gridwidth = 3;
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(5, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 8;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		
		
		scrollPaneDescripcion = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 50);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 8;
		getContentPane().add(scrollPaneDescripcion, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true); 
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setEditable(false);
        scrollPaneDescripcion.setViewportView(textAreaDescripcion);
        
        JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			limpiarFormulario();
    			setVisible(false);
    		}
    	});
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridwidth = 4;
        gbc_btnCancelar.insets = new Insets(0, 10, 20, 100);
        gbc_btnCancelar.gridx = 0;
        gbc_btnCancelar.gridy = 9;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        JButton btnAceptar = new JButton("Aceptar");
        	btnAceptar.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			modificarDatos();
        		}
        	});
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.insets = new Insets(0, 0, 20, 40);
        gbc_btnAceptar.gridx = 3;
        gbc_btnAceptar.gridy = 9;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        

	}
	
    public void cargarUsuarios() {
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
        }
    }
    
	public void cargarDatos(String selectedItem) {
		DTUsuario u = contUsuario.mostrarInformacionUsuario(selectedItem);
		
		textNickname.setText(u.getNickname());
		textNombre.setText(u.getNombre());
		textApellido.setText(u.getApellido());
		textCorreo.setText(u.getCorreo());
		textNombre.setEditable(true);
		textApellido.setEditable(true);
		
        if(u instanceof DTPostulante) {
        	scrollPaneDescripcion.setVisible(false);
        	lblDescripcion.setVisible(false);
        	lblFechaNomEmp.setText("Fecha de Nacimiento:");
        	lblNacLink.setText("Nacionalidad:");
        	DTPostulante selectedPost = (DTPostulante) u;
        	textFechaNomEmp.setVisible(false);
        	textFechaNomEmp.setText(".");
        	dateChooser.setVisible(true);
        	Date date = Date.from(selectedPost.getFechaDeNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        	dateChooser.setDate(date);
        	dateChooser.setEnabled(true);
        	//textFechaNomEmp.setText(selectedPost.getFechaDeNacimiento().toString());
        	textNacLink.setText(selectedPost.getNacionalidad());
        	textNacLink.setEditable(true);
        }else if(u instanceof DTEmpresa) {
        	scrollPaneDescripcion.setVisible(true);
        	textAreaDescripcion.setVisible(true);
        	textAreaDescripcion.setEditable(true);
        	lblDescripcion.setVisible(true);
        	dateChooser.setVisible(false);
        	textFechaNomEmp.setVisible(true);
        	textFechaNomEmp.setEditable(true);
        	lblFechaNomEmp.setText("Nombre de Empresa:");
        	lblNacLink.setText("Link:");
        	DTEmpresa selectedEmp = (DTEmpresa) u;
        	textFechaNomEmp.setText(selectedEmp.getNombreDeEmpresa());
        	textNacLink.setText(selectedEmp.getLink());
        	textNacLink.setEditable(true);
        	textAreaDescripcion.setText(selectedEmp.getDescripcion());
        	textAreaDescripcion.setEnabled(true);
        	textAreaDescripcion.setEditable(true);
        }
	}
	
	public void modificarDatos() {
		if(CheckForm()) {
			String selectedUser = (String) comboBoxUsuarios.getSelectedItem();
			DTUsuario u = contUsuario.mostrarInformacionUsuario(selectedUser);
			if(u instanceof DTEmpresa) {
				contUsuario.modificarEmpresa(textNickname.getText(),textNombre.getText(), textApellido.getText(), textFechaNomEmp.getText(),textAreaDescripcion.getText(),textNacLink.getText());
			}else if (u instanceof DTPostulante) {
				contUsuario.modificarPostulante(textNickname.getText(),textNombre.getText(), textApellido.getText(), dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),textNacLink.getText());
			}
			limpiarFormulario();
			setVisible(false);
		}
	}
	
	private boolean validar(String s) {
        // Expresión regular que permite letras, espacios, la letra 'ñ' y caracteres acentuados
        String regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
	
	public boolean CheckForm() {
		String nombre = textNombre.getText();
		String apellido = textApellido.getText();
		String descripcion = textAreaDescripcion.getText();
		String FechaNombreEmp = textFechaNomEmp.getText();
		String NacionalidadLink = textNacLink.getText();
		Date FechaNacimiento = dateChooser.getDate();
		
		if(!validar(nombre) || !validar(apellido)) {
			JOptionPane.showMessageDialog(this, "Ingrese un nombre y apellido válidos", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(comboBoxUsuarios.getSelectedItem() instanceof DTEmpresa) {
			if(nombre.isEmpty() || apellido.isEmpty() || descripcion.isEmpty() || FechaNombreEmp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}else if (comboBoxUsuarios.getSelectedItem() instanceof DTPostulante){
			if(nombre.isEmpty() || apellido.isEmpty() || NacionalidadLink.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(FechaNacimiento == null) {
				JOptionPane.showMessageDialog(this, "Se debe seleccionar una fecha de nacimiento", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
		
		JOptionPane.showMessageDialog(this, "Los datos se han modificado correctamente",
				"Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
		return true;
		
	}
	
	public void limpiarFormulario() {
		textAreaDescripcion.setText("");
		textNombre.setText("");;
		textNickname.setText("");;
		textApellido.setText("");;
		textCorreo.setText("");;
		textFechaNomEmp.setText("");;
		textNacLink.setText("");;
		dateChooser.setDate(null);
	}

}
