
package publicar;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "UsuariosNoExistenException", targetNamespace = "http://publicar/")
public class UsuariosNoExistenException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UsuariosNoExistenException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UsuariosNoExistenException_Exception(String message, UsuariosNoExistenException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public UsuariosNoExistenException_Exception(String message, UsuariosNoExistenException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicar.UsuariosNoExistenException
     */
    public UsuariosNoExistenException getFaultInfo() {
        return faultInfo;
    }

}
