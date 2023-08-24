package presentacion;

import javax.swing.JInternalFrame;

//import excepciones.UsuarioRepetidoException;
//import logica.IControladorUsuario;
import logica.DTTipo;
import logica.DTKeyword;
import logica.IUsuario;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JList;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
	
	private IUsuario cu;
	
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JComboBox<?> comboBoxEmpresas;
	private JComboBox<DTTipo> comboBoxTipPubOL;
	private JList<String> listKeys;
	private DefaultListModel<String> listModel;
	private JDateChooser dateChooserFechaDeAlta;
	
	public AltaOfertaLaboral() {
		
		//cu = icu;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Oferta Laboral");
        setBounds(10, 10, 574, 539);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 150, 150, 100};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 100, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel_3 = new JLabel("Elija e ingrese los siguientes datos: ");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.gridwidth = 4;
        gbc_lblNewLabel_3.insets = new Insets(8, 0, 5, 0);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 0;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblNewLabel = new JLabel("Empresas:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 4;
        gbc_lblNewLabel.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        comboBoxEmpresas = new JComboBox<Object>();
        GridBagConstraints gbc_comboBoxEmpresas = new GridBagConstraints();
        gbc_comboBoxEmpresas.gridwidth = 4;
        gbc_comboBoxEmpresas.insets = new Insets(0, 10, 8, 10);
        gbc_comboBoxEmpresas.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEmpresas.gridx = 0;
        gbc_comboBoxEmpresas.gridy = 2;
        getContentPane().add(comboBoxEmpresas, gbc_comboBoxEmpresas);
        
        JLabel lblNewLabel_1 = new JLabel("Tipos de Publicaciones de Ofertas Laborales: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 3;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        comboBoxTipPubOL = new JComboBox<DTTipo>();
        GridBagConstraints gbc_comboBoxTipPubOL = new GridBagConstraints();
        gbc_comboBoxTipPubOL.gridwidth = 4;
        gbc_comboBoxTipPubOL.insets = new Insets(0, 10, 8, 10);
        gbc_comboBoxTipPubOL.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxTipPubOL.gridx = 0;
        gbc_comboBoxTipPubOL.gridy = 4;
        getContentPane().add(comboBoxTipPubOL, gbc_comboBoxTipPubOL);
        
        JLabel lblNewLabel_2 = new JLabel("Keywords:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_2.gridwidth = 4;
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 5;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        DTKeyword tKey = new DTKeyword("Programador");
        listModel = new DefaultListModel<>();
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        listModel.addElement(tKey.getNombre());
        
        listKeys = new JList<>(listModel);
        GridBagConstraints gbc_listKeys = new GridBagConstraints();
        gbc_listKeys.gridwidth = 2;
        gbc_listKeys.insets = new Insets(0, 10, 8, 10);
        gbc_listKeys.fill = GridBagConstraints.BOTH;
        gbc_listKeys.gridx = 1;
        gbc_listKeys.gridy = 6;
        getContentPane().add(listKeys, gbc_listKeys);
        
        JScrollPane scrollPane = new JScrollPane(listKeys);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.insets = new Insets(0, 10, 8, 10);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 6;

        getContentPane().add(scrollPane, gbc_scrollPane);
        
        JLabel lblNewLabel_4 = new JLabel("Nombre: ");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 8;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridwidth = 3;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 8;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Descripción: ");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 9;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.gridwidth = 3;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 40);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 1;
        gbc_scrollPane_1.gridy = 9;
        getContentPane().add(scrollPane_1, gbc_scrollPane_1);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setWrapStyleWord(true); // Ajuste de palabras
        textAreaDescripcion.setLineWrap(true); // Ajuste de líneas
        textAreaDescripcion.setEditable(true);
        scrollPane_1.setViewportView(textAreaDescripcion);
        
        JLabel lblNewLabel_9 = new JLabel("Horario:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_9.gridx = 0;
        gbc_lblNewLabel_9.gridy = 10;
        getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
        
        textFieldHorario = new JTextField();
        GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
        gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldHorario.gridwidth = 3;
        gbc_textFieldHorario.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldHorario.gridx = 1;
        gbc_textFieldHorario.gridy = 10;
        getContentPane().add(textFieldHorario, gbc_textFieldHorario);
        textFieldHorario.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Remuneración: ");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_6.insets = new Insets(0, 8, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 11;
        getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        textFieldRemuneracion = new JTextField();
        GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
        gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldRemuneracion.gridwidth = 3;
        gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldRemuneracion.gridx = 1;
        gbc_textFieldRemuneracion.gridy = 11;
        getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);
        textFieldRemuneracion.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Ciudad: ");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 0;
        gbc_lblNewLabel_7.gridy = 12;
        getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
        
        textFieldCiudad = new JTextField();
        GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
        gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCiudad.gridwidth = 3;
        gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldCiudad.gridx = 1;
        gbc_textFieldCiudad.gridy = 12;
        getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
        textFieldCiudad.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Departamento:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_8.gridx = 0;
        gbc_lblNewLabel_8.gridy = 13;
        getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
        
        textFieldDepartamento = new JTextField();
        GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
        gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldDepartamento.gridwidth = 3;
        gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldDepartamento.gridx = 1;
        gbc_textFieldDepartamento.gridy = 13;
        getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);
        textFieldDepartamento.setColumns(10);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormulario();
        		setVisible(false);
        	}
        });
        
        JLabel lblNewLabel_10 = new JLabel("Fecha de Alta:");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
        gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_10.gridx = 0;
        gbc_lblNewLabel_10.gridy = 14;
        getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
        
        dateChooserFechaDeAlta = new JDateChooser();
        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
        gbc_dateChooser.gridwidth = 3;
        gbc_dateChooser.insets = new Insets(0, 0, 5, 40);
        gbc_dateChooser.fill = GridBagConstraints.BOTH;
        gbc_dateChooser.gridx = 1;
        gbc_dateChooser.gridy = 14;
        getContentPane().add(dateChooserFechaDeAlta, gbc_dateChooser);
        
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.anchor = GridBagConstraints.WEST;
        gbc_btnCancelar.insets = new Insets(0, 0, 10, 5);
        gbc_btnCancelar.gridx = 1;
        gbc_btnCancelar.gridy = 15;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cmdAltaOfertaLaboralActionPerformed(arg0);
        	}
            });
        
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.anchor = GridBagConstraints.WEST;
        gbc_btnAceptar.insets = new Insets(0, 0, 10, 5);
        gbc_btnAceptar.gridx = 2;
        gbc_btnAceptar.gridy = 15;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
	}
	
	protected void cmdAltaOfertaLaboralActionPerformed(ActionEvent arg0) {
		
		String nombreU = this.textFieldNombre.getText();
		
		String horarioU = this.textFieldHorario.getText();
		String remuneracionU = this.textFieldRemuneracion.getText();
		String ciudadU = this.textFieldCiudad.getText();
		String departamentoU = this.textFieldDepartamento.getText();
		//DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		DTTipo tipPubOLU = (DTTipo) this.comboBoxTipPubOL.getSelectedItem();
		List<String> keysSeleccionadas = listKeys.getSelectedValuesList(); 
		
		
		if(checkForm()) {
			/*DTOfertaLaboral datosOL = new DTOfertaLaboral(nombreU, descripcionU, ciudadU, departamentoU, horarioU, Integer.parseInt(remuneracionU));
			
			try {
				cu.ingresarDatosOL(ciudadU, departamentoU, null, null)
			} catch(OfertaRepetidaException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			}*/
			limpiarFormulario();
			setVisible(false);
		}
		
	}
	
	private boolean checkForm() {
		
		String nombreU = this.textFieldNombre.getText();
		String descripcionU = this.textAreaDescripcion.getText();
		String horarioU = this.textFieldHorario.getText();
		String remuneracionU = this.textFieldRemuneracion.getText();
		String ciudadU = this.textFieldCiudad.getText();
		String departamentoU = this.textFieldDepartamento.getText();
		//DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		DTTipo tipPubOLU = (DTTipo) this.comboBoxTipPubOL.getSelectedItem();
		List<String> keysSeleccionadas = listKeys.getSelectedValuesList(); 
		Date fechaAltaU = dateChooserFechaDeAlta.getDate();
		//long milisegundosDesdeEnero1900 = -2208988800000L; // Milisegundos desde la época para el 1 de enero de 1900
        long milisegundosDesdeEnero2000 = 946684800000L;
        Date fechaAntigua = new Date(milisegundosDesdeEnero2000);
		Date fechaDia = new Date();
		
		
		/*if(empresaU == null) {
			JOptionPane.showMessageDialog(this, "Se debe elegir una Empresa", "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (tipPubOLU == null) {
			JOptionPane.showMessageDialog(this, "Se debe elegir un Tipo de Publicacion", "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}*/
		
		if(keysSeleccionadas.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Se debe elegir al menos una Keyword", "Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (nombreU.isEmpty() || horarioU.isEmpty() || descripcionU.isEmpty() ||
			remuneracionU.isEmpty() || ciudadU.isEmpty() || departamentoU.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (fechaAltaU == null ) {
			JOptionPane.showMessageDialog(this, "Se debe elegir una Fecha de Alta", "Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!this.validar(nombreU)) {
			JOptionPane.showMessageDialog(this, "Debe ser un Nombre válido",
					"Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Float.parseFloat(remuneracionU);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La remuneración debe ser un numero", "Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Float.parseFloat(remuneracionU) <= 0) {
			JOptionPane.showMessageDialog(this, "La remuneración debe ser mayror a cero", "Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		if (!this.validar(ciudadU)) {
			JOptionPane.showMessageDialog(this, "Debe ser una Ciudad válida",
					"Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!this.validar(departamentoU)) {
			JOptionPane.showMessageDialog(this, "Debe ser un Departamento válido",
					"Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (fechaAltaU.compareTo(fechaDia) > 0) {
			JOptionPane.showMessageDialog(this, "La Fecha de Alta no puede ser posterior a la fecha actual", 
					"Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(fechaAltaU.compareTo(fechaAntigua) < 0) {
			JOptionPane.showMessageDialog(this, "La Fecha de Alta deber ser posteriror al 2000", 
					"Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private boolean validar(String s) {
        // Expresión regular que permite letras, espacios, la letra 'ñ' y caracteres acentuados
        String regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldHorario.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldRemuneracion.setText("");
		/*comboBoxEmpresas.removeAllItems();
		comboBoxTipPubOL.removeAllItems();
		listModel.removeAllElements();*/
	}
}
