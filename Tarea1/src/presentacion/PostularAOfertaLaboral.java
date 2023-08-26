package presentacion;

import java.awt.GridBagLayout;
import java.awt.Dimension; 
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.PostulantesNoExistenException;
import excepciones.UsuarioNoExisteException;
import excepciones.YaSePostuloException;
import logica.DTEmpresa;
import logica.IUsuario;
import logica.DTPostulante;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;


	@SuppressWarnings("serial")
	public class PostularAOfertaLaboral  extends JInternalFrame {
		private JTextArea textAreaCV;
		private JTextArea textAreaMotivacion;
		private JComboBox<DTEmpresa> comboBoxEmpresas;
		private JComboBox<DTOfertaLaboral> comboBoxOfertaLaboral;
		private JComboBox<DTPostulante> comboBoxPostulante;
		private JDateChooser dateChooser;
		/**
		 * @wbp.nonvisual location=571,9
		 */
		
	    private IUsuario controlUsr;

		
		public PostularAOfertaLaboral() {
			
			setResizable(true);
	        setIconifiable(true);
	        setMaximizable(true);
	        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        setClosable(true);
	        setTitle("Postular Usuario a Oferta Laboral");
	        setBounds(10, 10, 587, 529);
	        
	        GridBagLayout gridBagLayout = new GridBagLayout();
	        gridBagLayout.columnWidths = new int[]{100, 150, 300, 100};
	        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 74, 0, 42, 30, 28, 41, 0};
	        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
	        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0,  0.0, 0.0, 0.0, 4.9E-324, Double.MIN_VALUE, 0.0, 0.0, 0.0, 1.0, 0.0};
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
	        gbc_lblNewLabel.insets = new Insets(5, 15, 5, 0);
	        gbc_lblNewLabel.gridx = 0;
	        gbc_lblNewLabel.gridy = 1;
	        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	        
	        comboBoxEmpresas = new JComboBox<DTEmpresa>();
	        GridBagConstraints gbc_comboBoxEmpresas = new GridBagConstraints();
	        gbc_comboBoxEmpresas.gridwidth = 4;
	        gbc_comboBoxEmpresas.insets = new Insets(5, 5, 8, 5);
	        gbc_comboBoxEmpresas.fill = GridBagConstraints.HORIZONTAL;
	        gbc_comboBoxEmpresas.gridx = 0;
	        gbc_comboBoxEmpresas.gridy = 2;
	        getContentPane().add(comboBoxEmpresas, gbc_comboBoxEmpresas);
	        
	        JLabel lblNewLabel_1 = new JLabel("Oferta laboral:");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	        gbc_lblNewLabel_1.gridwidth = 4;
	        gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 0);
	        gbc_lblNewLabel_1.gridx = 0;
	        gbc_lblNewLabel_1.gridy = 3;
	        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	        
	        comboBoxOfertaLaboral = new JComboBox<DTOfertaLaboral>();
	        GridBagConstraints gbc_comboBoxOfertaLaboral = new GridBagConstraints();
	        gbc_comboBoxOfertaLaboral.gridwidth = 4;
	        gbc_comboBoxOfertaLaboral.insets = new Insets(5, 5, 8, 5);
	        gbc_comboBoxOfertaLaboral.fill = GridBagConstraints.HORIZONTAL;
	        gbc_comboBoxOfertaLaboral.gridx = 0;
	        gbc_comboBoxOfertaLaboral.gridy = 4;
	        getContentPane().add(comboBoxOfertaLaboral, gbc_comboBoxOfertaLaboral);
	        
	        JLabel lblNewLabel_2 = new JLabel("Postulante: ");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	        gbc_lblNewLabel_2.insets = new Insets(5, 5, 5, 0);
	        gbc_lblNewLabel_2.gridwidth = 4;
	        gbc_lblNewLabel_2.gridx = 0;
	        gbc_lblNewLabel_2.gridy = 5;
	        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
	        
	        comboBoxPostulante = new JComboBox<DTPostulante>();
	        GridBagConstraints gbc_comboBoxPostulante = new GridBagConstraints();
	        gbc_comboBoxPostulante.gridwidth = 4;
	        gbc_comboBoxPostulante.insets = new Insets(5, 5, 8, 5);
	        gbc_comboBoxPostulante.fill = GridBagConstraints.HORIZONTAL;
	        gbc_comboBoxPostulante.gridx = 0;
	        gbc_comboBoxPostulante.gridy = 6;
	        getContentPane().add(comboBoxPostulante, gbc_comboBoxPostulante);
	        
	        JLabel lblNewLabel_4 = new JLabel("CV reducido: ");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
	        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
	        gbc_lblNewLabel_4.insets = new Insets(5, 5, 5, 5);
	        gbc_lblNewLabel_4.gridx = 0;
	        gbc_lblNewLabel_4.gridy = 7;
	        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
	        
	        textAreaCV = new JTextArea(); // Usar JTextArea en lugar de JTextField
	        GridBagConstraints gbc_textAreaCV = new GridBagConstraints();
	        gbc_textAreaCV.gridheight = 2;
	        gbc_textAreaCV.fill = GridBagConstraints.BOTH;
	        gbc_textAreaCV.gridwidth = 3;
	        gbc_textAreaCV.insets = new Insets(5, 5, 5, 5);
	        gbc_textAreaCV.gridx = 1;
	        gbc_textAreaCV.gridy = 7;
	        textAreaCV.setRows(5); 
	        getContentPane().add(textAreaCV, gbc_textAreaCV);
	        textAreaCV.setColumns(10);
	
	        JLabel lblNewLabel_5 = new JLabel("Motivación: ");
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
	        gbc_lblNewLabel_5.insets = new Insets(5, 5, 5, 5);
	        gbc_lblNewLabel_5.gridx = 0;
	        gbc_lblNewLabel_5.gridy = 9;
	        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
	        
	        textAreaMotivacion = new JTextArea();
	        GridBagConstraints gbc_textAreaMotivacion = new GridBagConstraints();
	        gbc_textAreaMotivacion.gridheight = 2;
	        gbc_textAreaMotivacion.fill = GridBagConstraints.BOTH;
	        gbc_textAreaMotivacion.gridwidth = 3;
	        gbc_textAreaMotivacion.insets = new Insets(5, 5, 5, 5);
	        gbc_textAreaMotivacion.gridx = 1;
	        gbc_textAreaMotivacion.gridy = 9;
	        textAreaMotivacion.setRows(5); 
	        getContentPane().add(textAreaMotivacion, gbc_textAreaMotivacion);
	        textAreaMotivacion.setColumns(10);
	        
	        JLabel lblNewLabel_9 = new JLabel("Fecha de postulación:");
	        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
	        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
	        gbc_lblNewLabel_9.insets = new Insets(5, 5, 5, 5);
	        gbc_lblNewLabel_9.gridx = 1;
	        gbc_lblNewLabel_9.gridy = 11;
	        getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
	        
	        dateChooser = new JDateChooser();
	        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
	        gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
	        gbc_dateChooser.fill = GridBagConstraints.BOTH;
	        gbc_dateChooser.gridx = 2;
	        gbc_dateChooser.gridy = 11;
	        getContentPane().add(dateChooser, gbc_dateChooser);
	        
	        JButton btnCancelar = new JButton("Cancelar");
	        btnCancelar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		limpiarFormulario();
	        		setVisible(false);
	        	}
	        });
	        
	        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
	        gbc_btnCancelar.anchor = GridBagConstraints.WEST;
	        gbc_btnCancelar.insets = new Insets(0, 110, 5, 5);
	        gbc_btnCancelar.gridx = 1;
	        gbc_btnCancelar.gridy = 12;
	        getContentPane().add(btnCancelar, gbc_btnCancelar);
	        
	        JButton btnAceptar = new JButton("Aceptar");
	        btnAceptar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		cmdPostularAOfertaLaboralActionPerformed(arg0);
	        	}
	            });
	        
	        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 11));
	        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
	        gbc_btnAceptar.anchor = GridBagConstraints.WEST;
	        gbc_btnAceptar.insets = new Insets(0, 20, 5, 5);
	        gbc_btnAceptar.gridx = 2;
	        gbc_btnAceptar.gridy = 12;
	        getContentPane().add(btnAceptar, gbc_btnAceptar);
		}
		
		protected void cmdPostularAOfertaLaboralActionPerformed(ActionEvent arg0) {
			
			String CVReducidoU = this.textAreaCV.getText();
			String motivacionU = this.textAreaMotivacion.getText();
			Date fechaAltaU = this.dateChooser.getDate();
			DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
			DTPostulante postulanteU = (DTPostulante) this.comboBoxPostulante.getSelectedItem();
			DTOfertaLaboral ofertaLaboralU = (DTOfertaLaboral) this.comboBoxOfertaLaboral.getSelectedItem(); 
			
			
			if(checkForm()) {
				LocalDate fechaDeAltaU = this.convertirDateALocalDate(fechaAltaU);
				
				try {
					controlUsr.ingresarPostulacion(CVReducidoU, motivacionU, fechaDeAltaU, empresaU.getNickname(), ofertaLaboralU.getNombre(), postulanteU.getNickname());
					
					JOptionPane.showMessageDialog(this, "El postulante se ha postulado con éxito", "Postular a Oferta Laboral",
	                        JOptionPane.INFORMATION_MESSAGE);} 
				catch(YaSePostuloException e) {	JOptionPane.showMessageDialog(this, e.getMessage(), 
						"Postular A Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);}
				
				limpiarFormulario();
				setVisible(false);
			}
			
		}

		
		private boolean checkForm() {
			
			String cvU = this.textAreaCV.getText();
			String motivacionU = this.textAreaMotivacion.getText();
			Date fechaAltaU = this.dateChooser.getDate();
			DTEmpresa empresaU = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
			DTPostulante postulanteU = (DTPostulante) this.comboBoxPostulante.getSelectedItem();
			DTOfertaLaboral ofertaLaboralU = (DTOfertaLaboral) this.comboBoxOfertaLaboral.getSelectedItem(); ;
	
			
			if (cvU.isEmpty() || motivacionU.isEmpty() ) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Postular A Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if (fechaAltaU == null ) {
				JOptionPane.showMessageDialog(this, "Se debe elegir una Fecha de Alta",  "Postular A Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			

			if(empresaU == null) {
				JOptionPane.showMessageDialog(this, "Se debe elegir una Empresa", "Postular A Oferta Laboral", 
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

			if(postulanteU == null) {
				JOptionPane.showMessageDialog(this, "Se debe elegir un Postulante", "Postular A Oferta Laboral", 
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(ofertaLaboralU == null) {
				JOptionPane.showMessageDialog(this, "Se debe elegir una Oferta Laboral", "Postular A Oferta Laboral", 
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
		}
		

		public boolean cargarEmpresas() {
			DefaultComboBoxModel<DTEmpresa> model;
			try {
				model = new DefaultComboBoxModel<DTEmpresa>(controlUsr.listarEmpresasAOL());
				this.comboBoxEmpresas.setModel(model);
			} catch(EmpresasNoExistenException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), 
						"Postular A Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}

		
   public boolean  cargarOfertasLaborales() {
		DTEmpresa empresaU = (DTEmpresa) this.comboBoxOfertaLaboral.getSelectedItem();
		DefaultComboBoxModel<DTOfertaLaboral> model;
		try {
			model= new DefaultComboBoxModel<DTOfertaLaboral>(controlUsr.listarOfertasLaboralesVigentes(empresaU.getNickname()));
			this.comboBoxOfertaLaboral.setModel(model);
			} catch (EmpresaSinOfertasException e){
				JOptionPane.showMessageDialog(this, e.getMessage(), 
						"Postular A Oferta Laboral", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		return true;
		
}

		   
		   public boolean cargarPostulantes() {
		        DefaultComboBoxModel<DTPostulante> model;
		        try {
		            model = new DefaultComboBoxModel<DTPostulante>(controlUsr.listarPostulantes());
		            this.comboBoxPostulante.setModel(model);
		        }
		         catch (PostulantesNoExistenException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), 
								"Postular A Oferta Laboral", JOptionPane.ERROR_MESSAGE);
						return false;		        }
		        return true;
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

		
		private void limpiarFormulario() {
			textAreaCV.setText("");
			textAreaMotivacion.setText("");
			comboBoxEmpresas.removeAllItems();
			comboBoxOfertaLaboral.removeAllItems();
			comboBoxPostulante.removeAllItems();
		}
	}
