package interfaces;

import entidades.Administrador;
import entidades.Productor;
import entidades.Transportador;
/**
 * Interface RepoUsuarios.
 * @author josej
 */
public interface IRepoUsuarios {

    /**
     * Método que se comunica con la base de datos para insertar un
     * administrador en caso de que si exista
     *
     * @param administrador Usuario administrador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    public boolean guardarAdministrador(Administrador administrador);

    /**
     * Método que se encarga de consultar en la base de datos si existe el
     * administrador con el nombre y contraseña ingresados, de manera que los
     * datos de un administrador coincidan con los ingresados en este método
     *
     * @param nombre Nombre del usuario a consultar.
     * @param contraseña Contraseña del usuario.
     * @return El usario si este se encontro, null en caso contrario.
     */
    public Administrador consultarAdministrador(String nombre, String contraseña);

    /**
     * Método que sirve para insertar un productor en caso de que este si exista
     *
     * @param productor Productor a registrar.
     * @return true si el usuario productor se guardo con exito, false en caso
     * contrario
     */
    public boolean guardarProductor(Productor productor);

    /**
     * Método que se encarga de consultar en la base de datos si existe el
     * productor con el nombre y contraseña ingresados, de manera que los datos
     * de un productor coincidan con los ingresados en este método
     *
     * @param nombre Nombre del usuario productor a consultar.
     * @param contraseña Contraseña del usuario productor a consultar.
     * @return El usuario productor si este existe, null en caso contrario.
     */
    public Productor consultarProductor(String nombre, String contraseña);

    /**
     * Sirve para guardar en la base de datos el transportador que sea ingresado
     * a este método en caso de que si exista
     *
     * @param transportador Usuario transportador a guardar.
     * @return true si este se guardo con exito, false en caso contrario.
     */
    public boolean guardarTransportador(Transportador transportador);

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
    public Transportador consultarTransportador(String nombre, String contraseña);

    /**
     * Método que sirve para validar un usuario, recibe de parámetro el nombre
     * del usuario, este es consultado en la base de datos, y lo devuelve en
     * caso de que si exista
     *
     * @param nombre Nombre a validar.
     * @return true si existe alguno, false en caso contrario.
     */
    public boolean validarUsuario(String nombre);
}
