package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;


	@SuppressWarnings("serial")
	public class AltaDeKeywords  extends JInternalFrame {
		private JTextField textField_1;
		public AltaDeKeywords() {
			setResizable(true);
			setIconifiable(true);
			setMaximizable(true);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    setClosable(true);
		    setTitle("Alta de Keywords");
		    setBounds(30, 30, 450, 184);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{70, 150, 150, 5};
			gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 30, 30};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			getContentPane().setLayout(gridBagLayout);
			
			JLabel lblNewLabel = new JLabel("Ingrese el nombre de la Keyword");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridheight = 2;
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 2;
			getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.gridwidth = 2;
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 2;
			getContentPane().add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
			
			JButton btnNewButton = new JButton("Cancelar");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 4;
			getContentPane().add(btnNewButton, gbc_btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Aceptar");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton_1.gridx = 2;
			gbc_btnNewButton_1.gridy = 4;
			getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		}
		
}
