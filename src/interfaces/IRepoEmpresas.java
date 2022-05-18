
package interfaces;

import entidades.Productora;
import entidades.Transporte;
import entidades.Vehiculo;
import java.util.List;
import org.bson.types.ObjectId;
/**
 * Interfaz de RepoEmpresas
 * @author josej
 */
public interface IRepoEmpresas {
    
    /**
     * Se encarga de buscar en la base de datos todas las empresas que sean del
     * tipo empresa productora y los devuelve en caso de que los encuentre
     *
     * @return Lista de la empresa productoras disponibles.
     */
    public List<Productora> getEmpresasProductoras();

    /**
     * Se encarga de buscar en la base de datos todas las empresas que sean del
     * tipo empresa de transporte y los devuelve en caso de que los encuentre
     *
     * @return Lista de las empresas de transporte disponibles.
     */
    public List<Transporte> getEmpresasTransportes() ;

    /**
     * Se encarga de consultar en la base de datos a la empresa productora por
     * medio de su id y la devuelve en caso de que la encuentre
     *
     * @param _id El id de la empresa productora.
     * @return La empresa transportadora obtenida.
     */ 
    public Productora getEmpresaProductora(ObjectId _id) ;

    /**
     * Se encarga de consultar en la base de datos a la empresa de transporte
     * por medio de su id y la devuelve en caso de que la encuentre
     *
     * @param _id El id de la empresa de transporte.
     * @return La empresa de transporte obtenida.
     */
    public Transporte getEmpresaTransporte(ObjectId _id) ;

    /**
     * Se encarga de insertar en la base de datos la empresa productora que
     * recibe de parámetro, la inserta y devuelve verdadero en caso de que
     * exista, si no devuelve falso y no inserta nada
     *
     * @param productora La empresa productora a guardar.
     * @return true si la empresa empresa productora se guardo con exito, false
     * en caso contrario.
     */
    public boolean guardarEmpresaProductora(Productora productora);

    /**
     * Se encarga de insertar en la base de datos la empresa de transporte que
     * recibe de parámetro, la inserta y devuelve verdadero en caso de que
     * exista, si no devuelve falso y no inserta nada
     *
        * @param transporte La empresa de transporte a registrar.
     * @return true si la empresa de transporte registrada se guardo con exito,
     * false en caso contrario.
     */
    public boolean guardarEmpresaTransporte(Transporte transporte);

    /**
     * Este método se encarga de insertar en la base de datos un vehiculo junto
     * con la empresa de transporte que lo tiene, primero realiza una consulta
     * para comprobar si existe el vehiculo, si no encuentra nada checa si la
     * empresa tiene ese vehiculo, si no lo tiene entonces le asigna el vehiculo
     * a la empresa, despues actualiza la base de datos para asignar el nuevo
     * vehiculo
     *
     * @param vehiculo Vehiculo a guardar.
     * @param transporteEmpresa Empresa transportadora a guardar el vehiculo.
     * @return true si el vehiculo se guardo con exito, false en caso contrario.
     */
    public boolean guardarVehiculo(Vehiculo vehiculo, Transporte transporteEmpresa);
}
