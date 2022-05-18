
package interfaces;

import entidades.Quimico;
import java.util.List;

/**
 * Interfaz de RepoQuimicos
 * 
 * @author josej
 */
public interface IRepoQuimicos {
      /**
     * Sirve para consultar en la base de datos todos los químicos existentes y
     * los devuelve en caso de que si existan químicos en la base de datos
     *
     * @return Lista de quimicos del sistema.
     */
    public List<Quimico> getQuimicos() ;

    /**
     * Inserta en la base de datos el químico ingresado de parámetro en caso de
     * que este no sea nulo
     *
      * @param quimico Quimico a guardar.
     * @return true si el quimico se guardo, false en caso contrario.
     */
    public boolean guardarQuimico(Quimico quimico);
}
