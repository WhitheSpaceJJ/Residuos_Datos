package implementaciones;

import interfaces.IDatos;
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
import interfaces.IRepoAsignacionTraslado;
import interfaces.IRepoEmpresas;
import interfaces.IRepoQuimicos;
import interfaces.IRepoResiduos;
import interfaces.IRepoSolicitudTraslado;
import interfaces.IRepoTraslado;
import interfaces.IRepoUsuarios;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Fachada para los datos, dentro de esta fachada se encuentran todos los repos
 * para reducir las comunicaciones y dependencias entre los subsistemas, la
 * fachada cuenta con métodos utilizados en cada uno de los repositorios de la
 * capa de datos.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 *
 */
public class FDatos implements IDatos {

    private IRepoEmpresas repoEmpresas;
    private IRepoUsuarios repoUsuarios;
    private IRepoAsignacionTraslado repoAsignacionTraslado;
    private IRepoQuimicos repoQuimicos;
    private IRepoResiduos repoResiduos;
    private IRepoSolicitudTraslado repoSolicitudTraslado;
    private IRepoTraslado repoTraslado;

    /**
     * Clase que sirve para crear un repo de Asignación Traslado, lo devuelve en
     * caso de que ya exista, en caso de que no exista crea uno nuevo y lo
     * devuelve.
     *
     * @return Instancia de la clase RepoAsignacionTraslado
     */
    public IRepoAsignacionTraslado crearRepoAsignacionTrasladoO() {
        if (repoAsignacionTraslado != null) {
            return repoAsignacionTraslado;
        } else {
            this.repoAsignacionTraslado = new RepoAsignacionTraslado();
            return repoAsignacionTraslado;
        }

    }

    /**
     * Clase que sirve para crear un repo de químicos, lo devuelve en caso de
     * que ya exista, en caso de que no exista crea uno nuevo y lo devuelve
     *
     * @return Instancia de la clase RepoQuimicos
     */
    public IRepoQuimicos crearRepoQuimicos() {
        if (repoQuimicos != null) {
            return repoQuimicos;
        } else {
            this.repoQuimicos = new RepoQuimicos();
            return repoQuimicos;
        }
    }

    /**
     * Clase que sirve para crear un repo de residuos, lo devuelve en caso de
     * que ya exista, en caso de que no exista crea uno nuevo y lo devuelve
     *
     * @return Instancia de la clase RepoResiduos
     */
    public IRepoResiduos crearRepoResiduos() {
        if (repoResiduos != null) {
            return repoResiduos;
        } else {
            this.repoResiduos = new RepoResiduos();
            return repoResiduos;
        }
    }

    /**
     * Clase que sirve para crear un repo de Asignación Traslado, lo devuelve en
     * caso de que ya exista, en caso de que no exista crea uno nuevo y lo
     * devuelve
     *
     * @return Instancia de la clase RepoSolicitudTraslado
     */
    public IRepoSolicitudTraslado crearRepoSolicitudTraslado() {
        if (repoSolicitudTraslado != null) {
            return repoSolicitudTraslado;
        } else {
            this.repoSolicitudTraslado = new RepoSolicitudTraslado();
            return repoSolicitudTraslado;
        }
    }

    /**
     * Clase que sirve para crear un repo de traslado, lo devuelve en caso de
     * que ya exista, en caso de que no exista crea uno nuevo y lo devuelve
     *
     * @return Instancia de la clase RepoTraslado
     */
    public IRepoTraslado crearRepoTraslado() {
        if (repoTraslado != null) {
            return repoTraslado;
        } else {
            this.repoTraslado = new RepoTraslado();
            return repoTraslado;
        }
    }

    /**
     * Clase que sirve para crear un repo de usuarios, lo devuelve en caso de
     * que ya exista, en caso de que no exista crea uno nuevo y lo devuelve
     *
     * @return Instancia de la clase RepoUsuarios *
     */
    public IRepoUsuarios crearRepoUsuarios() {
        if (repoUsuarios != null) {
            return repoUsuarios;
        } else {
            this.repoUsuarios = new RepoUsuarios();
            return repoUsuarios;
        }
    }

    /**
     * Clase que sirve para crear un repo de empresas, lo devuelve en caso de
     * que ya exista, en caso de que no exista crea uno nuevo y lo devuelve
     *
     * @return Instancia de la clase RepoEmpresas *
     */
    public IRepoEmpresas crearRepoEmpresas() {
        if (repoEmpresas != null) {
            return repoEmpresas;
        } else {
            this.repoEmpresas = new RepoEmpresas();
            return repoEmpresas;
        }
    }

