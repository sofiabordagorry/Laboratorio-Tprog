
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para loginEstado.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="loginEstado">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="NO_LOGIN"/>
 *     <enumeration value="LOGIN_INCORRECTO"/>
 *     <enumeration value="LOGIN_CORRECTO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "loginEstado")
@XmlEnum
public enum LoginEstado {

    NO_LOGIN,
    LOGIN_INCORRECTO,
    LOGIN_CORRECTO;

    public String value() {
        return name();
    }

    public static LoginEstado fromValue(String v) {
        return valueOf(v);
    }

}
