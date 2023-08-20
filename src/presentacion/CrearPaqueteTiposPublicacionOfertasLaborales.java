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
public class CrearPaqueteTiposPublicacionOfertasLaborales extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public CrearPaqueteTiposPublicacionOfertasLaborales() {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear Paquetes de Tipos de Publicacion de Ofertas Laborales");
        setBounds(10, 40, 464, 246);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 150, 150};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 1.0, 0};
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Ingrese  los siguientes datos: ");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 3;
        gbc_lblNewLabel.insets = new Insets(5, 0, 8, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 2;
        gbc_textField.insets = new Insets(0, 0, 5, 15);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción: ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 2;
        gbc_textField_1.insets = new Insets(0, 0, 5, 15);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 2;
        getContentPane().add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Periódo de validez:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 5, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 3;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.gridwidth = 2;
        gbc_textField_2.insets = new Insets(0, 0, 5, 15);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 1;
        gbc_textField_2.gridy = 3;
        getContentPane().add(textField_2, gbc_textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Descuento:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 4;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        textField_3 = new JTextField();
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.gridwidth = 2;
        gbc_textField_3.insets = new Insets(0, 0, 5, 15);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 1;
        gbc_textField_3.gridy = 4;
        getContentPane().add(textField_3, gbc_textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Costo asociado: ");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 5;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        GridBagConstraints gbc_textField_4 = new GridBagConstraints();
        gbc_textField_4.gridwidth = 2;
        gbc_textField_4.insets = new Insets(0, 0, 5, 15);
        gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_4.gridx = 1;
        gbc_textField_4.gridy = 5;
        getContentPane().add(textField_4, gbc_textField_4);
        
        JButton btnNewButton = new JButton("Cancelar");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 20);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 6;
        getContentPane().add(btnNewButton, gbc_btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 20);
        gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_1.gridx = 2;
        gbc_btnNewButton_1.gridy = 6;
        getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	}
}
