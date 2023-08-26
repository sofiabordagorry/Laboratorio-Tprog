package excepciones;

@SuppressWarnings("serial")
public class UsuariosNoExistenException extends Exception{
	public UsuariosNoExistenException(String string) {
		super(string);
	}
}