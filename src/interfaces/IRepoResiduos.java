
package interfaces;

import entidades.Residuo;
import java.util.List;
import org.bson.types.ObjectId;
/**
 * Interface de RepoResiduos
 * @author josej
 */
public interface IRepoResiduos {
    
    /**
     * Consulta en la base de datos el residuo que coincida con el id ingresado
     * como parámetro
     *
     * @param _id EL ide del residuo a consultar.
     * @return El residuo si la busqueda tuvo exito, null en caso contrario.
     */ 
    public Residuo getResiduo(ObjectId _id) ;

    /**
     * Inserta en la base de datos el residuo ingresado de parámetro en caso de
     * que no sea nulo
     *
     * @param residuo Residuo a guadar.
     * @return true si el residuo se registro con exito, false en caso
     * contrario.
     */ 
    public boolean guardarResiduo(Residuo residuo) ;

    /**
     * Consulta en la base de datos todos los residuos que se encuentren con el
     * id de la empresa productora
     *
     * @param idEmpresaProductora ID de la empresa productora.
     * @return lista de los residuos de la empresa productora.
     */
    public List<Residuo> consultarResiduosProductor(ObjectId idEmpresaProductora);

    /**
     * Recibe de parámetro un objeto residuo, con este mismo se realiza una
     * consulta por id, si no encuentra nada entonces se hace una consulta
     * utilizando su nombre como expresion regular para ver si coincide con
     * algun otro nombre de residuo, si no coincide con ninguno entonces
     * consulta el tamaño de los quimicos que contiene, si pasa todos los
     * filtros entonces regresará falso.
     *
     * @param residuo Residuo a validar.
     * @return true si cumple con lo necesario, false en caso contrario
     */ 
    public String validarResiduo(Residuo residuo);
}
