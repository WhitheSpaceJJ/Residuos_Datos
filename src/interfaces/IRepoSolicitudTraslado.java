
package interfaces;

import entidades.Residuo_Transporte;
import entidades.Solicitud_de_Traslado;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
/**
 * Interface de RepoSolicitudTraslado
 * @author josej
 */
public interface IRepoSolicitudTraslado {
      /**
     * Inserta en la base de datos la solicitud de traslado ingresada como
     * parámetro en caso de que no sea nulo
     *
     * @param solicitudDeTraslado Solicitud de traslado.
     * @return true si la solicitud de traslado se registro con exito, false en
     * caso contrario
     */
    public boolean guardarSolicitud(Solicitud_de_Traslado solicitudDeTraslado) ;

    /**
     * Busca las solicitudes no atendidas en la base de datos a través de una
     * consulta en la cual se busca todas las solicitudes que tengan de estatus
     * "En Proceso" si encuentra algo devuelve la lista, si no encuentra nada
     * devuelve una nueva lista
     *
     * @return La lista de solicitudes no atendidas.
     */
    public List<Solicitud_de_Traslado> consultarSolicitudesNoAtendidas() ;

    /**
     * Consulta primeramente que exista una solicitud con la misma fecha a la
     * que fue ingresada, si no encuentra nada entonces crea una lista de Id's y
     * la llena con los id de los residuos, despues realiza una consulta
     * comparando que la fecha de solicitud y el tamaño de los residuos
     * coincidan, si encuentra algo entonces regresa la fecha encontrada que
     * coincida con la fecha de la solicitud ingresado, al igual que regresa el
     * id de la empresa productora encontrada que coincide con el id de la
     * empresa productora de la solicitud ingresada
     *
     * @param solicitudDeTraslado Solicitud de traslado a validar.
     * @return El mensaje que se adecue ala validacion,Ya se alcanzo el limite
     * de solicitudes en la fecha seleccionado o no es posible realizar
     * solicitudes con los mismo datos.
     */
    public String validarSolicitud(Solicitud_de_Traslado solicitudDeTraslado);
    /**
     * Consulta las solicitudes en la base de datos con la fecha ingresada como
     * parámetro, regresa lo encontrado en caso de que haya encontrado algo, si
     * no, regresa una nueva lista
     *
     * @param fecha Fecha para consular las solictudes.
     * @return La lista de solicitudes por fecha, null en caso contrario de
     * ninguna coincidencia.
     */
    public List<Solicitud_de_Traslado> consultarSolicitudesFecha(Date fecha) ;

    /**
     * Sirve para actualizar una solicitud, cambia el estatus a asignado, y
     * actualiza el residuo de transporte anterior por el recibido de parámetro
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado
     * @param residuoTransporte Residuo de transporte a actualizar.
     * @return true si este se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitud(ObjectId _idSolicitudTraslado, Residuo_Transporte residuoTransporte) ;

    /**
     * Sirve para actualizar una solicitud de traslado, actualiza la solicitud
     * que tiene el id ingresado y cambia su estatus a "Concluida"
     *
     * @param _idSolicitudTraslado ID de la solicitud de traslado a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitudTraslado(ObjectId _idSolicitudTraslado) ;
}
