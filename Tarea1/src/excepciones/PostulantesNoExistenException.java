package excepciones;

@SuppressWarnings("serial")
public class PostulantesNoExistenException extends Exception{
	public PostulantesNoExistenException(String string) {
		super(string);
	}
}