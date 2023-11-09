
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCompra">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paqComprado" type="{http://publicar/}dtPaquete" minOccurs="0"/>
 *         <element name="ofertasLaborales" type="{http://publicar/}dtOfertaLaboral" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompra", propOrder = {
    "fechaCompra",
    "fechaVencimiento",
    "paqComprado",
    "ofertasLaborales"
})
public class DtCompra {

    protected String fechaCompra;
    protected String fechaVencimiento;
    protected DtPaquete paqComprado;
    @XmlElement(nillable = true)
    protected List<DtOfertaLaboral> ofertasLaborales;

    /**
     * Obtiene el valor de la propiedad fechaCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Define el valor de la propiedad fechaCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCompra(String value) {
        this.fechaCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad paqComprado.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquete }
     *     
     */
    public DtPaquete getPaqComprado() {
        return paqComprado;
    }

    /**
     * Define el valor de la propiedad paqComprado.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquete }
     *     
     */
    public void setPaqComprado(DtPaquete value) {
        this.paqComprado = value;
    }

    /**
     * Gets the value of the ofertasLaborales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ofertasLaborales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfertasLaborales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtOfertaLaboral }
     * 
     * 
     * @return
     *     The value of the ofertasLaborales property.
     */
    public List<DtOfertaLaboral> getOfertasLaborales() {
        if (ofertasLaborales == null) {
            ofertasLaborales = new ArrayList<>();
        }
        return this.ofertasLaborales;
    }

}
