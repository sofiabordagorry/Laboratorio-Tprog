package excepciones;

@SuppressWarnings("serial")
public class OfertasLaboralesNoExistenNingunaException extends Exception{
	public OfertasLaboralesNoExistenNingunaException(String string) {
		super(string);
	}
}