package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import entidades.Residuo_Transporte;
import entidades.Solicitud_de_Traslado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase que representa el repositorio de Solicitudes de traslado.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoSolicitudTraslado {

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoSolicitudTraslado() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de las solicitudes de traslado de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Solicitud_de_Traslado> getCollectionSolicitudes() {
        return this.baseDatos.getCollection("Solicitud_de_Traslado", Solicitud_de_Traslado.class);
    }

    /**
     * Inserta en la base de datos la solicitud de traslado ingresada como
     * parámetro en caso de que no sea nulo
     *
     * @param solicitudDeTraslado Solicitud de traslado.
     * @return true si la solicitud de traslado se registro con exito, false en
     * caso contrario
     */
    public boolean guardarSolicitud(Solicitud_de_Traslado solicitudDeTraslado) {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        if (solicitudDeTraslado != null) {
            try {

                coleccion.insertOne(solicitudDeTraslado);
                return true;
            } catch (MongoException me) {
                return false;
            }
        }
        return false;
    }

    /**
     * Busca las solicitudes no atendidas en la base de datos a través de una
     * consulta en la cual se busca todas las solicitudes que tengan de estatus
     * "En Proceso" si encuentra algo devuelve la lista, si no encuentra nada
     * devuelve una nueva lista
     *
     * @return La lista de solicitudes no atendidas.
     */
    public List<Solicitud_de_Traslado> consultarSolicitudesNoAtendidas() {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        List<Solicitud_de_Traslado> lista = new ArrayList<>();
        try {
            coleccion.find(Filters.eq("estatus", "En Proceso")).into(lista);
            if (lista.isEmpty()) {
                return new ArrayList<>();
            } else {
                return lista;
            }
        } catch (MongoException me) {
            return null;
        }
    }

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
     * @return El mensaje que se adecue ala validacion,Ya se alcanzo el limite de solicitudes en la fecha seleccionado o 
     * no es posible realizar solicitudes con los mismo datos.
     */
    public String validarSolicitud(Solicitud_de_Traslado solicitudDeTraslado) {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        List<Solicitud_de_Traslado> lista = new ArrayList<>();

        try {
            coleccion.find(Filters.eq("fechaSolicitud", solicitudDeTraslado.getFechaSolicitud())).into(lista);
            if (lista.size() == 5) {
                return "No hay disponibilidad para la fecha seleccionada.";
            } else {
                List<ObjectId> listaID = new ArrayList<>();
                for (int i = 0; i < solicitudDeTraslado.getResiduos().size(); i++) {
                    ObjectId get = solicitudDeTraslado.getResiduos().get(i).getResiduo().getId();
                    listaID.add(get);
                }
                Solicitud_de_Traslado auxiliar = new Solicitud_de_Traslado();
                try {
                    auxiliar = coleccion.find(
                            Filters.and(Filters.eq("fechaSolicitud", solicitudDeTraslado.getFechaSolicitud()), Filters.size("residuos", solicitudDeTraslado.getResiduos().size()),
                                    Filters.in("residuos.residuo._id", listaID))
                    ).first();
                    if (auxiliar != null) {
                        return auxiliar.getFechaSolicitud().equals(solicitudDeTraslado.getFechaSolicitud())
                                && auxiliar.getEmpresaProductora().getId().equals(solicitudDeTraslado.getEmpresaProductora().getId())?"No se pueden hacer solicitudes repetidas.":"";
                    } else {
                        return "";
                    }
                } catch (MongoException me) {
                    return "";
                }
            }
        } catch (MongoException me) {
            return "";
        }
    }

    /**
     * Consulta las solicitudes en la base de datos con la fecha ingresada como
     * parámetro, regresa lo encontrado en caso de que haya encontrado algo, si
     * no, regresa una nueva lista
     *
     * @param fecha Fecha para consular las solictudes.
     * @return La lista de solicitudes por fecha, null en caso contrario de ninguna coincidencia.
     */
    public List<Solicitud_de_Traslado> consultarSolicitudesFecha(Date fecha) {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        List<Solicitud_de_Traslado> lista = new ArrayList<>();

        try {
            coleccion.find(Filters.eq("fechaSolicitud", fecha)).into(lista);
            if (lista.isEmpty()) {
                return new ArrayList<>();
            } else {
                return lista;
            }
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Sirve para actualizar una solicitud, cambia el estatus a asignado, y
     * actualiza el residuo de transporte anterior por el recibido de parámetro
     *
  * @param _idSolicitudTraslado ID de la solicitud de traslado
     * @param residuoTransporte Residuo de transporte a actualizar.
     * @return true si este se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitud(ObjectId _idSolicitudTraslado, Residuo_Transporte residuoTransporte) {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        try {
            coleccion.updateOne(new Document("_id", _idSolicitudTraslado),
                    Updates.set("residuos.$[elem].estatus", "Asignado"),
                    new UpdateOptions().arrayFilters(Arrays.asList(new Document("elem.idResiduoTransporte", residuoTransporte.getIdResiduoTransporte()))));
            return true;
        } catch (MongoException me) {
            return false;
        }
    }

    /**
     * Sirve para actualizar una solicitud de traslado, actualiza la solicitud
     * que tiene el id ingresado y cambia su estatus a "Concluida"
     *
    * @param _idSolicitudTraslado ID de la solicitud de traslado a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarSolicitudTraslado(ObjectId _idSolicitudTraslado) {
        MongoCollection<Solicitud_de_Traslado> coleccion = this.getCollectionSolicitudes();
        try {
            coleccion.updateOne(Filters.eq("_id", _idSolicitudTraslado), new Document("$set", new Document("estatus", "Concluida")));
            return true;
        } catch (MongoException me) {
            return false;
        }
    }
}
