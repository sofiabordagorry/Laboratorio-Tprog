
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOfertaLaboral complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtOfertaLaboral">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fechaDeAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costoAsociado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="dataTipo" type="{http://publicar/}dtTipo" minOccurs="0"/>
 *         <element name="estado" type="{http://publicar/}estadoOL" minOccurs="0"/>
 *         <element name="dataKeywords" type="{http://publicar/}dtKeyword" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="dataPostulaciones" type="{http://publicar/}dtPostulacion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="dataEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         <element name="rankeada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="cantidadFav" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaSeleccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaLaboral", propOrder = {
    "nombre",
    "descripcion",
    "ciudad",
    "departamento",
    "horario",
    "remuneracion",
    "fechaDeAlta",
    "costoAsociado",
    "dataTipo",
    "estado",
    "dataKeywords",
    "dataPostulaciones",
    "dataEmpresa",
    "imagen",
    "rankeada",
    "cantidadFav",
    "fechaSeleccion"
})
public class DtOfertaLaboral {

    protected String nombre;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horario;
    protected float remuneracion;
    protected String fechaDeAlta;
    protected float costoAsociado;
    protected DtTipo dataTipo;
    @XmlSchemaType(name = "string")
    protected EstadoOL estado;
    @XmlElement(nillable = true)
    protected List<DtKeyword> dataKeywords;
    @XmlElement(nillable = true)
    protected List<DtPostulacion> dataPostulaciones;
    protected String dataEmpresa;
    protected byte[] imagen;
    protected boolean rankeada;
    protected int cantidadFav;
    protected String fechaSeleccion;

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
     * Obtiene el valor de la propiedad ciudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Define el valor de la propiedad ciudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad horario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Define el valor de la propiedad horario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorario(String value) {
        this.horario = value;
    }

    /**
     * Obtiene el valor de la propiedad remuneracion.
     * 
     */
    public float getRemuneracion() {
        return remuneracion;
    }

    /**
     * Define el valor de la propiedad remuneracion.
     * 
     */
    public void setRemuneracion(float value) {
        this.remuneracion = value;
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
     * Obtiene el valor de la propiedad dataTipo.
     * 
     * @return
     *     possible object is
     *     {@link DtTipo }
     *     
     */
    public DtTipo getDataTipo() {
        return dataTipo;
    }

    /**
     * Define el valor de la propiedad dataTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link DtTipo }
     *     
     */
    public void setDataTipo(DtTipo value) {
        this.dataTipo = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoOL }
     *     
     */
    public EstadoOL getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoOL }
     *     
     */
    public void setEstado(EstadoOL value) {
        this.estado = value;
    }

    /**
     * Gets the value of the dataKeywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dataKeywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtKeyword }
     * 
     * 
     * @return
     *     The value of the dataKeywords property.
     */
    public List<DtKeyword> getDataKeywords() {
        if (dataKeywords == null) {
            dataKeywords = new ArrayList<>();
        }
        return this.dataKeywords;
    }

    /**
     * Gets the value of the dataPostulaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dataPostulaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataPostulaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulacion }
     * 
     * 
     * @return
     *     The value of the dataPostulaciones property.
     */
    public List<DtPostulacion> getDataPostulaciones() {
        if (dataPostulaciones == null) {
            dataPostulaciones = new ArrayList<>();
        }
        return this.dataPostulaciones;
    }

    /**
     * Obtiene el valor de la propiedad dataEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataEmpresa() {
        return dataEmpresa;
    }

    /**
     * Define el valor de la propiedad dataEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEmpresa(String value) {
        this.dataEmpresa = value;
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

    /**
     * Obtiene el valor de la propiedad rankeada.
     * 
     */
    public boolean isRankeada() {
        return rankeada;
    }

    /**
     * Define el valor de la propiedad rankeada.
     * 
     */
    public void setRankeada(boolean value) {
        this.rankeada = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadFav.
     * 
     */
    public int getCantidadFav() {
        return cantidadFav;
    }

    /**
     * Define el valor de la propiedad cantidadFav.
     * 
     */
    public void setCantidadFav(int value) {
        this.cantidadFav = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSeleccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSeleccion() {
        return fechaSeleccion;
    }

    /**
     * Define el valor de la propiedad fechaSeleccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSeleccion(String value) {
        this.fechaSeleccion = value;
    }

}
