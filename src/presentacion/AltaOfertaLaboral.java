package presentacion;

import javax.swing.JInternalFrame;

//import excepciones.UsuarioRepetidoException;
//import logica.IControladorUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	
	
	public AltaOfertaLaboral() {
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Oferta Laboral");
        setBounds(10, 10, 561, 495);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 150, 150, 100};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
        
        JComboBox comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 4;
        gbc_comboBox.insets = new Insets(0, 0, 8, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 2;
        getContentPane().add(comboBox, gbc_comboBox);
        
        JLabel lblNewLabel_1 = new JLabel("Tipos de Publicaciones de Ofertas Laborales: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridwidth = 4;
        gbc_lblNewLabel_1.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 3;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JComboBox comboBox_1 = new JComboBox();
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.gridwidth = 4;
        gbc_comboBox_1.insets = new Insets(0, 0, 8, 0);
        gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_1.gridx = 0;
        gbc_comboBox_1.gridy = 4;
        getContentPane().add(comboBox_1, gbc_comboBox_1);
        
        JLabel lblNewLabel_2 = new JLabel("Keywords");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(5, 0, 5, 0);
        gbc_lblNewLabel_2.gridwidth = 4;
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 5;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JComboBox comboBox_2 = new JComboBox();
        GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
        gbc_comboBox_2.gridwidth = 4;
        gbc_comboBox_2.insets = new Insets(0, 0, 8, 0);
        gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_2.gridx = 0;
        gbc_comboBox_2.gridy = 6;
        getContentPane().add(comboBox_2, gbc_comboBox_2);
        
        JLabel lblNewLabel_4 = new JLabel("Nombre: ");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 8;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridwidth = 3;
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 8;
        getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Descripción: ");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 9;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridwidth = 3;
        gbc_textField_1.insets = new Insets(5, 0, 5, 0);
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 9;
        getContentPane().add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Horario:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_9.gridx = 0;
        gbc_lblNewLabel_9.gridy = 10;
        getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
        
        textField_5 = new JTextField();
        GridBagConstraints gbc_textField_5 = new GridBagConstraints();
        gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_5.gridwidth = 3;
        gbc_textField_5.insets = new Insets(0, 0, 5, 0);
        gbc_textField_5.gridx = 1;
        gbc_textField_5.gridy = 10;
        getContentPane().add(textField_5, gbc_textField_5);
        textField_5.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Remuneración: ");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_6.insets = new Insets(0, 8, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 11;
        getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridwidth = 3;
        gbc_textField_2.insets = new Insets(5, 0, 5, 0);
        gbc_textField_2.gridx = 1;
        gbc_textField_2.gridy = 11;
        getContentPane().add(textField_2, gbc_textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Ciudad: ");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 0;
        gbc_lblNewLabel_7.gridy = 12;
        getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
        
        textField_3 = new JTextField();
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridwidth = 3;
        gbc_textField_3.insets = new Insets(5, 0, 5, 0);
        gbc_textField_3.gridx = 1;
        gbc_textField_3.gridy = 12;
        getContentPane().add(textField_3, gbc_textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Departamento:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_8.gridx = 0;
        gbc_lblNewLabel_8.gridy = 13;
        getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
        
        textField_4 = new JTextField();
        GridBagConstraints gbc_textField_4 = new GridBagConstraints();
        gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_4.gridwidth = 3;
        gbc_textField_4.insets = new Insets(5, 0, 5, 0);
        gbc_textField_4.gridx = 1;
        gbc_textField_4.gridy = 13;
        getContentPane().add(textField_4, gbc_textField_4);
        textField_4.setColumns(10);
        
        JButton btnNewButton = new JButton("Cancelar");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 15;
        getContentPane().add(btnNewButton, gbc_btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 15;
        getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	}
}
