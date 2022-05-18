
package interfaces;

import entidades.Asignacion_Traslado;
import java.util.List;
import org.bson.types.ObjectId;
/**
 * Interfaz de RepoAsignacionTraslado
 * @author josej
 */
public interface IRepoAsignacionTraslado {
    
    /**
     * Método que sirve para guardar en la base de datos la asignación de
     * traslado, recibe la lista de asignaciones y en caso de que la lista no
     * esté vacia entonces insertara todos los elementos de la lista en la
     * coleccion
     *
    * @param list Lista de asignaciones de traslado a registrar.
     * @return true si estas se registraron con exito, false en caso contraio.
     */
    public boolean guardarAsignacionTraslado(List<Asignacion_Traslado> list);

    /**
     * Método que sirve para consultar las asignaciones de traslado de empresas
     * de transporte, recibe el id de la empresa y busca en la base de datos que
     * ese id cuente con un estatus de "En Proceso"
     *
       * @param idEmpresaTransporte ID de la empresa de transporte.
     * @return La lista de las asignaciones de traslado de la empresa de transporte.
     */
    public List<Asignacion_Traslado> consultarAsignacionTrasladosEmpresaT(ObjectId idEmpresaTransporte);

    /**
     * Método que sirve para actualizar una asignación en la base de datos,
     * recibe el id y entonces intenta actualizar el estatus de la asignación a
     * concluida.
     *
       * @param _id ID de la asignacion a actualizar.
     * @return true si esta se actualizo con exito, false en caso contrario.
     */
    public boolean actualizarAsignacion(ObjectId _id);
}
