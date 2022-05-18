package interfaces;

import entidades.Administrador;
import entidades.Asignacion_Traslado;
import entidades.Productor;
import entidades.Productora;
import entidades.Quimico;
import entidades.Residuo;
import entidades.Residuo_Transporte;
import entidades.Solicitud_de_Traslado;
import entidades.Transportador;
import entidades.Transporte;
import entidades.Traslado;
import entidades.Vehiculo;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Interface para la fachada de los datos
 */
public interface IDatos {

    /**
     * Método para guardar la solicitud de traslado, recibe la solicitud del
     * traslado.
     *
     *
     * @param solicitudDeTraslado Solicitud de traslado.
     * @return true si la solicitud de traslado se registro con exito, false en
     * caso contrario
     */
    public boolean guardarSolicitud(Solicitud_de_Traslado solicitudDeTraslado);

    /**
     *Método para consultar las solicitudes no atendidas
     *
     * @return La lista de solicitudes no atendidas.
     */
    public List<Solicitud_de_Traslado> consultarSolicitudesNoAtendidas();

    /**
     * Método para validar las solicitudes de traslado, recibe la solicitud del
     * traslado
     *
     * @param solicitudDeTraslado Solicitud de traslado a validar.
     * @return El mensaje de la validacion.
     */
    public String validarSolicitud(Solicitud_de_Traslado solicitudDeTraslado);

    /**
     * Método para actualizar las solicitudes, recibe la solicitud y el residuo
     * a transportar
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado
     * @param residuoTransporte Residuo de transporte a actualizar.
     * @return true si este se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitud(ObjectId _idSolicitudTraslado, Residuo_Transporte residuoTransporte);

    /**
     * Método para actualizar la solicitud del traslado, recibe el id de la
     * solicitud del traslado
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitudTraslado(ObjectId _idSolicitudTraslado);

    /**
     * Método para guardar la asignación de traslado, recibe la lista de
     * traslados a asignar
     *
     * @param list Lista de asignaciones de traslado a registrar.
     * @return true si estas se registraron con exito, false en caso contrario.
     */
    public boolean guardarAsignacionTraslado(List<Asignacion_Traslado> list);

    /**
     * Método para consultar las asignaciones de traslados por empresa de
     * transporte, recibe el id de la empresa
     *
     * @param idEmpresaTransporte ID de la empresa de transporte.
     * @return La lista de las asignaciones de traslado de la empresa de
     * transporte.
     */
    public List<Asignacion_Traslado> consultarAsignacionTrasladosEmpresaT(ObjectId idEmpresaTransporte);

    /**
     * Método para actualizar la asignación, recibe el id de parámetro
     *
     * @param _id ID de la asignacion a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarAsignacion(ObjectId _id);

    /**
     *Método para guardar el traslado, recibe el traslado a guardar
     *
     * @param traslado Traslado a guardar.
     * @return true si el traslado se registro con exito, false en caso
     * contrario.
     */
    public boolean guardarTraslado(Traslado traslado);

    /**
     * Método para obtener una lista de químicos
     *
     * @return Lista de quimicos del sistema.
     */
    public List<Quimico> getQuimicos();

    /**
     * Método para guardar un químico, recibe el mismo
     *
     * @param quimico Quimico a guardar.
     * @return true si el quimico se guardo, false en caso contrario.
     */
    public boolean guardarQuimico(Quimico quimico);

    /**
     * Método para guardar al administrador, recibe al mismo
     *
     * @param administrador Usuario administrador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    public boolean guardarAdministrador(Administrador administrador);

    /**
     * Método para consultar al administrador, recibe su nombre y contraseña
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usario si este se encontro, null en caso contrario.
     */
    public Administrador consultarAdministrador(String nombre, String contraseña);

    /**
     *Método para guardar al productor, recibe al mismo productor
     *
     * @param productor Productor a registrar.
     * @return true si el usuario productor se guardo con exito, false en caso
     * contrario
     */
    public boolean guardarProductor(Productor productor);

    /**
     * Método para consultar al productor, recibe su nombre y contraseña
     *
     * @param nombre Nombre del usuario productor a consultar.
     * @param contraseña Contraseña del usuario productor a consultar.
     * @return EL usuario productor si este existe, null en caso contrario.
     */
    public Productor consultarProductor(String nombre, String contraseña);

    /**
     * Método para guardar al transportador, recibe al mismo
     *
     * @param transportador Usuario transportador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    public boolean guardarTransportador(Transportador transportador);

    /**
     * Método para consultar al transportador, recibe su nombre y contraseña
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usuario transportado que se obtuvo o null si este no se
     * encontro.
     */
    public Transportador consultarTransportador(String nombre, String contraseña);

    /**
     * método para validar al usuario, recibe el nombre del mismo
     *
     * @param nombre Nombre a validar.
     * @return true si existe alguno, false en caso contrario.
     */
    public boolean validarUsuario(String nombre);

    /**
     * Método para obtener las empresas productoras
     *
     * @return Lista de la empresa productoras disponibles.
     */
    public List<Productora> getEmpresasProductoras();

    /**
     * método para obtener las empresas de transporte
     *
     * @return Lista de las empresas de transporte disponibles.
     */
    public List<Transporte> getEmpresasTransportes();

    /**
     * Método para obtener la empresa productora por id
     *
     * resultante de la operación
     *
     * @param _id ID de la empresa productora.
     * @return La empresa productora obtenida.
     */
    public Productora getEmpresaProductora(ObjectId _id);

    /**
     * Método para obtener la empresa de transporte por id
     *
     * @param _id El id de la empresa de transporte.
     * @return La empresa de transporte obtenida.
     */
    public Transporte getEmpresaTransporte(ObjectId _id);

    /**
     * Método para guardar la empresa productora, recibe a la misma
     *
     * @param productora La empresa productora a guardar.
     * @return true si la empresa empresa productora se guardo con exito, false
     * en caso contrario.
     */
    public boolean guardarEmpresaProductora(Productora productora);

    /**
     * Método para guardar la empresa de transporte, recibe a la misma
     *
     * @param transporte La empresa de transporte a regisstrar.
     * @return true si la empresa de transporte registrada se guardo con exito,
     * false en caso contrario.
     */
    public boolean guardarEmpresaTransporte(Transporte transporte);

    /**
     * Método para guardar un vehiculo, recibe el vehiculo y la empresa de
     * transporte involucrada
     *
     * @param vehiculo Vehiculo a guardar.
     * @param transporteEmpresa Empresa transportadora a guardar el vehiculo.
     * @return true si el vehiculo se guardo con exito, false en caso contrario.
     */
    public boolean guardarVehiculo(Vehiculo vehiculo, Transporte transporteEmpresa);

    /**
     * Método para guardar un residuo
     *
     * @param residuo Residuo a guadar.
     * @return true si el residuo se registro con exito, false en caso
     * contrario.
     */
    public boolean guardarResiduo(Residuo residuo);

    /**
     *Método para consultar los residuos con el id de una empresa productora
     *
       * @param idEmpresaProductora ID de la empresa productora.
     * @return lista de los residuos de la empresa productora.
     */
    public List<Residuo> consultarResiduosProductor(ObjectId idEmpresaProductora);

    /**
     * Método para validar un residuo, recibe el mismo
     *
     * @param residuo Residuo a validar.
     * @return Mensaje si el residuo es valido o no.
     */
    public String validarResiduo(Residuo residuo);
}
