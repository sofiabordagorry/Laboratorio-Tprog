package presentacion;

import static org.junit.jupiter.api.Assertions.fail;

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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logica.DTPaquete;
import logica.DTPaqueteTipo;
import logica.DTTipo;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.ManejadorTipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.Tipo;

import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import excepciones.NoExistenPaquetesException;
import excepciones.NoHayPaquetesException;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConsultaPaqueteDeTiposDeOfertaLaboral extends JInternalFrame {

	private IOfertaLaboral col;
	private JComboBox<ComboBoxItem> comboBox;
	private JList<DTPaqueteTipo> list;
	private JTextArea descripcionText;

	/**
	 * Create the frame.
	 * @throws NoExistenPaquetesException 
	 */
	public ConsultaPaqueteDeTiposDeOfertaLaboral(IOfertaLaboral iOL) {
    	
		// Interfaz Oferta Laboral
		col = iOL;
		
    	// ManejadorTipo
    	ManejadorTipo m = ManejadorTipo.getInstancia();
    	
        setBounds(10, 40, 780, 524);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);    
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{50, 207, 99};
        gridBagLayout.rowHeights = new int[]{36, 0, 29, 39, 28, 29, 28, 27, 29, 29, 30, 0, 29, 25, 27, 28, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0, 0.0, Double.MIN_VALUE};
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
         
        comboBox = new JComboBox<ComboBoxItem>();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 2;
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 1;
        getContentPane().add(comboBox, gbc_comboBox);
        list = new JList<>();
        
        JLabel NombrePaq = new JLabel("Nombre: ");
        GridBagConstraints gbc_NombrePaq = new GridBagConstraints();
        gbc_NombrePaq.anchor = GridBagConstraints.EAST;
        gbc_NombrePaq.insets = new Insets(0, 0, 5, 5);
        gbc_NombrePaq.gridx = 0;
        gbc_NombrePaq.gridy = 2;
        getContentPane().add(NombrePaq, gbc_NombrePaq);
        
        JLabel nombrePaqText = new JLabel("");
        GridBagConstraints gbc_nombrePaqText = new GridBagConstraints();
        gbc_nombrePaqText.anchor = GridBagConstraints.WEST;
        gbc_nombrePaqText.insets = new Insets(0, 0, 5, 5);
        gbc_nombrePaqText.gridx = 1;
        gbc_nombrePaqText.gridy = 2;
        getContentPane().add(nombrePaqText, gbc_nombrePaqText);
        
        JLabel descripcionPaq = new JLabel("Descripcion: ");
        GridBagConstraints gbc_descripcionPaq = new GridBagConstraints();
        gbc_descripcionPaq.anchor = GridBagConstraints.EAST;
        gbc_descripcionPaq.insets = new Insets(0, 0, 5, 5);
        gbc_descripcionPaq.gridx = 0;
        gbc_descripcionPaq.gridy = 3;
        getContentPane().add(descripcionPaq, gbc_descripcionPaq);
        
        JTextArea descripcionPaqText = new JTextArea();
        GridBagConstraints gbc_descripcionPaqText = new GridBagConstraints();
        gbc_descripcionPaqText.insets = new Insets(0, 0, 5, 5);
        gbc_descripcionPaqText.fill = GridBagConstraints.BOTH;
        gbc_descripcionPaqText.gridx = 1;
        gbc_descripcionPaqText.gridy = 3;
        descripcionPaqText.setEditable(false);
        descripcionPaqText.setWrapStyleWord(true); 
        descripcionPaqText.setLineWrap(true);
        getContentPane().add(descripcionPaqText, gbc_descripcionPaqText);
        
        JLabel periodoDeValidez = new JLabel("Periodo de validez: ");
        GridBagConstraints gbc_periodoDeValidez = new GridBagConstraints();
        gbc_periodoDeValidez.anchor = GridBagConstraints.EAST;
        gbc_periodoDeValidez.insets = new Insets(0, 0, 5, 5);
        gbc_periodoDeValidez.gridx = 0;
        gbc_periodoDeValidez.gridy = 4;
        getContentPane().add(periodoDeValidez, gbc_periodoDeValidez);
        
        JLabel periodoDeValidezText = new JLabel("");
        GridBagConstraints gbc_periodoDeValidezText = new GridBagConstraints();
        gbc_periodoDeValidezText.anchor = GridBagConstraints.WEST;
        gbc_periodoDeValidezText.insets = new Insets(0, 0, 5, 5);
        gbc_periodoDeValidezText.gridx = 1;
        gbc_periodoDeValidezText.gridy = 4;
        getContentPane().add(periodoDeValidezText, gbc_periodoDeValidezText);
        
        JLabel descuentoPaq = new JLabel("Descuento: ");
        GridBagConstraints gbc_descuentoPaq = new GridBagConstraints();
        gbc_descuentoPaq.anchor = GridBagConstraints.EAST;
        gbc_descuentoPaq.insets = new Insets(0, 0, 5, 5);
        gbc_descuentoPaq.gridx = 0;
        gbc_descuentoPaq.gridy = 5;
        getContentPane().add(descuentoPaq, gbc_descuentoPaq);
        
        JLabel descuentoPaqText = new JLabel("");
        GridBagConstraints gbc_descuentoPaqText = new GridBagConstraints();
        gbc_descuentoPaqText.anchor = GridBagConstraints.WEST;
        gbc_descuentoPaqText.insets = new Insets(0, 0, 5, 5);
        gbc_descuentoPaqText.gridx = 1;
        gbc_descuentoPaqText.gridy = 5;
        getContentPane().add(descuentoPaqText, gbc_descuentoPaqText);
        
        JLabel costoAsociadoPaq = new JLabel("Costo asociado: ");
        GridBagConstraints gbc_costoAsociadoPaq = new GridBagConstraints();
        gbc_costoAsociadoPaq.anchor = GridBagConstraints.EAST;
        gbc_costoAsociadoPaq.insets = new Insets(0, 0, 5, 5);
        gbc_costoAsociadoPaq.gridx = 0;
        gbc_costoAsociadoPaq.gridy = 6;
        getContentPane().add(costoAsociadoPaq, gbc_costoAsociadoPaq);
        
        JLabel costoAsociadoText = new JLabel("");
        GridBagConstraints gbc_costoAsociadoText = new GridBagConstraints();
        gbc_costoAsociadoText.anchor = GridBagConstraints.WEST;
        gbc_costoAsociadoText.insets = new Insets(0, 0, 5, 5);
        gbc_costoAsociadoText.gridx = 1;
        gbc_costoAsociadoText.gridy = 6;
        getContentPane().add(costoAsociadoText, gbc_costoAsociadoText);
        
        JLabel fechaDeAltaPaq = new JLabel("Fecha de alta: ");
        GridBagConstraints gbc_fechaDeAltaPaq = new GridBagConstraints();
        gbc_fechaDeAltaPaq.anchor = GridBagConstraints.EAST;
        gbc_fechaDeAltaPaq.insets = new Insets(0, 0, 5, 5);
        gbc_fechaDeAltaPaq.gridx = 0;
        gbc_fechaDeAltaPaq.gridy = 7;
        getContentPane().add(fechaDeAltaPaq, gbc_fechaDeAltaPaq);
        
        JLabel fechaDeAltaText = new JLabel("");
        GridBagConstraints gbc_fechaDeAltaText = new GridBagConstraints();
        gbc_fechaDeAltaText.anchor = GridBagConstraints.WEST;
        gbc_fechaDeAltaText.insets = new Insets(0, 0, 5, 5);
        gbc_fechaDeAltaText.gridx = 1;
        gbc_fechaDeAltaText.gridy = 7;
        getContentPane().add(fechaDeAltaText, gbc_fechaDeAltaText);
        
        comboBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JComboBox<ComboBoxItem> source = (JComboBox<ComboBoxItem>) e.getSource();
        		ComboBoxItem selectedItem = (ComboBoxItem)source.getSelectedItem();
        		
        		if (selectedItem != null) {
        			DTPaquete p = selectedItem.getPaquete();
        			// CAMBIAR DATOS
        			nombrePaqText.setText(p.getNombre());
        			descripcionPaqText.setText(p.getDescripcion());
        			periodoDeValidezText.setText(String.valueOf(p.getPeriodoDeValidez()) + " días");
        			descuentoPaqText.setText(String.valueOf(p.getDescuento()) + " %");
        			costoAsociadoText.setText("$" + String.valueOf(p.getCostoAsociado()));
        			fechaDeAltaText.setText(p.getFechaDeAlta().toString());
        			DTPaqueteTipo[] pqt = p.getPaqueteTipos();
        			list.setListData(pqt);
        		}
        	}
        });
        
        JLabel lblTiposOL = new JLabel("Tipos de publicación de ofertas laborales:");
        lblTiposOL.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblTiposOL = new GridBagConstraints();
        gbc_lblTiposOL.anchor = GridBagConstraints.EAST;
        gbc_lblTiposOL.insets = new Insets(0, 0, 5, 5);
        gbc_lblTiposOL.gridx = 0;
        gbc_lblTiposOL.gridy = 8;
        getContentPane().add(lblTiposOL, gbc_lblTiposOL);
        
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.gridwidth = 2;
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 1;
        gbc_list.gridy = 8;
        getContentPane().add(list, gbc_list);
        
        JLabel lblNombre = new JLabel("Nombre: ");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 10;
        getContentPane().add(lblNombre, gbc_lblNombre);
        
        JLabel lblNameText = new JLabel("");
        GridBagConstraints gbc_lblNameText = new GridBagConstraints();
        gbc_lblNameText.anchor = GridBagConstraints.WEST;
        gbc_lblNameText.gridwidth = 2;
        gbc_lblNameText.insets = new Insets(0, 0, 5, 0);
        gbc_lblNameText.gridx = 1;
        gbc_lblNameText.gridy = 10;
        getContentPane().add(lblNameText, gbc_lblNameText);
        
        JLabel lblDescripcion = new JLabel("Descripcion: ");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 11;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);
        
        descripcionText = new JTextArea();
        GridBagConstraints gbc_descripcionText = new GridBagConstraints();
        gbc_descripcionText.gridwidth = 2;
        gbc_descripcionText.insets = new Insets(0, 0, 5, 0);
        gbc_descripcionText.fill = GridBagConstraints.HORIZONTAL;
        gbc_descripcionText.gridx = 1;
        gbc_descripcionText.gridy = 11;
        getContentPane().add(descripcionText, gbc_descripcionText);
        descripcionText.setWrapStyleWord(true); 
        descripcionText.setLineWrap(true);
        descripcionText.setEditable(false);
        descripcionText.setColumns(10);
        
        JLabel lblExposicion = new JLabel("Exposicion: ");
        GridBagConstraints gbc_lblExposicion = new GridBagConstraints();
        gbc_lblExposicion.anchor = GridBagConstraints.EAST;
        gbc_lblExposicion.insets = new Insets(0, 0, 5, 5);
        gbc_lblExposicion.gridx = 0;
        gbc_lblExposicion.gridy = 12;
        getContentPane().add(lblExposicion, gbc_lblExposicion);
        
        JLabel exposicionText = new JLabel("");
        GridBagConstraints gbc_exposicionText = new GridBagConstraints();
        gbc_exposicionText.anchor = GridBagConstraints.WEST;
        gbc_exposicionText.gridwidth = 2;
        gbc_exposicionText.insets = new Insets(0, 0, 5, 0);
        gbc_exposicionText.gridx = 1;
        gbc_exposicionText.gridy = 12;
        getContentPane().add(exposicionText, gbc_exposicionText);
        
        JLabel lblDuracion = new JLabel("Duracion: ");
        GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
        gbc_lblDuracion.anchor = GridBagConstraints.EAST;
        gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDuracion.gridx = 0;
        gbc_lblDuracion.gridy = 13;
        getContentPane().add(lblDuracion, gbc_lblDuracion);
        
        JLabel duracionText = new JLabel("");
        GridBagConstraints gbc_duracionText = new GridBagConstraints();
        gbc_duracionText.anchor = GridBagConstraints.WEST;
        gbc_duracionText.gridwidth = 2;
        gbc_duracionText.insets = new Insets(0, 0, 5, 0);
        gbc_duracionText.gridx = 1;
        gbc_duracionText.gridy = 13;
        getContentPane().add(duracionText, gbc_duracionText);
        
        JLabel lblFecha = new JLabel("Fecha de alta: ");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.anchor = GridBagConstraints.EAST;
        gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
        gbc_lblFecha.gridx = 0;
        gbc_lblFecha.gridy = 14;
        getContentPane().add(lblFecha, gbc_lblFecha);
        
        JLabel fechaText = new JLabel("");
        GridBagConstraints gbc_fechaText = new GridBagConstraints();
        gbc_fechaText.anchor = GridBagConstraints.WEST;
        gbc_fechaText.gridwidth = 2;
        gbc_fechaText.insets = new Insets(0, 0, 5, 0);
        gbc_fechaText.gridx = 1;
        gbc_fechaText.gridy = 14;
        getContentPane().add(fechaText, gbc_fechaText);
        
        JLabel lblCosto = new JLabel("Costo: ");
        GridBagConstraints gbc_lblCosto = new GridBagConstraints();
        gbc_lblCosto.anchor = GridBagConstraints.EAST;
        gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
        gbc_lblCosto.gridx = 0;
        gbc_lblCosto.gridy = 15;
        getContentPane().add(lblCosto, gbc_lblCosto);
        
        JLabel costoText = new JLabel("");
        GridBagConstraints gbc_costoText = new GridBagConstraints();
        gbc_costoText.anchor = GridBagConstraints.WEST;
        gbc_costoText.gridwidth = 2;
        gbc_costoText.insets = new Insets(0, 0, 5, 0);
        gbc_costoText.gridx = 1;
        gbc_costoText.gridy = 15;
        getContentPane().add(costoText, gbc_costoText);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			nombrePaqText.setText("");
    			descripcionPaqText.setText("");
    			periodoDeValidezText.setText("");
    			descuentoPaqText.setText("");
    			costoAsociadoText.setText("");
    			fechaDeAltaText.setText("");
            	lblNameText.setText("");
            	descripcionText.setText("");
            	exposicionText.setText("");
            	duracionText.setText("");
            	fechaText.setText("");
            	costoText.setText("");
            	comboBox.setSelectedIndex(-1);
            	DTPaqueteTipo[] pqt = new DTPaqueteTipo[0];
            	list.setListData(pqt);
            	setVisible(false);
            }
        });
        
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 1;
        gbc_btnCancelar.gridy = 16;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        list.addListSelectionListener(new ListSelectionListener() {
        	@Override
        	public void valueChanged(ListSelectionEvent e) {
        		if(!e.getValueIsAdjusting()) {
        			JList<DTPaqueteTipo> source = (JList<DTPaqueteTipo>) e.getSource();
                    DTPaqueteTipo selectedItem = source.getSelectedValue();
                    
                    if (selectedItem != null) {
                    	DTTipo tipoAsociado = selectedItem.getTipo();
                    	lblNameText.setText(tipoAsociado.getNombre());
                    	descripcionText.setText(tipoAsociado.getDescripcion());
                    	exposicionText.setText(String.valueOf(tipoAsociado.getExposicion()));
                    	duracionText.setText(String.valueOf(tipoAsociado.getDuracion()) + " días");
                    	fechaText.setText(tipoAsociado.getFechaDeAlta().toString());
                    	costoText.setText("$" + String.valueOf(tipoAsociado.getCosto()));
                    }
        		}
        	}
        });

	}
	
	class ComboBoxItem {
		private DTPaquete instancia;
		
		public ComboBoxItem(DTPaquete instancia) {
			this.instancia = instancia;
		}
		
		public DTPaquete getPaquete() {
			return instancia;
		}
		
		@Override
		public String toString() {
			return instancia.getNombre();
		}
	}
	
	class ComboBoxModel extends DefaultComboBoxModel<ComboBoxItem> {
		public ComboBoxModel(DTPaquete[] paqlist) {
			if (paqlist != null) {
				for (DTPaquete paquete : paqlist) {
					ComboBoxItem item = new ComboBoxItem(paquete);
					addElement(item);
				}
			}
		}
	}
	
	public boolean updateComboBox() {
		try {
			DTPaquete[] listaPaq = col.listarPaquetes();
			ComboBoxModel model = new ComboBoxModel(listaPaq);
			comboBox.setModel(model);
            return true;
		} catch (NoHayPaquetesException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Paquetes", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
}
