
package publicar;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExisteUnUsuarioYaRegistradoException_QNAME = new QName("http://publicar/", "ExisteUnUsuarioYaRegistradoException");
    private final static QName _NoExistenOfertasSeleccionarPostulanteException_QNAME = new QName("http://publicar/", "NoExistenOfertasSeleccionarPostulanteException");
    private final static QName _NoHayTiposException_QNAME = new QName("http://publicar/", "NoHayTiposException");
    private final static QName _OfertasLaboralesNoExistenNingunaException_QNAME = new QName("http://publicar/", "OfertasLaboralesNoExistenNingunaException");
    private final static QName _UsuarioSinPostulacionesException_QNAME = new QName("http://publicar/", "UsuarioSinPostulacionesException");
    private final static QName _UsuariosNoExistenException_QNAME = new QName("http://publicar/", "UsuariosNoExistenException");
    private final static QName _YaSePostuloException_QNAME = new QName("http://publicar/", "YaSePostuloException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExisteUnUsuarioYaRegistradoException }
     * 
     * @return
     *     the new instance of {@link ExisteUnUsuarioYaRegistradoException }
     */
    public ExisteUnUsuarioYaRegistradoException createExisteUnUsuarioYaRegistradoException() {
        return new ExisteUnUsuarioYaRegistradoException();
    }

    /**
     * Create an instance of {@link NoExistenOfertasSeleccionarPostulanteException }
     * 
     * @return
     *     the new instance of {@link NoExistenOfertasSeleccionarPostulanteException }
     */
    public NoExistenOfertasSeleccionarPostulanteException createNoExistenOfertasSeleccionarPostulanteException() {
        return new NoExistenOfertasSeleccionarPostulanteException();
    }

    /**
     * Create an instance of {@link NoHayTiposException }
     * 
     * @return
     *     the new instance of {@link NoHayTiposException }
     */
    public NoHayTiposException createNoHayTiposException() {
        return new NoHayTiposException();
    }

    /**
     * Create an instance of {@link OfertasLaboralesNoExistenNingunaException }
     * 
     * @return
     *     the new instance of {@link OfertasLaboralesNoExistenNingunaException }
     */
    public OfertasLaboralesNoExistenNingunaException createOfertasLaboralesNoExistenNingunaException() {
        return new OfertasLaboralesNoExistenNingunaException();
    }

    /**
     * Create an instance of {@link UsuarioSinPostulacionesException }
     * 
     * @return
     *     the new instance of {@link UsuarioSinPostulacionesException }
     */
    public UsuarioSinPostulacionesException createUsuarioSinPostulacionesException() {
        return new UsuarioSinPostulacionesException();
    }

    /**
     * Create an instance of {@link UsuariosNoExistenException }
     * 
     * @return
     *     the new instance of {@link UsuariosNoExistenException }
     */
    public UsuariosNoExistenException createUsuariosNoExistenException() {
        return new UsuariosNoExistenException();
    }

    /**
     * Create an instance of {@link YaSePostuloException }
     * 
     * @return
     *     the new instance of {@link YaSePostuloException }
     */
    public YaSePostuloException createYaSePostuloException() {
        return new YaSePostuloException();
    }

    /**
     * Create an instance of {@link DtOfertaLaboral }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboral }
     */
    public DtOfertaLaboral createDtOfertaLaboral() {
        return new DtOfertaLaboral();
    }

    /**
     * Create an instance of {@link DtTipo }
     * 
     * @return
     *     the new instance of {@link DtTipo }
     */
    public DtTipo createDtTipo() {
        return new DtTipo();
    }

    /**
     * Create an instance of {@link DtKeyword }
     * 
     * @return
     *     the new instance of {@link DtKeyword }
     */
    public DtKeyword createDtKeyword() {
        return new DtKeyword();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtCompra }
     * 
     * @return
     *     the new instance of {@link DtCompra }
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPaqueteTipo }
     * 
     * @return
     *     the new instance of {@link DtPaqueteTipo }
     */
    public DtPaqueteTipo createDtPaqueteTipo() {
        return new DtPaqueteTipo();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtKeywordWS }
     * 
     * @return
     *     the new instance of {@link DtKeywordWS }
     */
    public DtKeywordWS createDtKeywordWS() {
        return new DtKeywordWS();
    }

    /**
     * Create an instance of {@link DtUsuarioWS }
     * 
     * @return
     *     the new instance of {@link DtUsuarioWS }
     */
    public DtUsuarioWS createDtUsuarioWS() {
        return new DtUsuarioWS();
    }

    /**
     * Create an instance of {@link DtCompraWS }
     * 
     * @return
     *     the new instance of {@link DtCompraWS }
     */
    public DtCompraWS createDtCompraWS() {
        return new DtCompraWS();
    }

    /**
     * Create an instance of {@link DtOfertaLaboralWS }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboralWS }
     */
    public DtOfertaLaboralWS createDtOfertaLaboralWS() {
        return new DtOfertaLaboralWS();
    }

    /**
     * Create an instance of {@link DtOfertaLaboralMisPostulacionesWS }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboralMisPostulacionesWS }
     */
    public DtOfertaLaboralMisPostulacionesWS createDtOfertaLaboralMisPostulacionesWS() {
        return new DtOfertaLaboralMisPostulacionesWS();
    }

    /**
     * Create an instance of {@link DtOfertaLaboralArray }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboralArray }
     */
    public DtOfertaLaboralArray createDtOfertaLaboralArray() {
        return new DtOfertaLaboralArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExisteUnUsuarioYaRegistradoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExisteUnUsuarioYaRegistradoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "ExisteUnUsuarioYaRegistradoException")
    public JAXBElement<ExisteUnUsuarioYaRegistradoException> createExisteUnUsuarioYaRegistradoException(ExisteUnUsuarioYaRegistradoException value) {
        return new JAXBElement<>(_ExisteUnUsuarioYaRegistradoException_QNAME, ExisteUnUsuarioYaRegistradoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExistenOfertasSeleccionarPostulanteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExistenOfertasSeleccionarPostulanteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "NoExistenOfertasSeleccionarPostulanteException")
    public JAXBElement<NoExistenOfertasSeleccionarPostulanteException> createNoExistenOfertasSeleccionarPostulanteException(NoExistenOfertasSeleccionarPostulanteException value) {
        return new JAXBElement<>(_NoExistenOfertasSeleccionarPostulanteException_QNAME, NoExistenOfertasSeleccionarPostulanteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoHayTiposException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoHayTiposException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "NoHayTiposException")
    public JAXBElement<NoHayTiposException> createNoHayTiposException(NoHayTiposException value) {
        return new JAXBElement<>(_NoHayTiposException_QNAME, NoHayTiposException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertasLaboralesNoExistenNingunaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertasLaboralesNoExistenNingunaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "OfertasLaboralesNoExistenNingunaException")
    public JAXBElement<OfertasLaboralesNoExistenNingunaException> createOfertasLaboralesNoExistenNingunaException(OfertasLaboralesNoExistenNingunaException value) {
        return new JAXBElement<>(_OfertasLaboralesNoExistenNingunaException_QNAME, OfertasLaboralesNoExistenNingunaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioSinPostulacionesException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioSinPostulacionesException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuarioSinPostulacionesException")
    public JAXBElement<UsuarioSinPostulacionesException> createUsuarioSinPostulacionesException(UsuarioSinPostulacionesException value) {
        return new JAXBElement<>(_UsuarioSinPostulacionesException_QNAME, UsuarioSinPostulacionesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuariosNoExistenException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuariosNoExistenException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuariosNoExistenException")
    public JAXBElement<UsuariosNoExistenException> createUsuariosNoExistenException(UsuariosNoExistenException value) {
        return new JAXBElement<>(_UsuariosNoExistenException_QNAME, UsuariosNoExistenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YaSePostuloException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link YaSePostuloException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "YaSePostuloException")
    public JAXBElement<YaSePostuloException> createYaSePostuloException(YaSePostuloException value) {
        return new JAXBElement<>(_YaSePostuloException_QNAME, YaSePostuloException.class, null, value);
    }

}
