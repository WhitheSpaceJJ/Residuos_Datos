package implementaciones;

import interfaces.IConexionBD;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase que representa una conexion entre la base de datos mongo y el software.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class ConexionBD implements IConexionBD {

    private static final String HOST = "localhost";
    private static final int PUERTO = 27017;
    private static final String BASE_DATOS = "Residuos_DB";
    private static MongoDatabase baseDatosD;

    /**
     * Esta es la clase que nos permitirá crear una conexión a la base de datos
     * que para este programa estaremos usando la base de datos mongodb, el
     * código en si es la estructura necesaria para que la conexión sirva en
     * este tipo de base de datos.
     *
     * @return La conexion a la base datos.
     */
    @Override
    public MongoDatabase crearConexion() {
        if (baseDatosD != null) {
            return baseDatosD;
        } else {
            try {
                //CONFIGURACIÓN PARA QUE MONGODRIVE REALICE EL MAPEO DE POJOS AUMATICAMENTE
                CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

                CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

                ConnectionString cadenaConexion = new ConnectionString("mongodb://" + HOST + "/" + PUERTO);

                MongoClientSettings clientsSettings = MongoClientSettings.builder()
                        .applyConnectionString(cadenaConexion)
                        .codecRegistry(codecRegistry)
                        .build();

                MongoClient clienteMongo = MongoClients.create(clientsSettings);

                MongoDatabase baseDatos = clienteMongo.getDatabase(BASE_DATOS);

                return baseDatos;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
    }
//

    /**
     * Funciona igual que el método crearConexion, con la diferencia de que este
     * será un método estático para que pueda ser accedido desde cualquier otra
     * clase
     *
     * @return La conexion a la base datos.
     */
    public static MongoDatabase getBaseDatos() {
        if (baseDatosD != null) {
            return baseDatosD;
        } else {
            try {
                //CONFIGURACIÓN PARA QUE MONGODRIVE REALICE EL MAPEO DE POJOS AUMATICAMENTE
                CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

                CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

                ConnectionString cadenaConexion = new ConnectionString("mongodb://" + HOST + "/" + PUERTO);

                MongoClientSettings clientsSettings = MongoClientSettings.builder()
                        .applyConnectionString(cadenaConexion)
                        .codecRegistry(codecRegistry)
                        .build();

                MongoClient clienteMongo = MongoClients.create(clientsSettings);

                MongoDatabase baseDatos = clienteMongo.getDatabase(BASE_DATOS);
                baseDatosD = baseDatos;
                return baseDatosD;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
    }
}