    /**
     * Construtor por defecto de la fachada.
     */
    public FDatos() {
    }

    /**
     * Llama al método guardarAdministrador, recibe un administrador de entrada
     * el cual sera mandado al método que la RepoUsuarios, con el fin de
     * registrarlo.
     *
     * @param administrador Usuario administrador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    @Override
    public boolean guardarAdministrador(Administrador administrador) {
        return this.crearRepoUsuarios().guardarAdministrador(administrador);
    }

    /**
     * Llama al método consultarAdministrador de la RepoUsuarios, recibe el
     * nombre y la contraseña para consultarlo un usuario administrador.
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usario si este se encontro, null en caso contrario.
     */
    @Override
    public Administrador consultarAdministrador(String nombre, String contraseña) {
        return this.crearRepoUsuarios().consultarAdministrador(nombre, contraseña);
    }

    /**
     * Llama al método guardarProductor de la RepoUsuarios, recibe el productor
     * como parámetro y devuelve lo resultante de la operación.
     *
     * @param productor Productor a registrar.
     * @return true si el usuario productor se guardo con exito, false en caso
     * contrario
     */
    @Override
    public boolean guardarProductor(Productor productor) {
        return this.crearRepoUsuarios().guardarProductor(productor);
    }

    /**
     * LLama al método consultarProductor de la RepoUsuarios, recibe el nombre y
     * la contraseña del mismo, devuelve lo resultante de la operación.
     *
     * @param nombre Nombre del usuario productor a consultar.
     * @param contraseña Contraseña del usuario productor a consultar.
     * @return El usuario productor si este existe, null en caso contrario.
     */
    @Override
    public Productor consultarProductor(String nombre, String contraseña) {
        Productor productor = this.crearRepoUsuarios().consultarProductor(nombre, contraseña);
        if (productor != null) {
            Productora p = this.getEmpresaProductora(productor.getIdEmpresaProductora());
            productor.setProductora(p);
            return productor;
        }
        return null;
    }

    /**
     * Llama al método de guardarTransportador de la RepoUsuarios y devuelve lo
     * resultante de la operación.
     *
     * @param transportador Usuario transportador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    @Override
    public boolean guardarTransportador(Transportador transportador) {
        return this.crearRepoUsuarios().guardarTransportador(transportador);
    }

    /**
     * Llama al método consultarTransportador de la RepoUsuarios, , devuelve lo
     * resultante de la operación.
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usuario transportado que se obtuvo o null si este no se
     * encontro.
     */
    @Override
    public Transportador consultarTransportador(String nombre, String contraseña) {
        Transportador transportador = this.crearRepoUsuarios().consultarTransportador(nombre, contraseña);
        if (transportador != null) {
            Transporte t = this.getEmpresaTransporte(transportador.getIdEmpresaTransporte());
            transportador.setEmpresaTransporte(t);
            return transportador;
        }
        return null;
    }

    /**
     * Llama al método validarUsuario de la RepoUsuarios,, devuelve lo
     * resultante de la operación.
     *
     * @param nombre Nombre a validar.
     * @return true si existe alguno, false en caso contrario.
     */
    @Override
    public boolean validarUsuario(String nombre) {
        return this.crearRepoUsuarios().validarUsuario(nombre);
    }

    /**
     * Llama al método getEmpresasProductoras de la RepoEmpresas, , devuelve lo
     * resultante de la operación.
     *
     * @return Lista de la empresa productoras disponibles.
     */
    @Override
    public List<Productora> getEmpresasProductoras() {
        return this.crearRepoEmpresas().getEmpresasProductoras();
    }

    /**
     * Llama al método getEmpresasTransporte de la RepoEmpresas, devuelve lo
     * resultante de la operación.
     *
     * @return Lista de las empresas de transporte disponibles.
     */
    @Override
    public List<Transporte> getEmpresasTransportes() {
        return this.crearRepoEmpresas().getEmpresasTransportes();
    }

    /**
     * Llama al método getEmpresaProductora de la RepoEmpresas, devuelve lo
     * resultante de la operación
     *
     * @param _id El id de la empresa productora.
     * @return La empresa transportadora obtenida.
     */
    @Override
    public Productora getEmpresaProductora(ObjectId _id) {
        return this.crearRepoEmpresas().getEmpresaProductora(_id);
    }

