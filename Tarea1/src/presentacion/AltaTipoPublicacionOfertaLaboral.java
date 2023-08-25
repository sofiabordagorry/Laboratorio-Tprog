package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.time.format.DateTimeFormatter;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.util.Date;

import java.awt.Insets;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class AltaTipoPublicacionOfertaLaboral extends JInternalFrame {
	
	private JTextField textFieldNombre;
	private JTextField textFieldExposicion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextArea textAreaDescripcion;
	private JDateChooser dateChooser;
	
	
	public AltaTipoPublicacionOfertaLaboral() {
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		 setResizable(false);
	     setIconifiable(true);
	     setMaximizable(false);
	     setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	     setClosable(true);
	     setTitle("Alta de Tipo de Publicacion de Oferta Laboral");
	     setBounds(30, 30, 484, 379);
	     GridBagLayout gridBagLayout = new GridBagLayout();
	     gridBagLayout.columnWidths = new int[]{100, 150, 150, 5, 5};
	     gridBagLayout.rowHeights = new int[]{30, 30, 30, 100, 30, 30, 30, 25, 30, 30};
	     gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0,Double.MIN_VALUE };
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
	     
	     textFieldNombre = new JTextField();
	     GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
	     gbc_textFieldNombre.gridwidth = 2;
	     gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textFieldNombre.gridx = 1;
	     gbc_textFieldNombre.gridy = 2;
	     getContentPane().add(textFieldNombre, gbc_textFieldNombre);
	     textFieldNombre.setColumns(10);
	     
	     JLabel lblNewLabel_1 = new JLabel("Descripción:");
	     lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	     gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_1.gridx = 0;
	     gbc_lblNewLabel_1.gridy = 3;
	     getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	     
	     JScrollPane scrollPane = new JScrollPane();
	     GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	     gbc_scrollPane.gridwidth = 2;
	     gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
	     gbc_scrollPane.fill = GridBagConstraints.BOTH;
	     gbc_scrollPane.gridx = 1;
	     gbc_scrollPane.gridy = 3;
	     getContentPane().add(scrollPane, gbc_scrollPane);
	     
	     textAreaDescripcion = new JTextArea();
	     scrollPane.setViewportView(textAreaDescripcion);
	     textAreaDescripcion. setWrapStyleWord(true); // Ajuste de palabras
	     textAreaDescripcion.setLineWrap(true); // Ajuste de líneas
	     textAreaDescripcion.setEditable(true);
	     
	     JLabel lblNewLabel_2 = new JLabel("Exposición:");
	     lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
	     gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_2.gridx = 0;
	     gbc_lblNewLabel_2.gridy = 4;
	     getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
	     
	     textFieldExposicion = new JTextField();
	     GridBagConstraints gbc_textFieldExposicion = new GridBagConstraints();
	     gbc_textFieldExposicion.gridwidth = 2;
	     gbc_textFieldExposicion.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldExposicion.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textFieldExposicion.gridx = 1;
	     gbc_textFieldExposicion.gridy = 4;
	     getContentPane().add(textFieldExposicion, gbc_textFieldExposicion);
	     textFieldExposicion.setColumns(10);
	     
	     JLabel lblNewLabel_3 = new JLabel("Duración:");
	     lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
	     gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_3.gridx = 0;
	     gbc_lblNewLabel_3.gridy = 5;
	     getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
	     
	     textFieldDuracion = new JTextField();
	     GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
	     gbc_textFieldDuracion.gridwidth = 2;
	     gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textFieldDuracion.gridx = 1;
	     gbc_textFieldDuracion.gridy = 5;
	     getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);
	     textFieldDuracion.setColumns(10);
	     
	     JLabel lblNewLabel_5 = new JLabel("Costo:");
	     lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	     gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_5.gridx = 0;
	     gbc_lblNewLabel_5.gridy = 6;
	     getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
	     
	     textFieldCosto = new JTextField();
	     GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
	     gbc_textFieldCosto.gridwidth = 2;
	     gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
	     gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
	     gbc_textFieldCosto.gridx = 1;
	     gbc_textFieldCosto.gridy = 6;
	     getContentPane().add(textFieldCosto, gbc_textFieldCosto);
	     textFieldCosto.setColumns(10);
	     
	     JButton btnCancelar = new JButton("Cancelar");
	     btnCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
	     btnCancelar.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		limpiarFormulario();
	     		setVisible(false);
	     	}
	     });
	     
	     dateChooser = new JDateChooser();
	     dateChooser.getCalendarButton().addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     	}
	     });
	     
	     JLabel lblNewLabel_6 = new JLabel("Fecha de Alta:");
	     lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
	     gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
	     gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
	     gbc_lblNewLabel_6.gridx = 0;
	     gbc_lblNewLabel_6.gridy = 7;
	     getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
	     GridBagConstraints gbc_dateChooser = new GridBagConstraints();
	     gbc_dateChooser.gridwidth = 2;
	     gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
	     gbc_dateChooser.fill = GridBagConstraints.BOTH;
	     gbc_dateChooser.gridx = 1;
	     gbc_dateChooser.gridy = 7;
	     getContentPane().add(dateChooser, gbc_dateChooser);
	     GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
	     gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
	     gbc_btnCancelar.gridx = 1;
	     gbc_btnCancelar.gridy = 8;
	     getContentPane().add(btnCancelar, gbc_btnCancelar);
	     
	     JButton btnAceptar = new JButton("Aceptar");
	     btnAceptar.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		cmdAltaTipoPublicacionOfertaLaboral(arg0);
	     	}
	     });
	     btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
	     GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
	     gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
	     gbc_btnAceptar.gridx = 2;
	     gbc_btnAceptar.gridy = 8;
	     getContentPane().add(btnAceptar, gbc_btnAceptar);
	}
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldExposicion.setText("");
		textFieldCosto.setText("");
		textFieldDuracion.setText("");
	}
	
	protected void cmdAltaTipoPublicacionOfertaLaboral(ActionEvent arg0) {
		String nombreT = this.textFieldNombre.getText();
		String descripcionT = textAreaDescripcion.getText();
		String exposicionT = this.textFieldExposicion.getText();
		String costoT = this.textFieldCosto.getText();
		String duracionT = this.textFieldDuracion.getText();
		
		if(checkFormulario()) {
			//try {
               // controlTipo.registrarUsuario(nombreU, apellidoU, ciU);

                // Muestro éxito de la operación
                //JOptionPane.showMessageDialog(this, "El Tipo de Publicacion se ha agregado con éxito", "Alta de Tipo de Publicacion de Oferta Laboral",
                //        JOptionPane.INFORMATION_MESSAGE);

          //  } catch (TipoYaAgragadoException e) {
                // Muestro error de registro
           //     JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Tipo de Publicacion de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
          //  }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
		}
	}
	
	private boolean checkFormulario() {
		String nombreT = this.textFieldNombre.getText();
		String descripcionT = textAreaDescripcion.getText();
		String exposicionT = this.textFieldExposicion.getText();
		String duracionT = this.textFieldDuracion.getText();
		String costoT = this.textFieldCosto.getText();
		Date fechaAlta = dateChooser.getDate();
		Date fechaHoy = new Date();
		long milisegundosDesdeEnero1900 = -2208988800000L; // Milisegundos desde la época para el 1 de enero de 1900
		long milisegundosDesdeEnero2000 = 946684800000L;// Milisegundos desde la época para el 1 de enero de 2000
        Date fechaAntigua = new Date(milisegundosDesdeEnero2000);
		
		if (nombreT.isEmpty() || descripcionT.isEmpty() || exposicionT.isEmpty() || costoT.isEmpty()  || duracionT.isEmpty() || fechaAlta == null) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		
		if(!validar(nombreT)) {
			JOptionPane.showMessageDialog(this, "El Nombre solo puede tener letras", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
        try {
            Integer.parseInt(exposicionT);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La Exposicion debe ser un numero", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        try {
        	Integer.parseInt(duracionT);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La Duracion debe ser un numero", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        
        if(Integer.parseInt(duracionT) <=0){
            JOptionPane.showMessageDialog(this, "La Duracion debe ser mayor a 0", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        try {
        	Float.parseFloat(costoT);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El Costo debe ser un numero, puede llevar punto (.)", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        
        //////COSTO = 0?????????????????
        
        if(Float.parseFloat(costoT) <=0){
            JOptionPane.showMessageDialog(this, "El Costo debe ser mayor a 0", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
            }
        
		if(fechaAlta.compareTo(fechaHoy) > 0){
			JOptionPane.showMessageDialog(this, "La Fecha de Alta no puede ser posterior a la fecha actual", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if(fechaAlta.compareTo(fechaAntigua) < 0){
			JOptionPane.showMessageDialog(this, "La Fecha de Alta debe ser posterior al 1ro de enero de 2000", "Alta de Tipo de Publicacion de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
        
        return true;
}
	
	private boolean validar(String s) {
        // Expresión regular que permite letras, espacios, la letra 'ñ' y caracteres acentuados
        String regex = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }


	
	
	
}
