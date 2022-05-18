package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import entidades.Administrador;
import entidades.Productor;
import entidades.Residuo;
import entidades.Transportador;
import entidades.Usuario;
import interfaces.IRepoUsuarios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Clase que representa el repositorio de quimicos.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoUsuarios implements IRepoUsuarios {

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoUsuarios() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de administrador de los usuarios
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Administrador> getCollectionAdministrador() {
        return this.baseDatos.getCollection("Usuarios", Administrador.class);
    }

    /**
     * Obtiene la colección de transportador de los usuarios
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Transportador> getCollectionTransportador() {
        return this.baseDatos.getCollection("Usuarios", Transportador.class);
    }

    /**
     * Obtiene la colección de productor de los usuarios
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Productor> getCollectionProductor() {
        return this.baseDatos.getCollection("Usuarios", Productor.class);
    }

    /**
     * Obtiene la colección de los usuarios
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Usuario> getCollectionUsuario() {
        return this.baseDatos.getCollection("Usuarios", Usuario.class);
    }

    /**
     * Método que se comunica con la base de datos para insertar un
     * administrador en caso de que si exista
     *
     * @param administrador Usuario administrador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    @Override
    public boolean guardarAdministrador(Administrador administrador) {
        MongoCollection<Administrador> coleccion = this.getCollectionAdministrador();
        if (administrador != null) {

            try {
                coleccion.insertOne(administrador);
                return true;
            } catch (MongoException me) {
                return false;
            }
        }
        return false;
    }

    /**
     * Método que se encarga de consultar en la base de datos si existe el
     * administrador con el nombre y contraseña ingresados, de manera que los
     * datos de un administrador coincidan con los ingresados en este método
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usario si este se encontro, null en caso contrario.
     */
    @Override
    public Administrador consultarAdministrador(String nombre, String contraseña) {
        MongoCollection<Administrador> coleccion = this.getCollectionAdministrador();
        Administrador administradorConsulta = new Administrador();
        Bson[] lista = {Filters.eq("nombreUsuario", nombre), Filters.eq("contrasena", contraseña), Filters.eq("tipoUsuario", "Administrador")};
        try {
            administradorConsulta = coleccion.find(Filters.and(lista)).first();
            return administradorConsulta != null ? administradorConsulta : null;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Método que sirve para insertar un productor en caso de que este si exista
     *
     * @param productor Productor a registrar.
     * @return true si el usuario productor se guardo con exito, false en caso
     * contrario
     */
    @Override
    public boolean guardarProductor(Productor productor) {
        MongoCollection<Productor> coleccion = this.getCollectionProductor();
        if (productor != null) {
            try {
                coleccion.insertOne(productor);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

    /**
     * Método que se encarga de consultar en la base de datos si existe el
     * productor con el nombre y contraseña ingresados, de manera que los datos
     * de un productor coincidan con los ingresados en este método
     *
     * @param nombre Nombre del usuario productor a consultar.
     * @param contraseña Contraseña del usuario productor a consultar.
     * @return El usuario productor si este existe, null en caso contrario.
     */
    @Override
    public Productor consultarProductor(String nombre, String contraseña) {

        MongoCollection<Productor> coleccion = this.getCollectionProductor();
        Productor productorConsulta = new Productor();
        Bson[] lista = {Filters.eq("nombreUsuario", nombre), Filters.eq("contrasena", contraseña), Filters.eq("tipoUsuario", "Productor")};
        try {
            productorConsulta = coleccion.find(Filters.and(lista)).first();
            return productorConsulta != null ? productorConsulta : null;
        } catch (MongoException me) {
            return null;
        }
    }

    /**
     * Sirve para guardar en la base de datos el transportador que sea ingresado
     * a este método en caso de que si exista
     *
     * @param transportador Usuario transportador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    @Override
    public boolean guardarTransportador(Transportador transportador) {
        MongoCollection<Transportador> coleccion = this.getCollectionTransportador();
        if (transportador != null) {
            try {
                coleccion.insertOne(transportador);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

    /**
     * Método que se encarga de consultar en la base de datos si existe el
     * transportador con el nombre y contraseña ingresados, de manera que los
     * datos de un transportador coincidan con los ingresados en este método
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usuario transportado que se obtuvo o null si este no se
     * encontro.
     */
    @Override
    public Transportador consultarTransportador(String nombre, String contraseña) {
        MongoCollection<Transportador> coleccion = this.getCollectionTransportador();
        Transportador transportadorConsulta = new Transportador();
        Bson[] lista = {Filters.eq("nombreUsuario", nombre), Filters.eq("contrasena", contraseña), Filters.eq("tipoUsuario", "Transportador")};
        try {
            transportadorConsulta = coleccion.find(Filters.and(lista)).first();
            return transportadorConsulta != null ? transportadorConsulta : null;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Método que sirve para validar un usuario, recibe de parámetro el nombre
     * del usuario, este es consultado en la base de datos, y lo devuelve en
     * caso de que si exista
     *
     * @param nombre Nombre a validar.
     * @return true si existe alguno, false en caso contrario.
     */
    @Override

    public boolean validarUsuario(String nombre) {
        MongoCollection<Usuario> coleccion = this.getCollectionUsuario();
        Usuario usuarioConsulta = new Usuario();
        try {
            usuarioConsulta = coleccion.find(Filters.eq("nombreUsuario", nombre)).first();
            return usuarioConsulta != null;
        } catch (MongoException me) {
            return false;
        }

    }

}
