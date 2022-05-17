package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidades.Traslado;

/**
 * Clase que representa el repositorio de traslados.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoTraslado {

    private MongoDatabase baseDatos;

    /**
     * Constructor por defecto del repositorio de traslados.
     */
    public RepoTraslado() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de traslados de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Traslado> getCollectionTraslado() {
        return this.baseDatos.getCollection("Traslado", Traslado.class);
    }

    /**
     * Metodo encargado de registrar un traslado dentro de la coleccion de la
     * base de datos correspondiente.
     *
     * @param traslado Traslado a guardar.
     * @return true si el traslado se registro con exito, false en caso
     * contrario.
     */
    public boolean guardarTraslado(Traslado traslado) {
        MongoCollection<Traslado> coleccion = this.getCollectionTraslado();
        if (traslado != null) {
            try {
                coleccion.insertOne(traslado);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

}
