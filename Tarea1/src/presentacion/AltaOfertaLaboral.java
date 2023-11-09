package presentacion;

import javax.swing.JInternalFrame;

import excepciones.EmpresasNoExistenException;
import excepciones.TipoPubNoExistenException;
import excepciones.KeywordsNoExistenException;
import excepciones.OfertaLaboralRepetidaException;

import logica.DTTipo;
import logica.DTKeyword;
import logica.IUsuario;
import logica.DTEmpresa;
import logica.DTOfertaLaboral;
import logica.IOfertaLaboral;

import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
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

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
	
	private IUsuario cu;
	private IOfertaLaboral col;
	
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion;
    private JTextField textFieldHorario; // Cambiado a JTextField para mostrar el horario
	private JSpinner spinnerHoraInicio;
	private JSpinner spinnerHoraFin;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JComboBox<DTEmpresa> comboBoxEmpresas;
	private JComboBox<DTTipo> comboBoxTipPubOL;
	private JList<String> listKeys;
	private JDateChooser dateChooserFechaDeAlta;
	
	public AltaOfertaLaboral(IUsuario IU,IOfertaLaboral IOL) {
	
		cu = IU;
		col = IOL;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Oferta Laboral");
        setBounds(10, 10, 574, 591);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 0, 150, 0, 0, 0, 150, 100};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 100, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel_3 = new JLabel("Elija e ingrese los siguientes datos: ");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.gridwidth = 8;
        gbc_lblNewLabel_3.insets = new Insets(8, 0, 5, 0);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 0;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblNewLabel = new JLabel("Empresas:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 8;
        gbc_lblNewLabel.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        comboBoxEmpresas = new JComboBox<DTEmpresa>();
        GridBagConstraints gbc_comboBoxEmpresas = new GridBagConstraints();
        gbc_comboBoxEmpresas.gridwidth = 8;
        gbc_comboBoxEmpresas.insets = new Insets(0, 10, 8, 10);
        gbc_comboBoxEmpresas.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEmpresas.gridx = 0;
        gbc_comboBoxEmpresas.gridy = 2;
        getContentPane().add(comboBoxEmpresas, gbc_comboBoxEmpresas);
        
        JLabel lblNewLabel_1 = new JLabel("Tipos de Publicaciones de Ofertas Laborales: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridwidth = 8;
        gbc_lblNewLabel_1.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 3;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        comboBoxTipPubOL = new JComboBox<DTTipo>();
        GridBagConstraints gbc_comboBoxTipPubOL = new GridBagConstraints();
        gbc_comboBoxTipPubOL.gridwidth = 8;
        gbc_comboBoxTipPubOL.insets = new Insets(0, 10, 8, 10);
        gbc_comboBoxTipPubOL.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxTipPubOL.gridx = 0;
        gbc_comboBoxTipPubOL.gridy = 4;
        getContentPane().add(comboBoxTipPubOL, gbc_comboBoxTipPubOL);
        
        JLabel lblNewLabel_2 = new JLabel("Keywords:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_2.gridwidth = 8;
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 5;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        listKeys = new JList<>();
        GridBagConstraints gbc_listKeys = new GridBagConstraints();
        gbc_listKeys.gridwidth = 2;
        gbc_listKeys.insets = new Insets(0, 10, 8, 10);
        gbc_listKeys.fill = GridBagConstraints.BOTH;
        gbc_listKeys.gridx = 1;
        gbc_listKeys.gridy = 6;
        getContentPane().add(listKeys, gbc_listKeys);
        
        JScrollPane scrollPane = new JScrollPane(listKeys);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 5;
        gbc_scrollPane.insets = new Insets(0, 10, 8, 10);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 2;
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
        gbc_textFieldNombre.gridwidth = 6;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldNombre.gridx = 2;
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
        gbc_scrollPane_1.gridwidth = 6;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 40);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 2;
        gbc_scrollPane_1.gridy = 9;
        getContentPane().add(scrollPane_1, gbc_scrollPane_1);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setWrapStyleWord(true); // Ajuste de palabras
        textAreaDescripcion.setLineWrap(true); // Ajuste de líneas
        textAreaDescripcion.setEditable(true);
        scrollPane_1.setViewportView(textAreaDescripcion);
        

     // JLabel para describir el campo de horario
        JLabel lblNewLabel_9 = new JLabel("Horario:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_9.gridx = 0;
        gbc_lblNewLabel_9.gridy = 10;
        getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);

        // JSpinner para seleccionar la hora de inicio
        spinnerHoraInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
        spinnerHoraInicio.setEditor(dateEditorInicio);
        GridBagConstraints gbc_spinnerHoraInicio = new GridBagConstraints();
        gbc_spinnerHoraInicio.fill = GridBagConstraints.HORIZONTAL;
        gbc_spinnerHoraInicio.insets = new Insets(0, 0, 5, 5);
        gbc_spinnerHoraInicio.gridx = 3;
        gbc_spinnerHoraInicio.gridy = 10;
        getContentPane().add(spinnerHoraInicio, gbc_spinnerHoraInicio);

        // JLabel para el separador "-"
        JLabel lblSeparador = new JLabel(" - ");
        GridBagConstraints gbc_lblSeparador = new GridBagConstraints();
        gbc_lblSeparador.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeparador.gridx = 4;
        gbc_lblSeparador.gridy = 10;
        getContentPane().add(lblSeparador, gbc_lblSeparador);

        // JSpinner para seleccionar la hora de fin
        spinnerHoraFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditorFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
        spinnerHoraFin.setEditor(dateEditorFin);
        GridBagConstraints gbc_spinnerHoraFin = new GridBagConstraints();
        gbc_spinnerHoraFin.fill = GridBagConstraints.HORIZONTAL;
        gbc_spinnerHoraFin.insets = new Insets(0, 0, 5, 5);
        gbc_spinnerHoraFin.gridx = 5;
        gbc_spinnerHoraFin.gridy = 10;
        getContentPane().add(spinnerHoraFin, gbc_spinnerHoraFin);

        // JTextField para mostrar el horario seleccionado
        textFieldHorario = new JTextField();
        textFieldHorario.setEditable(false); // Establece el JTextField como no editable
        GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
        gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldHorario.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldHorario.gridx = 6; // Cambia el valor del gridx para colocarlo al lado de los JSpinner
        gbc_textFieldHorario.gridy = 10;
        getContentPane().add(textFieldHorario, gbc_textFieldHorario);
        textFieldHorario.setColumns(10);

     // Agrega un ChangeListener a los JSpinner para actualizar el JTextField y validar las horas
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Date horaInicio = (Date) spinnerHoraInicio.getValue();
                Date horaFin = (Date) spinnerHoraFin.getValue();

                if (horaFin.before(horaInicio)) {
                    // La hora final es menor que la hora inicial, muestra un mensaje de error
                    JOptionPane.showMessageDialog(null, "La hora final debe ser mayor que la hora inicial", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    // Restaura la hora final a la hora inicial y actualiza el JTextField
                    spinnerHoraFin.setValue(horaInicio);
                    horaFin = horaInicio;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                String horarioString = dateFormat.format(horaInicio) + " - " + dateFormat.format(horaFin);
                textFieldHorario.setText(horarioString);
            }
        };

        spinnerHoraInicio.addChangeListener(changeListener);
        spinnerHoraFin.addChangeListener(changeListener);


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
        gbc_textFieldRemuneracion.gridwidth = 6;
        gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldRemuneracion.gridx = 2;
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
        gbc_textFieldCiudad.gridwidth = 6;
        gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldCiudad.gridx = 2;
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
        gbc_textFieldDepartamento.gridwidth = 6;
        gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldDepartamento.gridx = 2;
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
        gbc_dateChooser.gridwidth = 6;
        gbc_dateChooser.insets = new Insets(0, 0, 5, 40);
        gbc_dateChooser.fill = GridBagConstraints.BOTH;
        gbc_dateChooser.gridx = 2;
        gbc_dateChooser.gridy = 14;
        getContentPane().add(dateChooserFechaDeAlta, gbc_dateChooser);
        
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.anchor = GridBagConstraints.WEST;
        gbc_btnCancelar.insets = new Insets(0, 0, 10, 5);
        gbc_btnCancelar.gridx = 2;
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
        gbc_btnAceptar.gridx = 6;
        gbc_btnAceptar.gridy = 15;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario();
            }
        });
	}
	
	protected void cmdAltaOfertaLaboralActionPerformed(ActionEvent arg0) {
		
		String nombreU = this.textFieldNombre.getText();
		String descripcionU = this.textAreaDescripcion.getText(); 
		String horarioU = this.textFieldHorario.getText();
		String remuneracionU = this.textFieldRemuneracion.getText();
		String ciudadU = this.textFieldCiudad.getText();
		String departamentoU = this.textFieldDepartamento.getText();
		DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		DTTipo tipPubOLU = (DTTipo) this.comboBoxTipPubOL.getSelectedItem();
		List<String> keysSeleccionadas = listKeys.getSelectedValuesList(); 
		Date fechaAltaU = dateChooserFechaDeAlta.getDate();
		
		
		if(checkForm()) {
			DTKeyword[] keys = new DTKeyword[keysSeleccionadas.size()];
			int i = 0;
			for(String k : keysSeleccionadas) { 
				keys[i] = new DTKeyword(k);
				i++;
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fechaDeAltaU = dateFormat.format(fechaAltaU);
			DTOfertaLaboral datosOL = new DTOfertaLaboral(nombreU, descripcionU, ciudadU, departamentoU, horarioU, 
																		Float.parseFloat(remuneracionU), fechaDeAltaU.toString(), keys);
			try {
				col.ingresarDatosOL(empresaU.getNickname(), tipPubOLU.getNombre(), datosOL);
				
				JOptionPane.showMessageDialog(this, "La Oferta Laboral se ha creado con éxito", "Alta de Oferta Laboral",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				setVisible(false);
			} catch(OfertaLaboralRepetidaException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private boolean checkForm() {
		
		String nombreU = this.textFieldNombre.getText();
		String descripcionU = this.textAreaDescripcion.getText();
		String horarioU = this.textFieldHorario.getText();
		String remuneracionU = this.textFieldRemuneracion.getText();
		String ciudadU = this.textFieldCiudad.getText();
		String departamentoU = this.textFieldDepartamento.getText();
		DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		DTTipo tipPubOLU = (DTTipo) this.comboBoxTipPubOL.getSelectedItem();
		List<String> keysSeleccionadas = listKeys.getSelectedValuesList(); 
		Date fechaAltaU = dateChooserFechaDeAlta.getDate();
		//long milisegundosDesdeEnero1900 = -2208988800000L; // Milisegundos desde la época para el 1 de enero de 1900
        long milisegundosDesdeEnero2000 = 946684800000L;
        Date fechaAntigua = new Date(milisegundosDesdeEnero2000);
		Date fechaDia = new Date();
		
		if(empresaU == null) {
			JOptionPane.showMessageDialog(this, "Se debe elegir una Empresa", "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (tipPubOLU == null) {
			JOptionPane.showMessageDialog(this, "Se debe elegir un Tipo de Publicacion", "Alta de Oferta Laboral", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
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
		
//		if (!this.validar(nombreU)) {
//			JOptionPane.showMessageDialog(this, "Debe ser un Nombre válido",
//					"Alta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}
		
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
	
	private LocalDate convertirDateALocalDate(Date date) {
        // Convertir Date a Instant
        Instant instant = date.toInstant();

        // Obtener ZoneId (Zona horaria)
        ZoneId defaultZoneId = ZoneId.systemDefault();

        // Crear LocalDate a partir de Instant y ZoneId
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();

        return localDate;
    }
	
	public boolean cargarEmpresas() {
		DefaultComboBoxModel<DTEmpresa> model;
		try {
			model = new DefaultComboBoxModel<DTEmpresa>(cu.listarEmpresasAOL());
			this.comboBoxEmpresas.setModel(model);
		} catch(EmpresasNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean cargarTiposPubOL() {
		DefaultComboBoxModel<DTTipo> model;
		try {
			model = new DefaultComboBoxModel<DTTipo>(col.listarTipoPublicacionOfertaLaboral());
			this.comboBoxTipPubOL.setModel(model);
		} catch(TipoPubNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean cargarKeywords() {
		DefaultListModel<String> model;
		try {
			model = new DefaultListModel<String>();
			String[] keys = col.listarKeywords();
			for(String k: keys) {
				model.addElement(k);
			}
			this.listKeys.setModel(model);
		} catch(KeywordsNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Alta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldHorario.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldRemuneracion.setText("");
		comboBoxEmpresas.setSelectedIndex(-1);
		comboBoxTipPubOL.setSelectedIndex(-1);
		listKeys.clearSelection();
		dateChooserFechaDeAlta.setDate(null);
	}
}
