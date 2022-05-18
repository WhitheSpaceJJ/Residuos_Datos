
package interfaces;

import entidades.Traslado;
/**
 * Interface RepoTraslado
 * 
 * @author josej
 */
public interface IRepoTraslado {
    
    /**
     * Metodo encargado de registrar un traslado dentro de la coleccion de la
     * base de datos correspondiente.
     *
     * @param traslado Traslado a guardar.
     * @return true si el traslado se registro con exito, false en caso
     * contrario.
     */
    public boolean guardarTraslado(Traslado traslado);
}
