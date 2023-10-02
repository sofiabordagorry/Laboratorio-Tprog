package excepciones;

@SuppressWarnings("serial")
public class EmpresaSinOfertasException extends Exception{
	public EmpresaSinOfertasException(String string) {
		super(string);
	}
}