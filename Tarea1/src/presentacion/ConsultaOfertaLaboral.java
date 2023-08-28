package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import excepciones.EmpresasNoExistenException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import logica.DTEmpresa;
import logica.DTKeyword;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.DTUsuario;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorUsuario;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ConsultaOfertaLaboral extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldFechaDeAlta;
	private JTextField textFieldCosto;
	private JTable tablePostulaciones;
	private IUsuario contUsuario;
	private IOfertaLaboral contOfertaLaboral;
	private JComboBox<DTEmpresa> comboBoxEmpresas; 
	private JComboBox<DTOfertaLaboral> comboBoxOL;
	private JComboBox<DTKeyword> comboBoxKeywords;
	private JTextArea textAreaDescripcion;
	private JTextArea textAreaMotivacion;
	private JTextArea textAreaCV;
	private DefaultTableModel model;

	public ConsultaOfertaLaboral(IUsuario IU, IOfertaLaboral IOL) { 
		contUsuario = IU;
		contOfertaLaboral = IOL;
		setTitle("Consulta de Oferta Laboral");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 550, 655);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 60, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Seleccione una Empresa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(10, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		 
		comboBoxEmpresas = new JComboBox<DTEmpresa>();
	    comboBoxEmpresas.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	                if(comboBoxEmpresas.getSelectedIndex() != -1) {
	                	DTEmpresa selectedItem = (DTEmpresa) comboBoxEmpresas.getSelectedItem();
	                	cargarOfertasLaborales(selectedItem.getNickname());
	                }
	            }
	    });
		GridBagConstraints gbc_comboBoxEmpresas = new GridBagConstraints();
		gbc_comboBoxEmpresas.insets = new Insets(10, 0, 5, 10);
		gbc_comboBoxEmpresas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresas.gridx = 2;
		gbc_comboBoxEmpresas.gridy = 0;
		getContentPane().add(comboBoxEmpresas, gbc_comboBoxEmpresas);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione una Oferta Laboral:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(5, 10, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxOL = new JComboBox<DTOfertaLaboral>();
	    comboBoxOL.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 if(comboBoxOL.getSelectedIndex() != -1) {
	                DTOfertaLaboral selectedItem = (DTOfertaLaboral) comboBoxOL.getSelectedItem();
	                cargarDatos(selectedItem.getNombre());
	    		 }
	         }
	    });
		GridBagConstraints gbc_comboBoxOL = new GridBagConstraints();
		gbc_comboBoxOL.insets = new Insets(5, 0, 5, 10);
		gbc_comboBoxOL.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOL.gridx = 2;
		gbc_comboBoxOL.gridy = 1;
		getContentPane().add(comboBoxOL, gbc_comboBoxOL);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 10);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 3;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
 
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true); 
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setEditable(false);
		scrollPane_1.setViewportView(textAreaDescripcion);
		
		JLabel lblNewLabel_4 = new JLabel("Ciudad:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 4;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Departamento:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 5;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);
		textFieldDepartamento.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Horario:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 6;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		textFieldHorario = new JTextField();
		textFieldHorario.setEditable(false);
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 6;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);
		textFieldHorario.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Remuneracion:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 7;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 7;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);
		textFieldRemuneracion.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha de Alta:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 8;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		textFieldFechaDeAlta = new JTextField();
		textFieldFechaDeAlta.setEditable(false);
		GridBagConstraints gbc_textFieldFechaDeAlta = new GridBagConstraints();
		gbc_textFieldFechaDeAlta.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldFechaDeAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaDeAlta.gridx = 2;
		gbc_textFieldFechaDeAlta.gridy = 8;
		getContentPane().add(textFieldFechaDeAlta, gbc_textFieldFechaDeAlta);
		textFieldFechaDeAlta.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Costo Asociado:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.gridwidth = 2;
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 9;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 10);
		gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCosto.gridx = 2;
		gbc_textFieldCosto.gridy = 9;
		getContentPane().add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		JLabel lblKeywords = new JLabel("KeyWords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.gridwidth = 2;
		gbc_lblKeywords.anchor = GridBagConstraints.EAST;
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.gridx = 0;
		gbc_lblKeywords.gridy = 10;
		getContentPane().add(lblKeywords, gbc_lblKeywords);
		
 
		comboBoxKeywords = new JComboBox<DTKeyword>();
		GridBagConstraints gbc_comboBoxKeywords = new GridBagConstraints();
		gbc_comboBoxKeywords.insets = new Insets(0, 0, 5, 10);
		gbc_comboBoxKeywords.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxKeywords.gridx = 2;
		gbc_comboBoxKeywords.gridy = 10;
		getContentPane().add(comboBoxKeywords, gbc_comboBoxKeywords);
		
		JLabel lblNewLabel_11 = new JLabel("Postulaciones");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.gridwidth = 3;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 11;
		getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JScrollPane scrollPanePostulaciones = new JScrollPane();
		GridBagConstraints gbc_scrollPanePostulaciones = new GridBagConstraints();
		gbc_scrollPanePostulaciones.gridheight = 2;
		gbc_scrollPanePostulaciones.gridwidth = 2;
		gbc_scrollPanePostulaciones.insets = new Insets(0, 10, 5, 10);
		gbc_scrollPanePostulaciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePostulaciones.gridx = 0;
		gbc_scrollPanePostulaciones.gridy = 12;
		getContentPane().add(scrollPanePostulaciones, gbc_scrollPanePostulaciones);
		
		model = new DefaultTableModel();
		model.addColumn("Postulante");
		model.addColumn("");
		model.addColumn("");
		model.addColumn("Fecha");
		
		tablePostulaciones = new JTable(model);
        tablePostulaciones.setCellSelectionEnabled(true);
        tablePostulaciones.setDefaultEditor(Object.class, null);
		tablePostulaciones.getColumnModel().getColumn(1).setWidth(0);
		tablePostulaciones.getColumnModel().getColumn(1).setMinWidth(0);
		tablePostulaciones.getColumnModel().getColumn(1).setMaxWidth(0);
		tablePostulaciones.getColumnModel().getColumn(2).setWidth(0);
		tablePostulaciones.getColumnModel().getColumn(2).setMinWidth(0);
		tablePostulaciones.getColumnModel().getColumn(2).setMaxWidth(0);
		tablePostulaciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tablePostulaciones.getSelectedRow();
				if(selectedRow != -1) {
					mostrarCVyMotivacion(selectedRow);
				}
			}
		});
		scrollPanePostulaciones.setViewportView(tablePostulaciones);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		
		JScrollPane scrollPaneCV = new JScrollPane();
		GridBagConstraints gbc_scrollPaneCV = new GridBagConstraints();
		gbc_scrollPaneCV.insets = new Insets(0, 0, 5, 10);
		gbc_scrollPaneCV.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCV.gridx = 2;
		gbc_scrollPaneCV.gridy = 12;
		getContentPane().add(scrollPaneCV, gbc_scrollPaneCV);
		
 
		textAreaCV = new JTextArea();
		textAreaCV.setWrapStyleWord(true); 
        textAreaCV.setLineWrap(true);
        textAreaCV.setEditable(false);
		scrollPaneCV.setViewportView(textAreaCV);
		
		JLabel lblNewLabel_6 = new JLabel("Curriculum Vitae");
		scrollPaneCV.setColumnHeaderView(lblNewLabel_6);
		
		JScrollPane scrollPaneMotivacion = new JScrollPane();
		GridBagConstraints gbc_scrollPaneMotivacion = new GridBagConstraints();
		gbc_scrollPaneMotivacion.insets = new Insets(0, 0, 5, 10);
		gbc_scrollPaneMotivacion.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneMotivacion.gridx = 2;
		gbc_scrollPaneMotivacion.gridy = 13;
		getContentPane().add(scrollPaneMotivacion, gbc_scrollPaneMotivacion);
		
 
		textAreaMotivacion = new JTextArea();
		textAreaMotivacion.setWrapStyleWord(true); 
        textAreaMotivacion.setLineWrap(true);
        textAreaMotivacion.setEditable(false);
		scrollPaneMotivacion.setViewportView(textAreaMotivacion);
		
		JLabel lblNewLabel_12 = new JLabel("Motivacion");
		scrollPaneMotivacion.setColumnHeaderView(lblNewLabel_12);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridwidth = 3;
		gbc_btnCancelar.insets = new Insets(5, 40, 10, 40);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 14;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	public boolean cargarEmpresas() {
		model.setRowCount(0);
		comboBoxOL.setSelectedIndex(-1);
		DefaultComboBoxModel<DTEmpresa> model;
		try {
			model = new DefaultComboBoxModel<DTEmpresa>(contUsuario.listarEmpresas());
        	comboBoxEmpresas.setModel(model);
		}catch(EmpresasNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}catch(NoExistenEmpresasOfertasLaboralesException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		};
		return true;
	}
	
	public void cargarOfertasLaborales(String selectedItem) {
		model.setRowCount(0);
		try {
			DefaultComboBoxModel<DTOfertaLaboral> model = new DefaultComboBoxModel<DTOfertaLaboral>(contUsuario.listarOfertasLaborales(selectedItem));
			comboBoxOL.setModel(model);
		} catch(OfertasLaboralesNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cargarDatos(String selectedItem) {
		model.setRowCount(0);
		DTOfertaLaboral datosOL = contOfertaLaboral.mostrarDatosOfertaLaboral(selectedItem);
		textFieldNombre.setText(datosOL.getNombre());
		textAreaDescripcion.setText(datosOL.getDescripcion());
		textFieldCiudad.setText(datosOL.getCiudad());
		textFieldDepartamento.setText(datosOL.getDepartamento());
		textFieldHorario.setText(datosOL.getHorario());
		textFieldRemuneracion.setText(String.valueOf(datosOL.getRemuneracion()));
		textFieldFechaDeAlta.setText(datosOL.getFechaDeAlta().toString());
		textFieldCosto.setText(String.valueOf(datosOL.getCostoAsociado()));
		Map<String, DTKeyword> keys = datosOL.getKeywords();
		DTKeyword[] arrKeys = new DTKeyword[keys.size()];
		if(keys.size() > 0) {
			int i = 0;
			for(Map.Entry<String, DTKeyword> entry : keys.entrySet()) {
				arrKeys[i] = entry.getValue();
				i++;
			}
			DefaultComboBoxModel<DTKeyword> modelKeys = new DefaultComboBoxModel<DTKeyword>(arrKeys);
			comboBoxKeywords.setModel(modelKeys);
		}
		List<DTPostulacion> postulaciones = datosOL.getPostulaciones();

		for(DTPostulacion p : postulaciones) {
			Object[] rowData = {p.getPostulante(),p.getCVReducido(),p.getMotivacion(), p.getFecha().toString()};
			model.addRow(rowData);
		}
	}
	
	public void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaDeAlta.setText("");
		textFieldCosto.setText("");
		tablePostulaciones.removeAll();
		textAreaDescripcion.setText("");
		comboBoxEmpresas.setSelectedIndex(-1);
		comboBoxOL.setSelectedIndex(-1);
	}
	
	public void referenceConsultaUsuario(String nombreOL, String empresa) {
		cargarEmpresas();
		comboBoxEmpresas.setSelectedItem(empresa);
		cargarOfertasLaborales(empresa);
		comboBoxOL.setSelectedItem(nombreOL);
		cargarDatos(nombreOL);
	}
	public void mostrarCVyMotivacion(int p) {
		textAreaCV.setText(tablePostulaciones.getValueAt(p, 1).toString());
		textAreaMotivacion.setText(tablePostulaciones.getValueAt(p, 2).toString());

	}

}
