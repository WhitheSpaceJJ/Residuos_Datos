package implementaciones;

import com.mongodb.client.MongoDatabase;
/**
 * Interfaz de la conexion.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public interface IConexionBD {

    /**
     * Interface de conexion para la base de datos
     *
     * @return
     */
    MongoDatabase crearConexion();
}
