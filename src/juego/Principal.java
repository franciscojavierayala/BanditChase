package juego;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * La clase Principal contiene el método principal para iniciar la aplicación del juego.
 * 
 * @author Francisco Javier Ayala Parejo.
 * @version 1.0
 */
public class Principal extends JFrame {
	
	
	/**
	 * El número de versión de la clase, requerido por la interfaz Serializable de Java.
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * El punto de entrada principal para iniciar la aplicación del juego.
	 * 
	 * @param args Los argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Crear una instancia del menú principal y hacerlo visible
			MenuPrincipal mainMenu = new MenuPrincipal();
			mainMenu.setVisible(true);
		});
	}
}
