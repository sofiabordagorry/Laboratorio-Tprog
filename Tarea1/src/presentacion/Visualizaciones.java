package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Visualizaciones extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizaciones frame = new Visualizaciones();
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
	public Visualizaciones() {
		setBounds(100, 100, 450, 300);

	}

}
