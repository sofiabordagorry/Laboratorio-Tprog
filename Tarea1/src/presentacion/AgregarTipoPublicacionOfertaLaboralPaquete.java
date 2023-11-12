package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.IOfertaLaboral;
import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.TipoYaAgragadoException;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

@SuppressWarnings("serial")
public class AgregarTipoPublicacionOfertaLaboralPaquete extends JInternalFrame {
	
	private IOfertaLaboral IOL;
	
	private JTextField textFieldCantidad;
	private JComboBox<String> comboBoxPaquete;
	private JComboBox<String> comboBoxTipo;


	public AgregarTipoPublicacionOfertaLaboralPaquete(IOfertaLaboral iol) {
		
		IOL = iol;
		
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		 setResizable(true);
	     setIconifiable(true);
	     setMaximizable(true);
	     setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	     setClosable(true);
	     setTitle("Ag Tipo de Publicacion de Oferta Laboral");
	     setBounds(30, 30, 450, 294);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 150, 30, 150, 50};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 10, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Paquetes:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxPaquete = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.gridwidth = 3;
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 1;
		gbc_comboBoxPaquete.gridy = 1;
		getContentPane().add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		JLabel lblNewLabel_1 = new JLabel("Tipos de Oferta Laboral:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxTipo = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.gridwidth = 3;
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 1;
		gbc_comboBoxTipo.gridy = 3;
		getContentPane().add(comboBoxTipo, gbc_comboBoxTipo);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldCantidad = new JTextField();
		GridBagConstraints gbc_textFieldCantidad = new GridBagConstraints();
		gbc_textFieldCantidad.gridwidth = 2;
		gbc_textFieldCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidad.gridx = 2;
		gbc_textFieldCantidad.gridy = 5;
		getContentPane().add(textFieldCantidad, gbc_textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 7;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
	     btnAceptar.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		cmdAltaTipoPublicacionOfertaLaboral(arg0);
	     	}
	     });
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 7;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario();
            }
        });
	}

	private void limpiarFormulario() {
		textFieldCantidad.setText("");
		comboBoxPaquete.removeAllItems();
		comboBoxTipo.removeAllItems();
		setVisible(false);
	}
	
	protected void cmdAltaTipoPublicacionOfertaLaboral(ActionEvent arg0) {
		String cantidad = this.textFieldCantidad.getText();
		String tipPaquete = (String) this.comboBoxPaquete.getSelectedItem();
		String tipTipo = (String) this.comboBoxTipo.getSelectedItem();
		
		if(checkFormulario()) {
			try {
                IOL.agregarTipoAPaquete(Integer.parseInt(cantidad), tipPaquete, tipTipo);

                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "El Tipo de Publicacion se ha agregado con éxito", "Alta de Tipo de Publicacion de Oferta Laboral",
                        JOptionPane.INFORMATION_MESSAGE);
                // Limpio el internal frame antes de cerrar la ventana
                limpiarFormulario();
                setVisible(false);

            } catch (TipoYaAgragadoException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Tipo de Publicacion de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
               
            }

           
		}
	}
	
	private boolean checkFormulario() {
		String cantidad = this.textFieldCantidad.getText();
		String tipPaquete = (String) this.comboBoxPaquete.getSelectedItem();
		String tipTipo = (String) this.comboBoxTipo.getSelectedItem();
		
		
		if (cantidad.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Introduzca una cantidad", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		
		if (tipPaquete == "" ) {
            JOptionPane.showMessageDialog(this, "Elija un Paquete", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if (tipTipo == "" ) {
            JOptionPane.showMessageDialog(this, "Elija un Tipo de Publicacion", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		
        try {
           Integer.parseInt(cantidad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        
        if(Integer.parseInt(cantidad)<=0) {
			JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
        
        return true;
}

	public boolean cargarPaquetes() {
        DefaultComboBoxModel<String> model;
        try {
            model = new DefaultComboBoxModel<String>(IOL.listarNomPaquetesNoComprados());
            comboBoxPaquete.setModel(model);
        } catch (NoHayPaquetesException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        return true;

    }
	
	public boolean cargarTipos() {
        DefaultComboBoxModel<String> model;
        try {
            model = new DefaultComboBoxModel<String>(IOL.listarNomTipos());
            comboBoxTipo.setModel(model);
        } catch (NoHayTiposException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
        	return false;
    }
        return true;
	}

}
