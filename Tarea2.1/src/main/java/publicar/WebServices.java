
package publicar;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WebServices", targetNamespace = "http://publicar/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    publicar.ObjectFactory.class
})
public interface WebServices {


    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @throws YaSePostuloException_Exception
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/ingresarPostulacionRequest", output = "http://publicar/WebServices/ingresarPostulacionResponse", fault = {
        @FaultAction(className = YaSePostuloException_Exception.class, value = "http://publicar/WebServices/ingresarPostulacion/Fault/YaSePostuloException")
    })
    public void ingresarPostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws YaSePostuloException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.String[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/listarTipoPublicacionOfertaLaboralRequest", output = "http://publicar/WebServices/listarTipoPublicacionOfertaLaboralResponse")
    public String[] listarTipoPublicacionOfertaLaboral();

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/verificacionDePostulantePostulacionRequest", output = "http://publicar/WebServices/verificacionDePostulantePostulacionResponse")
    public boolean verificacionDePostulantePostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/ingresarDatosOLRequest", output = "http://publicar/WebServices/ingresarDatosOLResponse")
    public boolean ingresarDatosOL(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        DtOfertaLaboral arg2);

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.String[]
     * @throws NoHayTiposException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/listarNomTiposRequest", output = "http://publicar/WebServices/listarNomTiposResponse", fault = {
        @FaultAction(className = NoHayTiposException_Exception.class, value = "http://publicar/WebServices/listarNomTipos/Fault/NoHayTiposException")
    })
    public String[] listarNomTipos()
        throws NoHayTiposException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.String[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/listarNomPaquetesRequest", output = "http://publicar/WebServices/listarNomPaquetesResponse")
    public String[] listarNomPaquetes();

    /**
     * 
     * @return
     *     returns publicar.DtUsuarioWS
     * @throws UsuariosNoExistenException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/listarUsuariosRequest", output = "http://publicar/WebServices/listarUsuariosResponse", fault = {
        @FaultAction(className = UsuariosNoExistenException_Exception.class, value = "http://publicar/WebServices/listarUsuarios/Fault/UsuariosNoExistenException")
    })
    public DtUsuarioWS listarUsuarios()
        throws UsuariosNoExistenException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws ExisteUnUsuarioYaRegistradoException_Exception
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/ingresarDatosPostulanteRequest", output = "http://publicar/WebServices/ingresarDatosPostulanteResponse", fault = {
        @FaultAction(className = ExisteUnUsuarioYaRegistradoException_Exception.class, value = "http://publicar/WebServices/ingresarDatosPostulante/Fault/ExisteUnUsuarioYaRegistradoException")
    })
    public void ingresarDatosPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        DtPostulante arg0)
        throws ExisteUnUsuarioYaRegistradoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws ExisteUnUsuarioYaRegistradoException_Exception
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/ingresarDatosEmpresaRequest", output = "http://publicar/WebServices/ingresarDatosEmpresaResponse", fault = {
        @FaultAction(className = ExisteUnUsuarioYaRegistradoException_Exception.class, value = "http://publicar/WebServices/ingresarDatosEmpresa/Fault/ExisteUnUsuarioYaRegistradoException")
    })
    public void ingresarDatosEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        DtEmpresa arg0)
        throws ExisteUnUsuarioYaRegistradoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/mostrarInformacionUsuarioRequest", output = "http://publicar/WebServices/mostrarInformacionUsuarioResponse")
    public DtUsuario mostrarInformacionUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns publicar.DtOfertaLaboralWS
     * @throws OfertasLaboralesNoExistenNingunaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/getDTOfertasLaboralesRequest", output = "http://publicar/WebServices/getDTOfertasLaboralesResponse", fault = {
        @FaultAction(className = OfertasLaboralesNoExistenNingunaException_Exception.class, value = "http://publicar/WebServices/getDTOfertasLaborales/Fault/OfertasLaboralesNoExistenNingunaException")
    })
    public DtOfertaLaboralWS getDTOfertasLaborales()
        throws OfertasLaboralesNoExistenNingunaException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.String[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/obtenerSeguidosRequest", output = "http://publicar/WebServices/obtenerSeguidosResponse")
    public String[] obtenerSeguidos(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/dejarSeguimientoRequest", output = "http://publicar/WebServices/dejarSeguimientoResponse")
    public void dejarSeguimiento(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/existePostulanteRequest", output = "http://publicar/WebServices/existePostulanteResponse")
    public boolean existePostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @return
     *     returns publicar.DtPostulante
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/crearDTPostulanteRequest", output = "http://publicar/WebServices/crearDTPostulanteResponse")
    public DtPostulante crearDTPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        byte[] arg7);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/quitarOfertaFavRequest", output = "http://publicar/WebServices/quitarOfertaFavResponse")
    public void quitarOfertaFav(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/existeEmpresaRequest", output = "http://publicar/WebServices/existeEmpresaResponse")
    public boolean existeEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/estaSiguiendoRequest", output = "http://publicar/WebServices/estaSiguiendoResponse")
    public boolean estaSiguiendo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtEmpresa
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarEmpresaRequest", output = "http://publicar/WebServices/buscarEmpresaResponse")
    public DtEmpresa buscarEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/seguirUsuarioRequest", output = "http://publicar/WebServices/seguirUsuarioResponse")
    public void seguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.String[]
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/obtenerSeguidoresRequest", output = "http://publicar/WebServices/obtenerSeguidoresResponse")
    public String[] obtenerSeguidores(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/esFavoritoRequest", output = "http://publicar/WebServices/esFavoritoResponse")
    public boolean esFavorito(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @return
     *     returns publicar.DtEmpresa
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/crearDTEmpresaRequest", output = "http://publicar/WebServices/crearDTEmpresaResponse")
    public DtEmpresa crearDTEmpresa(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        byte[] arg7);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/agregarOfertaFavRequest", output = "http://publicar/WebServices/agregarOfertaFavResponse")
    public void agregarOfertaFav(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtPostulante
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/dataPostulanteRequest", output = "http://publicar/WebServices/dataPostulanteResponse")
    public DtPostulante dataPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/existeMailRequest", output = "http://publicar/WebServices/existeMailResponse")
    public boolean existeMail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns publicar.DtPostulacion
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/dataPostulacionRequest", output = "http://publicar/WebServices/dataPostulacionResponse")
    public DtPostulacion dataPostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtPaquete
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarPaqueteRequest", output = "http://publicar/WebServices/buscarPaqueteResponse")
    public DtPaquete buscarPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/realizarSeleccionRequest", output = "http://publicar/WebServices/realizarSeleccionResponse")
    public void realizarSeleccion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String[] arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/comprarPaqueteRequest", output = "http://publicar/WebServices/comprarPaqueteResponse")
    public boolean comprarPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/estaVigenteOfertaRequest", output = "http://publicar/WebServices/estaVigenteOfertaResponse")
    public boolean estaVigenteOferta(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtTipo
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarTipoRequest", output = "http://publicar/WebServices/buscarTipoResponse")
    public DtTipo buscarTipo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtPostulante
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarPostulanteRequest", output = "http://publicar/WebServices/buscarPostulanteResponse")
    public DtPostulante buscarPostulante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarUsuarioRequest", output = "http://publicar/WebServices/buscarUsuarioResponse")
    public DtUsuario buscarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns publicar.DtKeywordWS
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/getDTKeywordRequest", output = "http://publicar/WebServices/getDTKeywordResponse")
    public DtKeywordWS getDTKeyword();

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtCompraWS
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/getPaqCompradosRequest", output = "http://publicar/WebServices/getPaqCompradosResponse")
    public DtCompraWS getPaqComprados(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/cambiarEstadoRequest", output = "http://publicar/WebServices/cambiarEstadoResponse")
    public void cambiarEstado(
        @WebParam(name = "arg0", partName = "arg0")
        LoginEstado arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtOfertaLaboralWS
     * @throws NoExistenOfertasSeleccionarPostulanteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/getOfertasSeleccionarPosutlanteRequest", output = "http://publicar/WebServices/getOfertasSeleccionarPosutlanteResponse", fault = {
        @FaultAction(className = NoExistenOfertasSeleccionarPostulanteException_Exception.class, value = "http://publicar/WebServices/getOfertasSeleccionarPosutlante/Fault/NoExistenOfertasSeleccionarPostulanteException")
    })
    public DtOfertaLaboralWS getOfertasSeleccionarPosutlante(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws NoExistenOfertasSeleccionarPostulanteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtOfertaLaboralMisPostulacionesWS
     * @throws UsuarioSinPostulacionesException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/listarOfertasPostuladoRequest", output = "http://publicar/WebServices/listarOfertasPostuladoResponse", fault = {
        @FaultAction(className = UsuarioSinPostulacionesException_Exception.class, value = "http://publicar/WebServices/listarOfertasPostulado/Fault/UsuarioSinPostulacionesException")
    })
    public DtOfertaLaboralMisPostulacionesWS listarOfertasPostulado(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioSinPostulacionesException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtOfertaLaboral
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/obtenerDTOfertaLaboralRequest", output = "http://publicar/WebServices/obtenerDTOfertaLaboralResponse")
    public DtOfertaLaboral obtenerDTOfertaLaboral(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/obtenerVideoPostulacionRequest", output = "http://publicar/WebServices/obtenerVideoPostulacionResponse")
    public String obtenerVideoPostulacion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/verificacionCompraPaqRequest", output = "http://publicar/WebServices/verificacionCompraPaqResponse")
    public boolean verificacionCompraPaq(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtOfertaLaboral
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarOfertaLaboralRequest", output = "http://publicar/WebServices/buscarOfertaLaboralResponse")
    public DtOfertaLaboral buscarOfertaLaboral(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/buscarUsuarioPorMailRequest", output = "http://publicar/WebServices/buscarUsuarioPorMailResponse")
    public DtUsuario buscarUsuarioPorMail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     */
    @WebMethod
    @Action(input = "http://publicar/WebServices/cambiarEstadoOfertaRequest", output = "http://publicar/WebServices/cambiarEstadoOfertaResponse")
    public void cambiarEstadoOferta(
        @WebParam(name = "arg0", partName = "arg0")
        EstadoOL arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @return
     *     returns publicar.DtOfertaLaboral
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/crearDTOfertaLaboralRequest", output = "http://publicar/WebServices/crearDTOfertaLaboralResponse")
    public DtOfertaLaboral crearDTOfertaLaboral(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        float arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String[] arg6);

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://publicar/WebServices/existeOfertaEnListaRequest", output = "http://publicar/WebServices/existeOfertaEnListaResponse")
    public boolean existeOfertaEnLista(
        @WebParam(name = "arg0", partName = "arg0")
        DtOfertaLaboralArray arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}
