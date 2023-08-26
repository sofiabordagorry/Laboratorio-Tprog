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
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import logica.DTPaquete;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.ManejadorTipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.Tipo;

import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ConsultaPaqueteDeTiposDeOfertaLaboral extends JInternalFrame {
	
	private IOfertaLaboral iOL;

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
		// Interfaz OL
    	Factory fact = Factory.getInstance();
    	iOL = fact.getIOfertaLaboral();
    	
    	// ManejadorTipo
    	ManejadorTipo m = ManejadorTipo.getInstancia();
    	
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
        
        JComboBox<ComboBoxItem> comboBox = new JComboBox<>(new ComboBoxModel(m.getPaquetes()));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 2;
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 1;
        getContentPane().add(comboBox, gbc_comboBox);
        
        JTextArea areaDatos = new JTextArea();
        JList<PaqueteTipo> list = new JList<>();
        comboBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JComboBox<ComboBoxItem> source = (JComboBox<ComboBoxItem>) e.getSource();
        		ComboBoxItem selectedItem = (ComboBoxItem)source.getSelectedItem();
        		
        		if (selectedItem != null) {
        			Paquete p = selectedItem.getPaquete();
        			//getDatosPaquete
        			String datos = "Nombre: " + p.getNombre() + "\nDescripcion: " + p.getDescripcion() + "\nPeriodo de validez: " + p.getPeriodoDeValidez() + "\nDescuento: " + p.getDescuento() + "\nCosto: " + p.getCostoAsociado();
        			areaDatos.setText(datos);
        			
        			List<PaqueteTipo> pqt = p.getPaquetesTipos();
        			list.setListData(pqt.toArray(new PaqueteTipo[0]));
        		}
        	}
        });
        
        JLabel lblDatos = new JLabel("Datos:");
        lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblDatos = new GridBagConstraints();
        gbc_lblDatos.anchor = GridBagConstraints.EAST;
        gbc_lblDatos.insets = new Insets(0, 0, 5, 5);
        gbc_lblDatos.gridx = 0;
        gbc_lblDatos.gridy = 2;
        getContentPane().add(lblDatos, gbc_lblDatos);
        
        areaDatos.setEditable(false);
        GridBagConstraints gbc_areaDatos = new GridBagConstraints();
        gbc_areaDatos.gridwidth = 2;
        gbc_areaDatos.insets = new Insets(0, 0, 5, 0);
        gbc_areaDatos.fill = GridBagConstraints.BOTH;
        gbc_areaDatos.gridx = 1;
        gbc_areaDatos.gridy = 2;
        getContentPane().add(areaDatos, gbc_areaDatos);
        
        JLabel lblTiposOL = new JLabel("Tipos de publicación de ofertas laborales:");
        lblTiposOL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblTiposOL = new GridBagConstraints();
        gbc_lblTiposOL.anchor = GridBagConstraints.EAST;
        gbc_lblTiposOL.insets = new Insets(0, 0, 5, 5);
        gbc_lblTiposOL.gridx = 0;
        gbc_lblTiposOL.gridy = 3;
        getContentPane().add(lblTiposOL, gbc_lblTiposOL);
        
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.gridwidth = 2;
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 1;
        gbc_list.gridy = 3;
        getContentPane().add(list, gbc_list);
        
        JLabel lblNewLabel = new JLabel("Datos tipo:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 4;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JTextArea areaDatosTipo = new JTextArea();
        areaDatosTipo.setEditable(false);
        GridBagConstraints gbc_areaDatosTipo = new GridBagConstraints();
        gbc_areaDatosTipo.gridwidth = 2;
        gbc_areaDatosTipo.insets = new Insets(0, 0, 5, 5);
        gbc_areaDatosTipo.fill = GridBagConstraints.BOTH;
        gbc_areaDatosTipo.gridx = 1;
        gbc_areaDatosTipo.gridy = 4;
        getContentPane().add(areaDatosTipo, gbc_areaDatosTipo);
        
        list.addListSelectionListener(new ListSelectionListener() {
        	@Override
        	public void valueChanged(ListSelectionEvent e) {
        		if(!e.getValueIsAdjusting()) {
        			JList<PaqueteTipo> source = (JList<PaqueteTipo>) e.getSource();
                    PaqueteTipo selectedItem = source.getSelectedValue();
                    
                    if (selectedItem != null) {
                    	Tipo tipoAsociado = selectedItem.getTipo();
                    	String datosTipo = "Nombre: " + tipoAsociado.getNombre() + "\nDescripcion: " + tipoAsociado.getDescripcion() + "\nExposicion:" + tipoAsociado.getExposicion() + "\nDuracion: " + tipoAsociado.getDuracion() + "\nCosto" + tipoAsociado.getCosto() + "\n Fecha de alta: " + "aca va la fecha";
                    	areaDatosTipo.setText(datosTipo);
                    }
        		}
        	}
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 1;
        gbc_btnCancelar.gridy = 5;
        getContentPane().add(btnCancelar, gbc_btnCancelar);

	}
	
	class ComboBoxItem {
		private String key;
		private Paquete instancia;
		
		public ComboBoxItem(String key, Paquete instancia) {
			this.key = key;
			this.instancia = instancia;
		}
		
		public String getKey() {
			return key;
		}
		
		public Paquete getPaquete() {
			return instancia;
		}
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	class ComboBoxModel extends DefaultComboBoxModel<ComboBoxItem> {
		public ComboBoxModel(Map<String, Paquete> map) {
			for (Map.Entry<String, Paquete> entry : map.entrySet()) {
				String key = entry.getKey();
				Paquete paquete = entry.getValue();
				ComboBoxItem item = new ComboBoxItem(key, paquete);
				addElement(item);
			}
		}
	}
}
