package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaTipoPublicacionOfertaLaboral extends JInternalFrame {
	
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaTipoPublicacionOfertaLaboral frame = new AltaTipoPublicacionOfertaLaboral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public AltaTipoPublicacionOfertaLaboral() {
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		 setResizable(true);
	     setIconifiable(true);
	     setMaximizable(true);
	     setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	     setClosable(true);
	     setTitle("Alta de Tipo de Publicacion de Oferta Laboral");
	     setBounds(30, 30, 450, 311);
	     GridBagLayout gridBagLayout = new GridBagLayout();
	     gridBagLayout.columnWidths = new int[]{100, 150, 150, 5};
	     gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
	     gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0,Double.MIN_VALUE };
	     gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	     getContentPane().setLayout(gridBagLayout);
	     
	     JLabel lblNewLabel_4 = new JLabel("Ingrese los datos del Tipo de Publicacion");
	     lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
	     GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
	     gbc_lblNewLabel_4.gridheight = 2;
	     gbc_lblNewLabel_4.gridwidth = 2;
	     gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_4.gridx = 1;
	     gbc_lblNewLabel_4.gridy = 0;
	     getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
	     
	     JLabel lblNewLabel = new JLabel("Nombre:");
	     lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	     GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	     gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel.gridx = 0;
	     gbc_lblNewLabel.gridy = 2;
	     getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	     
	     textField_1 = new JTextField();
	     GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	     gbc_textField_1.gridwidth = 2;
	     gbc_textField_1.insets = new Insets(0, 0, 5, 5);
	     gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textField_1.gridx = 1;
	     gbc_textField_1.gridy = 2;
	     getContentPane().add(textField_1, gbc_textField_1);
	     textField_1.setColumns(10);
	     
	     JLabel lblNewLabel_1 = new JLabel("Descripción:");
	     lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	     gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_1.gridx = 0;
	     gbc_lblNewLabel_1.gridy = 3;
	     getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	     
	     textField = new JTextField();
	     textField.setColumns(10);
	     GridBagConstraints gbc_textField = new GridBagConstraints();
	     gbc_textField.gridwidth = 2;
	     gbc_textField.insets = new Insets(0, 0, 5, 5);
	     gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textField.gridx = 1;
	     gbc_textField.gridy = 3;
	     getContentPane().add(textField, gbc_textField);
	     
	     JLabel lblNewLabel_2 = new JLabel("Exposición:");
	     lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	     gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_2.gridx = 0;
	     gbc_lblNewLabel_2.gridy = 4;
	     getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
	     
	     textField_2 = new JTextField();
	     GridBagConstraints gbc_textField_2 = new GridBagConstraints();
	     gbc_textField_2.gridwidth = 2;
	     gbc_textField_2.insets = new Insets(0, 0, 5, 5);
	     gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textField_2.gridx = 1;
	     gbc_textField_2.gridy = 4;
	     getContentPane().add(textField_2, gbc_textField_2);
	     textField_2.setColumns(10);
	     
	     JLabel lblNewLabel_3 = new JLabel("Duración:");
	     lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
	     gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_3.gridx = 0;
	     gbc_lblNewLabel_3.gridy = 5;
	     getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
	     
	     textField_3 = new JTextField();
	     GridBagConstraints gbc_textField_3 = new GridBagConstraints();
	     gbc_textField_3.gridwidth = 2;
	     gbc_textField_3.insets = new Insets(0, 0, 5, 5);
	     gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textField_3.gridx = 1;
	     gbc_textField_3.gridy = 5;
	     getContentPane().add(textField_3, gbc_textField_3);
	     textField_3.setColumns(10);
	     
	     JLabel lblNewLabel_5 = new JLabel("Costo:");
	     lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	     gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_5.gridx = 0;
	     gbc_lblNewLabel_5.gridy = 6;
	     getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
	     
	     textField_4 = new JTextField();
	     GridBagConstraints gbc_textField_4 = new GridBagConstraints();
	     gbc_textField_4.gridwidth = 2;
	     gbc_textField_4.insets = new Insets(0, 0, 5, 5);
	     gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textField_4.gridx = 1;
	     gbc_textField_4.gridy = 6;
	     getContentPane().add(textField_4, gbc_textField_4);
	     textField_4.setColumns(10);
	     
	     JButton btnNewButton = new JButton("Cancelar");
	     btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
	     btnNewButton.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     	}
	     });
	     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	     gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
	     gbc_btnNewButton.gridx = 1;
	     gbc_btnNewButton.gridy = 8;
	     getContentPane().add(btnNewButton, gbc_btnNewButton);
	     
	     JButton btnNewButton_1 = new JButton("Aceptar");
	     btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 12));
	     GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	     gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
	     gbc_btnNewButton_1.gridx = 2;
	     gbc_btnNewButton_1.gridy = 8;
	     getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
	}

}
