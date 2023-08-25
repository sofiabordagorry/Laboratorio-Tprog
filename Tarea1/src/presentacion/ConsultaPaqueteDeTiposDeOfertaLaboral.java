package presentacion;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ConsultaPaqueteDeTiposDeOfertaLaboral extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPaqueteDeTiposDeOfertaLaboral frame = new ConsultaPaqueteDeTiposDeOfertaLaboral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaPaqueteDeTiposDeOfertaLaboral() {
        setBounds(10, 40, 487, 246);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);    
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 150, 150};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0};
        gridBagLayout.rowWeights = new double[]{0, 0, 1.0, 1.0, 1.0, 0, 0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        // LISTAR PAQUETES
        
        JLabel lblTitle = new JLabel("Seleccione un paquete para ver su información");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblTitle = new GridBagConstraints();
        gbc_lblTitle.gridwidth = 3;
        gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
        gbc_lblTitle.gridx = 0;
        gbc_lblTitle.gridy = 0;
        getContentPane().add(lblTitle, gbc_lblTitle);
        
        JLabel lblPaquetes = new JLabel("Paquete:");
        lblPaquetes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblPaquetes = new GridBagConstraints();
        gbc_lblPaquetes.anchor = GridBagConstraints.EAST;
        gbc_lblPaquetes.insets = new Insets(0, 0, 5, 5);
        gbc_lblPaquetes.gridx = 0;
        gbc_lblPaquetes.gridy = 1;
        getContentPane().add(lblPaquetes, gbc_lblPaquetes);
        
        JComboBox<String> comboBox = new JComboBox<String>();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 2;
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 1;
        getContentPane().add(comboBox, gbc_comboBox);
        
        JLabel lblDatos = new JLabel("Datos:");
        lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblDatos = new GridBagConstraints();
        gbc_lblDatos.anchor = GridBagConstraints.EAST;
        gbc_lblDatos.insets = new Insets(0, 0, 5, 5);
        gbc_lblDatos.gridx = 0;
        gbc_lblDatos.gridy = 2;
        getContentPane().add(lblDatos, gbc_lblDatos);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 2;
        getContentPane().add(panel, gbc_panel);
        
        JLabel lblTiposOL = new JLabel("Tipos de publicación de ofertas laborales:");
        lblTiposOL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblTiposOL = new GridBagConstraints();
        gbc_lblTiposOL.anchor = GridBagConstraints.EAST;
        gbc_lblTiposOL.insets = new Insets(0, 0, 5, 5);
        gbc_lblTiposOL.gridx = 0;
        gbc_lblTiposOL.gridy = 3;
        getContentPane().add(lblTiposOL, gbc_lblTiposOL);
        
        JList list = new JList();
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.gridwidth = 2;
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 1;
        gbc_list.gridy = 3;
        getContentPane().add(list, gbc_list);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        JLabel lblNewLabel = new JLabel("Datos tipo:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 4;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.gridwidth = 2;
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 4;
        getContentPane().add(panel_2, gbc_panel_2);
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 1;
        gbc_btnCancelar.gridy = 5;
        getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

}
