package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Asignacion_Traslado;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase que representa e repositorio de asignaciones de traslado.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoAsignacionTraslado {

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoAsignacionTraslado() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de la Asignación Traslado de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Asignacion_Traslado> getCollectionAsignacionTraslado() {
        return this.baseDatos.getCollection("Asignaciones_Traslado", Asignacion_Traslado.class);
    }

    /**
     * Método que sirve para guardar en la base de datos la asignación de
     * traslado, recibe la lista de asignaciones y en caso de que la lista no
     * esté vacia entonces insertara todos los elementos de la lista en la
     * coleccion
     *
    * @param list Lista de asignaciones de traslado a registrar.
     * @return true si estas se registraron con exito, false en caso contraio.
     */
    public boolean guardarAsignacionTraslado(List<Asignacion_Traslado> list) {
        MongoCollection<Asignacion_Traslado> coleccion = this.getCollectionAsignacionTraslado();
        if (!list.isEmpty()) {
            try {
                coleccion.insertMany(list);
                return true;
            } catch (MongoException me) {
                return false;
            }
        }
        return false;
    }

    /**
     * Método que sirve para consultar las asignaciones de traslado de empresas
     * de transporte, recibe el id de la empresa y busca en la base de datos que
     * ese id cuente con un estatus de "En Proceso"
     *
       * @param idEmpresaTransporte ID de la empresa de transporte.
     * @return La lista de las asignaciones de traslado de la empresa de transporte.
     */
    public List<Asignacion_Traslado> consultarAsignacionTrasladosEmpresaT(ObjectId idEmpresaTransporte) {
        MongoCollection<Asignacion_Traslado> coleccion = this.getCollectionAsignacionTraslado();
        List<Asignacion_Traslado> lista = new ArrayList<>();
        try {
            coleccion.find(Filters.and(Filters.eq("idEmpresaTransporte", idEmpresaTransporte),
                    Filters.eq("estatus", "En Proceso"))
            ).into(lista);
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
     * Método que sirve para actualizar una asignación en la base de datos,
     * recibe el id y entonces intenta actualizar el estatus de la asignación a
     * concluida.
     *
       * @param _id ID de la asignacion a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarAsignacion(ObjectId _id) {
        MongoCollection<Asignacion_Traslado> coleccion = this.getCollectionAsignacionTraslado();
        try {
            coleccion.updateOne(Filters.eq("_id", _id), new Document("$set", new Document("estatus", "Concluida")));
            return true;
        } catch (MongoException me) {
            return false;
        }
    }
}
