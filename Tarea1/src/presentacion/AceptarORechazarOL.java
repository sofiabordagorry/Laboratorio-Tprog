package presentacion;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import excepciones.EmpresasNoExistenException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.EmpresaSinOfertasException;
import logica.DTEmpresa;
import logica.DTOfertaLaboral;
import logica.EstadoOL;
import logica.IOfertaLaboral;
import logica.IUsuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AceptarORechazarOL extends JInternalFrame {
	
	
	private JComboBox<DTEmpresa> comboBoxEmpresas; 
	private JComboBox<DTOfertaLaboral> comboBoxOL;
	private IUsuario contUsuario;
	private IOfertaLaboral contOfertaLaboral;
	
	public AceptarORechazarOL(IUsuario IU, IOfertaLaboral IOL) {
	
		contUsuario = IU;
		contOfertaLaboral = IOL;

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		 setResizable(true);
	     setIconifiable(true);
	     setMaximizable(true);
	     setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	     setClosable(true);
	     setTitle("Aceptar o Rechazar Oferta Laboral");
	     setBounds(30, 30, 659, 226);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 150, 30, 150, 50};
		gridBagLayout.rowHeights = new int[]{30, 30, 30, 30, 10, 30, 30};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE, 1.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Selecione la Empresa:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
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
		gbc_comboBoxEmpresas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEmpresas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresas.gridx = 3;
		gbc_comboBoxEmpresas.gridy = 1;
		getContentPane().add(comboBoxEmpresas, gbc_comboBoxEmpresas);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione la Oferta Laboral:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxOL = new JComboBox<DTOfertaLaboral>();
		
		GridBagConstraints gbc_comboBoxOL = new GridBagConstraints();
		gbc_comboBoxOL.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOL.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOL.gridx = 3;
		gbc_comboBoxOL.gridy = 2;
		getContentPane().add(comboBoxOL, gbc_comboBoxOL);
		
		JButton btnNewButtonRechazar = new JButton("Rechazar");
		btnNewButtonRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdRechazar(e);
			}
		
		});
		GridBagConstraints gbc_btnNewButtonRechazar = new GridBagConstraints();
		gbc_btnNewButtonRechazar.anchor = GridBagConstraints.EAST;
		gbc_btnNewButtonRechazar.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonRechazar.gridx = 1;
		gbc_btnNewButtonRechazar.gridy = 4;
		getContentPane().add(btnNewButtonRechazar, gbc_btnNewButtonRechazar);
		
		JButton btnNewButtonAceptar = new JButton("Aceptar");
		btnNewButtonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAceptar(e);
			}
		
		});
		GridBagConstraints gbc_btnNewButtonAceptar = new GridBagConstraints();
		gbc_btnNewButtonAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonAceptar.gridx = 3;
		gbc_btnNewButtonAceptar.gridy = 4;
		getContentPane().add(btnNewButtonAceptar, gbc_btnNewButtonAceptar);
		
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario();
            }
        });
	}
	
	protected void limpiarFormulario() {
		comboBoxEmpresas.setSelectedIndex(-1);
		comboBoxOL.setSelectedIndex(-1);
		setVisible(false);
	}
	
	protected void cmdRechazar(ActionEvent e) {
		DTOfertaLaboral tipOL = (DTOfertaLaboral) this.comboBoxOL.getSelectedItem();
		DTEmpresa tipEmpresa = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		boolean correcto = true;
		if(tipEmpresa == null) {
		//if(comboBoxEmpresas.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Elija una Empresa",  "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
		if(tipOL == null) {
		//if(comboBoxOL.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Elija una Oferta Laboral", "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
		if(correcto) {
			
			contOfertaLaboral.acepRechOL(EstadoOL.Rechazada, tipOL.getNombre());
			
			 // Muestro éxito de la operación
            JOptionPane.showMessageDialog(this, "La oferta laboral se ha sido rechazada", "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.INFORMATION_MESSAGE);
			
            limpiarFormulario();
		}
	}
	
	protected void cmdAceptar(ActionEvent e) {
		DTOfertaLaboral tipOL = (DTOfertaLaboral) this.comboBoxOL.getSelectedItem();
		DTEmpresa tipEmpresa = (DTEmpresa) this.comboBoxEmpresas.getSelectedItem();
		boolean correcto = true;
		if(tipEmpresa == null) {
		//if(comboBoxEmpresas.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Elija una Empresa",  "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
		if(tipOL == null) {
		//if(comboBoxOL.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Elija una Oferta Laboral", "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            correcto = false;
        }
		if(correcto) {
			
			contOfertaLaboral.acepRechOL(EstadoOL.Aceptada, tipOL.getNombre());
			
			 // Muestro éxito de la operación
            JOptionPane.showMessageDialog(this, "La oferta laboral se ha sido aceptada", "Aceptar o Rechazar Oferta Laboral",
                    JOptionPane.INFORMATION_MESSAGE);
			
            limpiarFormulario();
		}
		
	}
	
	
	public boolean cargarEmpresas() {
		comboBoxEmpresas.setSelectedIndex(-1);
		DefaultComboBoxModel<DTEmpresa> model;
		try {
			model = new DefaultComboBoxModel<DTEmpresa>(contUsuario.listarEmpresas());
        	comboBoxEmpresas.setModel(model);
        	comboBoxEmpresas.setSelectedIndex(-1);
		}catch(EmpresasNoExistenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Aceptar o Rechazar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}catch(NoExistenEmpresasOfertasLaboralesException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Aceptar o Rechazar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		};
		return true;
	}
	
	
	public void cargarOfertasLaborales(String selectedItem) {
		comboBoxOL.setSelectedIndex(-1);
		DefaultComboBoxModel<DTOfertaLaboral> modelO;
		
		try {
			 modelO = new DefaultComboBoxModel<DTOfertaLaboral>(contUsuario.listarOfertasLaboralesIngresadas(selectedItem));
			comboBoxOL.setModel(modelO);
			comboBoxOL.setSelectedIndex(-1);
		} catch(EmpresaSinOfertasException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

