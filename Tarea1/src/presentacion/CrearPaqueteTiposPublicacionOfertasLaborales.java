package presentacion;

import javax.swing.JInternalFrame;

import excepciones.PaqueteRepetidoException;

import logica.IOfertaLaboral;
import logica.DTPaquete;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class CrearPaqueteTiposPublicacionOfertasLaborales extends JInternalFrame {
	
	private IOfertaLaboral col;
	
	private JTextField textFieldNombre;
	private JTextField textFieldPeriodoValidez;
	private JTextField textFieldDescuento;
	private JTextField textFieldCostoAsociado;
	private JTextArea textAreaDescripcion;
	
	public CrearPaqueteTiposPublicacionOfertasLaborales(IOfertaLaboral IOL) {
		
		col = IOL;
		
		setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear Paquete de Tipo de Publicacion de Oferta Laborale");
        setBounds(10, 40, 465, 291);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{100, 150, 150};
        gridBagLayout.rowHeights = new int[]{0, 0, 100, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[]{0, 0, 1.0, 0, 0, 0, 0.0, Double.MIN_VALUE};
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
        
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción: ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 40);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        getContentPane().add(scrollPane, gbc_scrollPane);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setWrapStyleWord(true); // Ajuste de palabras
        textAreaDescripcion.setLineWrap(true); // Ajuste de líneas
        textAreaDescripcion.setEditable(true);
        scrollPane.setViewportView(textAreaDescripcion);
        
        JLabel lblNewLabel_3 = new JLabel("Periódo de validez:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 5, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 3;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        textFieldPeriodoValidez = new JTextField();
        GridBagConstraints gbc_textFieldPeriodoValidez = new GridBagConstraints();
        gbc_textFieldPeriodoValidez.gridwidth = 2;
        gbc_textFieldPeriodoValidez.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldPeriodoValidez.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldPeriodoValidez.gridx = 1;
        gbc_textFieldPeriodoValidez.gridy = 3;
        getContentPane().add(textFieldPeriodoValidez, gbc_textFieldPeriodoValidez);
        textFieldPeriodoValidez.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Descuento:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 4;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        textFieldDescuento = new JTextField();
        GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
        gbc_textFieldDescuento.gridwidth = 2;
        gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldDescuento.gridx = 1;
        gbc_textFieldDescuento.gridy = 4;
        getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);
        textFieldDescuento.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Costo asociado: ");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 5;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        textFieldCostoAsociado = new JTextField();
        textFieldCostoAsociado.setColumns(10);
        GridBagConstraints gbc_textFieldCostoAsociado = new GridBagConstraints();
        gbc_textFieldCostoAsociado.gridwidth = 2;
        gbc_textFieldCostoAsociado.insets = new Insets(0, 0, 5, 40);
        gbc_textFieldCostoAsociado.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCostoAsociado.gridx = 1;
        gbc_textFieldCostoAsociado.gridy = 5;
        getContentPane().add(textFieldCostoAsociado, gbc_textFieldCostoAsociado);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormulario();
        		setVisible(false);
        	}
        });
        
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.anchor = GridBagConstraints.WEST;
        gbc_btnCancelar.insets = new Insets(0, 0, 10, 20);
        gbc_btnCancelar.gridx = 1;
        gbc_btnCancelar.gridy = 6;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cmdCrearPaqTipPubOL(arg0);
        	}
        });
        
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.insets = new Insets(0, 0, 10, 30);
        gbc_btnAceptar.anchor = GridBagConstraints.WEST;
        gbc_btnAceptar.gridx = 2;
        gbc_btnAceptar.gridy = 6;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
	}
	
	private void cmdCrearPaqTipPubOL(ActionEvent arg0) {
		String nombreU = textFieldNombre.getText();
		String descripcionU = textAreaDescripcion.getText();
		String periodoValidezU = textFieldPeriodoValidez.getText();
		String descuentoU = textFieldDescuento.getText();
		String costoAsociadoU = textFieldCostoAsociado.getText();
		
		if(checkForm()) {
			DTPaquete datosPaquete = new DTPaquete(nombreU, descripcionU, Integer.parseInt(periodoValidezU), Float.parseFloat(descuentoU), 
																	Float.parseFloat(costoAsociadoU), null);
			
			try {
				col.ingresarDatosPaquete(datosPaquete);
				
				JOptionPane.showMessageDialog(this, "El Paquete se ha creado con éxito ", "Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
                        JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();
				setVisible(false);
			} catch (PaqueteRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
                        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean checkForm() {
		String nombreU = textFieldNombre.getText();
		String descripcionU = textAreaDescripcion.getText();
		String periodoValidezU = textFieldPeriodoValidez.getText();
		String descuentoU = textFieldDescuento.getText();
		String costoAsociadoU = textFieldCostoAsociado.getText();
		
		if (nombreU.isEmpty() || descripcionU.isEmpty() || periodoValidezU.isEmpty() || 
				descuentoU.isEmpty() || costoAsociadoU.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
						JOptionPane.ERROR_MESSAGE);
				return false;
		}
		
		if(!this.validar(nombreU)) {
			JOptionPane.showMessageDialog(this, "El Nombre debe ser solo letras", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(periodoValidezU);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El Periodo de Validez debe ser un numero entero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Integer.parseInt(periodoValidezU) <= 0) {
			JOptionPane.showMessageDialog(this, "El Periodo de Validez debe ser mayor a cero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Float.parseFloat(descuentoU);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El Descuento debe ser un numero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(Float.parseFloat(descuentoU) <= 0) {
			JOptionPane.showMessageDialog(this, "El Descuento debe ser mayor que cero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(Float.parseFloat(descuentoU) > 100) {
			JOptionPane.showMessageDialog(this, "El Descuento debe ser menor que 100", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Float.parseFloat(costoAsociadoU);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El Costo Asociado debe ser un numero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(Float.parseFloat(costoAsociadoU) <= 0) {
			JOptionPane.showMessageDialog(this, "El Costo Asociado debe ser mayor a cero", 
					"Crear Paquetes de Tipos de Publicacion de Ofertas Laborales",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
	
	private boolean validar(String s) {
        // Expresión regular que permite letras, espacios, la letra 'ñ' y caracteres acentuados
        String regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldPeriodoValidez.setText("");
		textFieldCostoAsociado.setText("");
		textFieldDescuento.setText("");
	}
}
