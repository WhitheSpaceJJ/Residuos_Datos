package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Quimico;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que representa el repositorio de quimicos.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoQuimicos {

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoQuimicos() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de los químicos de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Quimico> getCollectionQuimicos() {
        return this.baseDatos.getCollection("Quimicos", Quimico.class);
    }

    /**
     * Sirve para consultar en la base de datos todos los químicos existentes y
     * los devuelve en caso de que si existan químicos en la base de datos
     *
     * @return Lista de quimicos del sistema.
     */
    public List<Quimico> getQuimicos() {
        MongoCollection<Quimico> coleccion = this.getCollectionQuimicos();
        List<Quimico> lista = new ArrayList<>();
        try {
            coleccion.find().into(lista);
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
     * Inserta en la base de datos el químico ingresado de parámetro en caso de
     * que este no sea nulo
     *
      * @param quimico Quimico a guardar.
     * @return true si el quimico se guardo, false en caso contrario.
     */
    public boolean guardarQuimico(Quimico quimico) {
        MongoCollection<Quimico> coleccion = this.getCollectionQuimicos();
        if (quimico != null) {

            try {

                coleccion.insertOne(quimico);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

}