    /**
     * Llama al método getEmpresasTransporte de la RepoEmpresas y recibe de
     * parámetro el id para hacer la consulta, devuelve lo resultante de la
     * operación
     *
     * @param _id El id de la empresa de transporte.
     * @return La empresa de transporte obtenida.
     */
    @Override
    public Transporte getEmpresaTransporte(ObjectId _id) {
        return this.crearRepoEmpresas().getEmpresaTransporte(_id);
    }

    /**
     * Llama al método guardarEmpresaProductora de la RepoEmpresas y recibe de
     * parámetro la productora a ser guardada, devuelve lo resultante de la
     * operación
     *
     * @param productora La empresa productora a guardar.
     * @return true si la empresa empresa productora se guardo con exito, false
     * en caso contrario.
     */
    @Override
    public boolean guardarEmpresaProductora(Productora productora) {
        return this.crearRepoEmpresas().guardarEmpresaProductora(productora);
    }

    /**
     * Llama al método guardarEmpresaTransporte de la RepoEmpresas y recibe de
     * parámetro la empresa de transporte a ser guardada, devuelve lo resultante
     * de la operación
     *
     * @param transporte La empresa de transporte a registrar.
     * @return true si la empresa de transporte registrada se guardo con exito,
     * false en caso contrario.
     */
    @Override
    public boolean guardarEmpresaTransporte(Transporte transporte) {
        return this.crearRepoEmpresas().guardarEmpresaTransporte(transporte);
    }

    /**
     * Llama al método guardarVehiculo de la RepoEmpresas, recibe de parámetro
     * el vehiculo y la empresa de transporte a guardar, devuelve lo resultante
     * de la operación
     *
     * @param vehiculo Vehiculo a guardar.
     * @param transporteEmpresa Empresa transportadora a guardar el vehiculo.
     * @return true si el vehiculo se guardo con exito, false en caso contrario.
     */
    @Override
    public boolean guardarVehiculo(Vehiculo vehiculo, Transporte transporteEmpresa) {
        return this.crearRepoEmpresas().guardarVehiculo(vehiculo, transporteEmpresa);
    }

    /**
     * Llama al método getQuimicos de la RepoQuimicos, devuelve lo resultante de
     * la operación
     *
     * @return Lista de quimicos del sistema.
     */
    @Override
    public List<Quimico> getQuimicos() {
        return this.crearRepoQuimicos().getQuimicos();
    }

    /**
     * Llama al método guardarQuímico de la RepoQuimicos, recibe de parámetro el
     * químico a guardar, devuelve lo resultante de la operación
     *
     * @param quimico Quimico a guardar.
     * @return true si el quimico se guardo, false en caso contrario.
     */
    @Override
    public boolean guardarQuimico(Quimico quimico) {
        return this.crearRepoQuimicos().guardarQuimico(quimico);
    }

    /**
     * Llama al método guardarTraslado de la RepoTraslado, recibe de parámetro
     * el traslado a guardar, devuelve lo resultante de la operación
     *
     * @param traslado Traslado a guardar.
     * @return true si el traslado se registro con exito, false en caso
     * contrario.
     */
    @Override
    public boolean guardarTraslado(Traslado traslado) {
        return this.crearRepoTraslado().guardarTraslado(traslado);
    }

    /**
     * Llama al método guardarResiduo de la RepoResiduos, recibe de parámetro el
     * mismo residuo , devuelve lo resultante de la operación
     *
     * @param residuo Residuo a guadar.
     * @return true si el residuo se registro con exito, false en caso
     * contrario.
     */
    @Override
    public boolean guardarResiduo(Residuo residuo) {
        return this.crearRepoResiduos().guardarResiduo(residuo);
    }

    /**
     * Llama al método consultarResiduosProductor de la RepoResiduos, recibe el
     * id de la empresa productora, devuelve lo resultante de la operación
     *
     * @param idEmpresaProductora ID de la empresa productora.
     * @return lista de los residuos de la empresa productora.
     */
    @Override
    public List<Residuo> consultarResiduosProductor(ObjectId idEmpresaProductora) {
        return this.crearRepoResiduos().consultarResiduosProductor(idEmpresaProductora);
    }

