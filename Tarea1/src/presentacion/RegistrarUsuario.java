package presentacion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.ZoneId;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import logica.DTEmpresa;
import logica.DTPostulante;
import logica.Factory;
import logica.IUsuario;

@SuppressWarnings("serial")
public class RegistrarUsuario extends JInternalFrame {

    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldMail;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseApellido;
    private JLabel lblIngreseMail;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JRadioButton postulanteButton;
    private JRadioButton empresaButton;
    private JLabel lblNewLabel_1;
    private ButtonGroup buttonGroup;
    private JLabel labelFechaNac;
    private JLabel labelNac;
    private JTextField textFieldNacionalidad;
    private JLabel ingreseDatos;
    private JTextField textField;
    private JLabel tipoUsuario;
    private JLabel descripcionLabel;
    private JLabel linkLabel;
    private JTextField textField_2;
    private JDateChooser dateChooser;
    private JScrollPane textScrollPane;
    private JTextPane textPane;
    private IUsuario iUsuario;
    private JLabel lblNombreEmpresa;
    private JTextField textFieldNombreEmpresa;
    
    private boolean esPostulante;

    /**
     * Create the frame.
     */
    public RegistrarUsuario() {
        // Se inicializa con la interfaz de usuarios
    	Factory fact = Factory.getInstance();
    	iUsuario = fact.getIUsuario();
    	
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 10, 422, 400);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 0, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
        
        esPostulante = true;
        
        buttonGroup = new ButtonGroup();
                                
                        ingreseDatos = new JLabel("Ingrese los siguientes datos:");
                        ingreseDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
                        GridBagConstraints gbc_ingreseDatos = new GridBagConstraints();
                        gbc_ingreseDatos.gridwidth = 4;
                        gbc_ingreseDatos.insets = new Insets(0, 0, 5, 0);
                        gbc_ingreseDatos.gridx = 0;
                        gbc_ingreseDatos.gridy = 0;
                        getContentPane().add(ingreseDatos, gbc_ingreseDatos);
                        
                        tipoUsuario = new JLabel("Tipo de Usuario:");
                        tipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
                        gbc_tipoUsuario.anchor = GridBagConstraints.EAST;
                        gbc_tipoUsuario.insets = new Insets(0, 0, 5, 5);
                        gbc_tipoUsuario.gridx = 0;
                        gbc_tipoUsuario.gridy = 1;
                        getContentPane().add(tipoUsuario, gbc_tipoUsuario);
                        
                        postulanteButton = new JRadioButton("Postulante");
                        GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
                        gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
                        gbc_rdbtnNewRadioButton.gridx = 1;
                        gbc_rdbtnNewRadioButton.gridy = 1;
                        getContentPane().add(postulanteButton, gbc_rdbtnNewRadioButton);
                        buttonGroup.add(postulanteButton);
                        postulanteButton.setSelected(true);
                        
