package excepciones;

/**
 * Excepción utilizada para indicar la inexistencia de un usuario en el sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class YaSePostuloException extends Exception {

    public YaSePostuloException(String string) {
        super(string);
    }
}