    /**
     * Llama al método validarResiduo de la RepoResiduos, recibe de parámetro el
     * residuo a ser validado, devuelve lo resultante de la operación
     *
     * @param residuo Residuo a validar.
     * @return Mensaje si el residuo es valido o no.
     */
    @Override
    public String validarResiduo(Residuo residuo) {
        return this.crearRepoResiduos().validarResiduo(residuo);
    }

    /**
     * Llama al método guardarSolicitud de la RepoSolicitudTraslado, recibe la
     * solicitud del traslado, devuelve lo resultante de la operación
     *
     * @param solicitudDeTraslado Solicitud de traslado.
     * @return true si la solicitud de traslado se registro con exito, false en
     * caso contrario
     */
    @Override
    public boolean guardarSolicitud(Solicitud_de_Traslado solicitudDeTraslado) {
        return this.crearRepoSolicitudTraslado().guardarSolicitud(solicitudDeTraslado);
    }

    /**
     * Llama al método consultarSolicitudesNoAtendidas de la
     * RepoSolicitudTraslado, devuelve lo resultante de la operación
     *
     * @return La lista de solicitudes no atendidas.
     */
    @Override
    public List<Solicitud_de_Traslado> consultarSolicitudesNoAtendidas() {
        return this.crearRepoSolicitudTraslado().consultarSolicitudesNoAtendidas();
    }

    /**
     * Llama al método validarSolicitud de la RepoSolicitudTraslado, recibe la
     * solicitud a validar, devuelve lo resultante de la operación
     *
     * @param solicitudDeTraslado Solicitud de traslado a validar.
    * @return El mensaje de la validacion.     */
    @Override
    public String validarSolicitud(Solicitud_de_Traslado solicitudDeTraslado) {
        return this.crearRepoSolicitudTraslado().validarSolicitud(solicitudDeTraslado);
    }

    /**
     * Llama al método actualizarSolicitud de la RepoSolicitudTraslado, recibe
     * el id de la solicitud y el residuo a transportar, devuelve lo resultante
     * de la operación
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado
     * @param residuoTransporte Residuo de transporte a actualizar.
     * @return true si este se actualizo con exito, false en caso contrario.
     */
    @Override
    public boolean actualizarSolicitud(ObjectId _idSolicitudTraslado, Residuo_Transporte residuoTransporte) {
        return this.crearRepoSolicitudTraslado().actualizarSolicitud(_idSolicitudTraslado, residuoTransporte);
    }

    /**
     * Llama al método actualizarSolicitudTraslado de la RepoSolicitudTraslado,
     * recibe el id de la solicitud, devuelve lo resultante de la operación
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    @Override
    public boolean actualizarSolicitudTraslado(ObjectId _idSolicitudTraslado) {
        return this.crearRepoSolicitudTraslado().actualizarSolicitudTraslado(_idSolicitudTraslado);
    }

    /**
     * Llama al método guardarAsignaciónTraslado de la RepoAsignacionTraslado,
     * recibe una lista de los traslados a asignar, devuelve lo resultante de la
     * operación
     *
     * @param list Lista de asignaciones de traslado a registrar.
     * @return true si estas se registraron con exito, false en caso contraio.
     */
    @Override
    public boolean guardarAsignacionTraslado(List<Asignacion_Traslado> list) {
        return this.crearRepoAsignacionTrasladoO().guardarAsignacionTraslado(list);
    }

    /**
     * Llama al método consultarAsignacionTrasladosEmpresaT de la
     * RepoAsignacionTraslado, recibe el id de la empresa de transporte,
     * devuelve lo resultante de la operación
     *
     * @param idEmpresaTransporte ID de la empresa de transporte.
     * @return La lista de las asignaciones de traslado de la empresa de
     * transporte.
     */
    @Override
    public List<Asignacion_Traslado> consultarAsignacionTrasladosEmpresaT(ObjectId idEmpresaTransporte) {
        return this.crearRepoAsignacionTrasladoO().consultarAsignacionTrasladosEmpresaT(idEmpresaTransporte);
    }

    /**
     * Llama al método actualizarAsignacion de la RepoAsignacionTraslado, recibe
     * el id de la asignación y devuelve lo resultante de la operación
     *
     * @param _id ID de la asignacion a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    @Override
    public boolean actualizarAsignacion(ObjectId _id) {
        return this.crearRepoAsignacionTrasladoO().actualizarAsignacion(_id);
    }

}