                        ItemListener PostItemListener = new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								// TODO Auto-generated method stub
								int state = e.getStateChange();
								if (state == ItemEvent.SELECTED) {
								    labelFechaNac.setVisible(true);
			                        dateChooser.setVisible(true);
								    labelNac.setVisible(true);
								    textFieldNacionalidad.setVisible(true);
								    esPostulante = true;
								} else {
								    labelFechaNac.setVisible(false);
			                        dateChooser.setVisible(false);
								    labelNac.setVisible(false);
								    textFieldNacionalidad.setVisible(false);
								    esPostulante = false;
								}
							}
                        };
                     
                        
                        postulanteButton.addItemListener(PostItemListener);
                        
                        empresaButton = new JRadioButton("Empresa");
                        GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
                        gbc_rdbtnNewRadioButton_1.gridwidth = 2;
                        gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
                        gbc_rdbtnNewRadioButton_1.gridx = 2;
                        gbc_rdbtnNewRadioButton_1.gridy = 1;
                        getContentPane().add(empresaButton, gbc_rdbtnNewRadioButton_1);
                        buttonGroup.add(empresaButton);
                        
                        ItemListener EmpItemListener = new ItemListener() {
                        	@Override
                        	public void itemStateChanged(ItemEvent e) {
                        		int state = e.getStateChange();
                        		if (state == ItemEvent.SELECTED) {
                        		    descripcionLabel.setVisible(true);
                        		    linkLabel.setVisible(true);
                        		    textField_2.setVisible(true);
                        		    textPane.setVisible(true);
                                    textScrollPane.setVisible(true);
                                    lblNombreEmpresa.setVisible(true);
                                    textFieldNombreEmpresa.setVisible(true);
                        		    esPostulante = false;
                        		} else {
                        		    descripcionLabel.setVisible(false);
                        		    linkLabel.setVisible(false);
                        		    textField_2.setVisible(false);
                        		    textPane.setVisible(false);
                                    textScrollPane.setVisible(false);
                                    lblNombreEmpresa.setVisible(false);
                                    textFieldNombreEmpresa.setVisible(false);
                        		    esPostulante = true;
                        		}
                        	}
                        };
                        
                        empresaButton.addItemListener(EmpItemListener);
                        
                        lblNewLabel_1 = new JLabel("Nickname:");
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
                        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
                        gbc_lblNewLabel_1.gridx = 0;
                        gbc_lblNewLabel_1.gridy = 2;
                        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

                       
                        textField = new JTextField();
                        GridBagConstraints gbc_textField = new GridBagConstraints();
                        gbc_textField.gridwidth = 3;
                        gbc_textField.insets = new Insets(0, 0, 5, 0);
                        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
                        gbc_textField.gridx = 1;
                        gbc_textField.gridy = 2;
                        getContentPane().add(textField, gbc_textField);
                        textField.setColumns(10);
                        

                        lblIngreseNombre = new JLabel("Nombre:");
                        lblIngreseNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
                        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
                        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
                        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
                        gbc_lblIngreseNombre.gridx = 0;
                        gbc_lblIngreseNombre.gridy = 3;
                        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);
                        

                        textFieldNombre = new JTextField();
                        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
                        gbc_textFieldNombre.gridwidth = 3;
                        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
                        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
                        gbc_textFieldNombre.gridx = 1;
                        gbc_textFieldNombre.gridy = 3;
                        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
                        textFieldNombre.setColumns(10);
               
                        
                        lblIngreseApellido = new JLabel("Apellido:");
                        lblIngreseApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
                        GridBagConstraints gbc_lblIngreseApellido = new GridBagConstraints();
                        gbc_lblIngreseApellido.fill = GridBagConstraints.BOTH;
                        gbc_lblIngreseApellido.insets = new Insets(0, 0, 5, 5);
                        gbc_lblIngreseApellido.gridx = 0;
                        gbc_lblIngreseApellido.gridy = 4;
                        getContentPane().add(lblIngreseApellido, gbc_lblIngreseApellido);
                

                        textFieldApellido = new JTextField();
                        GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
                        gbc_textFieldApellido.gridwidth = 3;
                        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
                        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
                        gbc_textFieldApellido.gridx = 1;
                        gbc_textFieldApellido.gridy = 4;
                        getContentPane().add(textFieldApellido, gbc_textFieldApellido);
                        textFieldApellido.setColumns(10);
                                
                
                        lblIngreseMail = new JLabel("E-mail:");
                        lblIngreseMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        lblIngreseMail.setHorizontalAlignment(SwingConstants.RIGHT);
                        GridBagConstraints gbc_lblIngreseMail = new GridBagConstraints();
                        gbc_lblIngreseMail.fill = GridBagConstraints.BOTH;
                        gbc_lblIngreseMail.insets = new Insets(0, 0, 5, 5);
                        gbc_lblIngreseMail.gridx = 0;
                        gbc_lblIngreseMail.gridy = 5;
                        getContentPane().add(lblIngreseMail, gbc_lblIngreseMail);
                

                        textFieldMail = new JTextField();
                        textFieldMail.setColumns(10);
                        GridBagConstraints gbc_textFieldMail = new GridBagConstraints();
                        gbc_textFieldMail.gridwidth = 3;
                        gbc_textFieldMail.fill = GridBagConstraints.BOTH;
                        gbc_textFieldMail.insets = new Insets(0, 0, 5, 0);
                        gbc_textFieldMail.gridx = 1;
                        gbc_textFieldMail.gridy = 5;
                        getContentPane().add(textFieldMail, gbc_textFieldMail);
            
                        
                        // POSTULANTE
                        
                        labelFechaNac = new JLabel("Fecha de Nacimiento");
                        labelFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
                        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
                        gbc_lblNewLabel.gridx = 0;
                        gbc_lblNewLabel.gridy = 6;
                        getContentPane().add(labelFechaNac, gbc_lblNewLabel);
                        
                        dateChooser = new JDateChooser();
                        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
                        gbc_dateChooser.gridwidth = 3;
                        gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
                        gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
                        gbc_dateChooser.gridx = 1;
                        gbc_dateChooser.gridy = 6;
                        getContentPane().add(dateChooser, gbc_dateChooser);
                        
                        labelNac = new JLabel("Nacionalidad");
                        labelNac.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        GridBagConstraints gbc_labelNac = new GridBagConstraints();
                        gbc_labelNac.anchor = GridBagConstraints.EAST;
                        gbc_labelNac.insets = new Insets(0, 0, 5, 5);
                        gbc_labelNac.gridx = 0;
                        gbc_labelNac.gridy = 7;
                        getContentPane().add(labelNac, gbc_labelNac);
                        
                        textFieldNacionalidad = new JTextField();
                        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
                        gbc_textField_1.gridwidth = 3;
                        gbc_textField_1.insets = new Insets(0, 0, 5, 0);
                        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
                        gbc_textField_1.gridx = 1;
                        gbc_textField_1.gridy = 7;
                        getContentPane().add(textFieldNacionalidad, gbc_textField_1);
                        textFieldNacionalidad.setColumns(10);
                
                        
                // EMPRESA
                        
                lblNombreEmpresa = new JLabel("Nombre de Empresa");
                lblNombreEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 12));
                GridBagConstraints gbc_lblNombreEmpresa = new GridBagConstraints();
                gbc_lblNombreEmpresa.anchor = GridBagConstraints.EAST;
                gbc_lblNombreEmpresa.insets = new Insets(0, 0, 5, 5);
                gbc_lblNombreEmpresa.gridx = 0;
                gbc_lblNombreEmpresa.gridy = 8;
                getContentPane().add(lblNombreEmpresa, gbc_lblNombreEmpresa);
                lblNombreEmpresa.setVisible(false);
                
                textFieldNombreEmpresa = new JTextField();
                GridBagConstraints gbc_textFieldNombreEmpresa = new GridBagConstraints();
                gbc_textFieldNombreEmpresa.gridwidth = 3;
                gbc_textFieldNombreEmpresa.insets = new Insets(0, 0, 5, 0);
                gbc_textFieldNombreEmpresa.fill = GridBagConstraints.HORIZONTAL;
                gbc_textFieldNombreEmpresa.gridx = 1;
                gbc_textFieldNombreEmpresa.gridy = 8;
                getContentPane().add(textFieldNombreEmpresa, gbc_textFieldNombreEmpresa);
                textFieldNombreEmpresa.setColumns(10);
                textFieldNombreEmpresa.setVisible(false);
                        
                descripcionLabel = new JLabel("Descripción general");
                descripcionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                GridBagConstraints gbc_descripcionLabel = new GridBagConstraints();
                gbc_descripcionLabel.anchor = GridBagConstraints.EAST;
                gbc_descripcionLabel.insets = new Insets(0, 0, 5, 5);
                gbc_descripcionLabel.gridx = 0;
                gbc_descripcionLabel.gridy = 9;
                getContentPane().add(descripcionLabel, gbc_descripcionLabel);
                descripcionLabel.setVisible(false);
                
                textPane = new JTextPane();
                textScrollPane = new JScrollPane(textPane);
                GridBagConstraints gbc_textScrollPane = new GridBagConstraints();
                gbc_textScrollPane.gridwidth = 3;
                gbc_textScrollPane.insets = new Insets(0, 0, 5, 0);
                gbc_textScrollPane.fill = GridBagConstraints.BOTH;
                gbc_textScrollPane.gridx = 1;
                gbc_textScrollPane.gridy = 9;
                getContentPane().add(textScrollPane, gbc_textScrollPane);
                textPane.setVisible(false);
                textScrollPane.setVisible(false);
                
                linkLabel = new JLabel("Página web (opcional)");
                linkLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                GridBagConstraints gbc_linkLabel = new GridBagConstraints();
                gbc_linkLabel.anchor = GridBagConstraints.EAST;
                gbc_linkLabel.insets = new Insets(0, 0, 5, 5);
                gbc_linkLabel.gridx = 0;
                gbc_linkLabel.gridy = 10;
                getContentPane().add(linkLabel, gbc_linkLabel);
                linkLabel.setVisible(false);
                
                textField_2 = new JTextField();
                GridBagConstraints gbc_textField_2 = new GridBagConstraints();
                gbc_textField_2.gridwidth = 3;
                gbc_textField_2.insets = new Insets(0, 0, 5, 0);
                gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
                gbc_textField_2.gridx = 1;
                gbc_textField_2.gridy = 10;
                getContentPane().add(textField_2, gbc_textField_2);
                textField_2.setColumns(10);
                textField_2.setVisible(false);
                                                    
                                                            btnAceptar = new JButton("Aceptar");
                                                            btnAceptar.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent arg0) {
                                                                    cmdRegistroUsuarioActionPerformed(arg0);
                                                                }
                                                            });
                                                                    
                                                                            // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
                                                                            // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
                                                                            btnCancelar = new JButton("Cancelar");
                                                                            btnCancelar.addActionListener(new ActionListener() {
                                                                                public void actionPerformed(ActionEvent e) {
                                                                                    limpiarFormulario();
                                                                                    setVisible(false);
                                                                                }
                                                                            });
                                                                            GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
                                                                            gbc_btnCancelar.anchor = GridBagConstraints.EAST;
                                                                            gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
                                                                            gbc_btnCancelar.gridwidth = 2;
                                                                            gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
                                                                            gbc_btnCancelar.gridx = 1;
                                                                            gbc_btnCancelar.gridy = 11;
                                                                            getContentPane().add(btnCancelar, gbc_btnCancelar);
                                                            
                                                                    GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
                                                                    gbc_btnAceptar.anchor = GridBagConstraints.WEST;
                                                                    gbc_btnAceptar.fill = GridBagConstraints.VERTICAL;
                                                                    gbc_btnAceptar.insets = new Insets(0, 0, 5, 0);
                                                                    gbc_btnAceptar.gridx = 3;
                                                                    gbc_btnAceptar.gridy = 11;
                                                                    getContentPane().add(btnAceptar, gbc_btnAceptar);
}

    // Este método es invocado al querer registrar un usuario, funcionalidad
    // provista por la operación del sistem registrarUsuario().
    // Previamente se hace una verificación de los campos, particularmente que no sean vacíos
    // y que la cédula sea un número. 
    // Tanto en caso de que haya un error (de verificación o de registro) o no, se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        if(checkForm()) {
        	if (esPostulante) {
        		DTPostulante postInfo = new DTPostulante(textField.getText(), textFieldNombre.getText(), textFieldApellido.getText(), textFieldMail.getText(), null, dateChooser.getDate().toInstant()
        			      .atZone(ZoneId.systemDefault())
        			      .toLocalDate(), textFieldNacionalidad.getText());
        		try {
            		iUsuario.ingresarDatosPostulante(postInfo);
            		JOptionPane.showMessageDialog(this, "Usuario registrado con éxito", "Registrar Usuario",
            				JOptionPane.INFORMATION_MESSAGE);
        		} catch(ExisteUnUsuarioYaRegistradoException e) {
        			JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario",
        					JOptionPane.ERROR_MESSAGE);
        		}
        	} else {
        		DTEmpresa empInfo = new DTEmpresa(textField.getText(), textFieldNombre.getText(), textFieldApellido.getText(), textFieldMail.getText(), null, lblNombreEmpresa.getText(), textPane.getText(), textField_2.getText());
        		try {
        			iUsuario.ingresarDatosEmpresa(empInfo);
            		JOptionPane.showMessageDialog(this, "Usuario registrado con éxito", "Registrar Usuario",
            				JOptionPane.INFORMATION_MESSAGE);
        		} catch(ExisteUnUsuarioYaRegistradoException e) {
        			JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario",
        					JOptionPane.ERROR_MESSAGE);
        		}
        	}
        	limpiarFormulario();
        }
    }
    
    private boolean checkForm() {
    	String nicknameU = textField.getText();
		String nombreU = textFieldNombre.getText();
		String apellidoU = textFieldApellido.getText();
		String mailU = textFieldMail.getText();
		String nacionalidadU = textFieldNacionalidad.getText();
		Date dateU = dateChooser.getDate();
		String nombreEmpresaU = lblNombreEmpresa.getText();
		String descripcionU = textPane.getText();
		
		
		if (esPostulante) {
			if (nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || mailU.isEmpty() || 
				nacionalidadU.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (dateU == null) {
				JOptionPane.showMessageDialog(this, "Se debe seleccionar una fecha de nacimiento", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
				
		if (!esPostulante) {
			if (nombreU.isEmpty() || apellidoU.isEmpty() || mailU.isEmpty() || descripcionU.isEmpty() || nombreEmpresaU.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	
		return true;
	}

    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
    	textField.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldMail.setText("");
        textFieldNacionalidad.setText("");
        textPane.setText("");
        textField_2.setText("");
        textFieldNombreEmpresa.setText("");
        dateChooser.setDate(null);
    }
}
