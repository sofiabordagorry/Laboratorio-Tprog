package excepciones;

@SuppressWarnings("serial")
public class TipoPubNoExistenException extends Exception{
	public TipoPubNoExistenException(String string) {
		super(string);
	}
}
