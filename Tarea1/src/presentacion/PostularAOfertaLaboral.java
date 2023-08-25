package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension; 
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


	@SuppressWarnings("serial")
	public class PostularAOfertaLaboral  extends JInternalFrame {
		private JTextArea textAreaCV;
		private JTextArea textAreaMotivacion;
		private JComboBox<?> comboBoxEmpresas;
		private JComboBox<?> comboBoxOfertaLaboral;
		private JComboBox<?> comboBoxPostulante;
		/**
		 * @wbp.nonvisual location=571,9
		 */
		
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
	        
	        comboBoxEmpresas = new JComboBox<Object>();
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
	        
	        comboBoxOfertaLaboral = new JComboBox<Object>();
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
	        
	        comboBoxPostulante = new JComboBox<Object>();
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
	        
	        JDateChooser dateChooser = new JDateChooser();
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
			
			String cvU = this.textAreaCV.getText();
			String motivacionU = this.textAreaMotivacion.getText();
			//String fechaU = this.textFieldFecha.getText();
			
			if(checkForm()) {
				limpiarFormulario();
				setVisible(false);
			}
			
		}
		
		private boolean checkForm() {
			
			String cvU = this.textAreaCV.getText();
			String motivacionU = this.textAreaMotivacion.getText();
			//String fechaU = this.textFieldFecha.getText();
	
			
			if (cvU.isEmpty() || motivacionU.isEmpty() /*|| fechaU.isEmpty()*/ ) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Postular A Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
		}
		

		private void limpiarFormulario() {
			textAreaCV.setText("");
			textAreaMotivacion.setText("");
			//.setText("");
			comboBoxEmpresas.removeAllItems();
			comboBoxOfertaLaboral.removeAllItems();
			comboBoxPostulante.removeAllItems();
		}
	}
