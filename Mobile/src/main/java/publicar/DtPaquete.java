
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="periodoDeValidez" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="descuento" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costoAsociado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="paquetesTipos" type="{http://publicar/}dtPaqueteTipo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="fechaDeAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPaquete", propOrder = {
    "nombre",
    "descripcion",
    "periodoDeValidez",
    "descuento",
    "fecha",
    "costoAsociado",
    "paquetesTipos",
    "fechaDeAlta",
    "imagen"
})
public class DtPaquete {

    protected String nombre;
    protected String descripcion;
    protected int periodoDeValidez;
    protected float descuento;
    protected String fecha;
    protected float costoAsociado;
    @XmlElement(nillable = true)
    protected List<DtPaqueteTipo> paquetesTipos;
    protected String fechaDeAlta;
    protected byte[] imagen;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad periodoDeValidez.
     * 
     */
    public int getPeriodoDeValidez() {
        return periodoDeValidez;
    }

    /**
     * Define el valor de la propiedad periodoDeValidez.
     * 
     */
    public void setPeriodoDeValidez(int value) {
        this.periodoDeValidez = value;
    }

    /**
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(float value) {
        this.descuento = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad costoAsociado.
     * 
     */
    public float getCostoAsociado() {
        return costoAsociado;
    }

    /**
     * Define el valor de la propiedad costoAsociado.
     * 
     */
    public void setCostoAsociado(float value) {
        this.costoAsociado = value;
    }

    /**
     * Gets the value of the paquetesTipos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the paquetesTipos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquetesTipos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPaqueteTipo }
     * 
     * 
     * @return
     *     The value of the paquetesTipos property.
     */
    public List<DtPaqueteTipo> getPaquetesTipos() {
        if (paquetesTipos == null) {
            paquetesTipos = new ArrayList<>();
        }
        return this.paquetesTipos;
    }

    /**
     * Obtiene el valor de la propiedad fechaDeAlta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeAlta() {
        return fechaDeAlta;
    }

    /**
     * Define el valor de la propiedad fechaDeAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeAlta(String value) {
        this.fechaDeAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

}
