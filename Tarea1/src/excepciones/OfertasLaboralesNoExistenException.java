package excepciones;

@SuppressWarnings("serial")
public class OfertasLaboralesNoExistenException extends Exception{
	public OfertasLaboralesNoExistenException(String string) {
		super(string);
	}
}
