package implementaciones;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import entidades.Residuo;
import interfaces.IRepoResiduos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Clase que representa el repositorio de quimicos.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoResiduos implements IRepoResiduos {

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoResiduos() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de los residuos de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Residuo> getCollectionResiduos() {
        return this.baseDatos.getCollection("Residuos", Residuo.class);
    }

    /**
     * Consulta en la base de datos el residuo que coincida con el id ingresado
     * como parámetro
     *
     * @param _id EL ide del residuo a consultar.
     * @return El residuo si la busqueda tuvo exito, null en caso contrario.
     */
    @Override
    public Residuo getResiduo(ObjectId _id) {
        MongoCollection<Residuo> coleccion = this.getCollectionResiduos();
        Residuo residuoConsulta = new Residuo();
        try {

            residuoConsulta = coleccion.find(Filters.eq("_id", _id)).first();
            return residuoConsulta != null ? residuoConsulta : null;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Inserta en la base de datos el residuo ingresado de parámetro en caso de
     * que no sea nulo
     *
     * @param residuo Residuo a guadar.
     * @return true si el residuo se registro con exito, false en caso
     * contrario.
     */
    @Override
    public boolean guardarResiduo(Residuo residuo) {
        MongoCollection<Residuo> coleccion = this.getCollectionResiduos();
        if (residuo != null) {
            try {
                coleccion.insertOne(residuo);
                return true;
            } catch (MongoException me) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Consulta en la base de datos todos los residuos que se encuentren con el
     * id de la empresa productora
     *
     * @param idEmpresaProductora ID de la empresa productora.
     * @return lista de los residuos de la empresa productora.
     */
    @Override
    public List<Residuo> consultarResiduosProductor(ObjectId idEmpresaProductora) {
        MongoCollection<Residuo> coleccion = this.getCollectionResiduos();
        List<Residuo> lista = new ArrayList<>();

        try {
            coleccion.find(Filters.eq("idEmpresaProductora", idEmpresaProductora)).into(lista);
            return lista.isEmpty() ? new ArrayList<>() : lista;
        } catch (MongoException me) {
            return null;
        }

    }

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
    @Override
    public String validarResiduo(Residuo residuo) {
        MongoCollection<Residuo> coleccion = this.getCollectionResiduos();
        Residuo residuoConsulta = null;

        try {
            residuoConsulta = coleccion.find(Filters.eq("idResiduo", residuo.getIdResiduo())).first();
            if (residuoConsulta != null) {
                return "Ya existe un residuo con el mismo código.";
            } else {
                Residuo auxiliar = null;

                auxiliar = coleccion.find(Filters.regex("nombre", ".*" + residuo.getNombre().trim() + ".*", "i")).first();
                if (auxiliar != null) {
                    return "Ya existe un residuo con el mismo nombre.";
                } else {

                    List<Residuo> lista = new ArrayList<>();

                    int tamano = residuo.getQuimicos().size();
                    coleccion.find(
                            Filters.and(Filters.size("quimicos", tamano), Filters.in("quimicos", residuo.getQuimicos()))
                    ).into(lista);

                    return lista.isEmpty() ? "" : "Ya existe un residuo con los mismos químicos.";
                }

            }
        } catch (MongoException me) {
            return "";
        }

    }
}
