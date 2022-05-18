/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Empresa;
import entidades.Productora;
import entidades.Transporte;
import entidades.Vehiculo;
import interfaces.IRepoEmpresas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase que representa el repositorio de empresas.
 *
 * @author Equipo 1 Jose,Abraham y Oroz
 */
public class RepoEmpresas implements IRepoEmpresas{

    private MongoDatabase baseDatos;

    /**
     * Constructor vacio que inicializa la base de datos
     */
    public RepoEmpresas() {
        this.baseDatos = ConexionBD.getBaseDatos();
    }

    /**
     * Obtiene la colección de las empresas de transporte de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Transporte> getCollectionEmpresasTransporte() {
        return this.baseDatos.getCollection("Empresas", Transporte.class);
    }

    /**
     * Obtiene la colección de las empresas productoras de la base de datos
     *
     * @return La coleccion correspondiente.
     */
    private MongoCollection<Productora> getCollectionEmpresasProductora() {
        return this.baseDatos.getCollection("Empresas", Productora.class);
    }

    /**
     * Se encarga de buscar en la base de datos todas las empresas que sean del
     * tipo empresa productora y los devuelve en caso de que los encuentre
     *
     * @return Lista de la empresa productoras disponibles.
     */ 
    @Override
    public List<Productora> getEmpresasProductoras() {
        MongoCollection<Productora> coleccion = this.getCollectionEmpresasProductora();
        List<Productora> lista = new ArrayList<>();
        try {
            coleccion.find(Filters.eq("tipoEmpresa", "Empresa Productora")).into(lista);
            return lista.isEmpty() ? new ArrayList<>() : lista;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Se encarga de buscar en la base de datos todas las empresas que sean del
     * tipo empresa de transporte y los devuelve en caso de que los encuentre
     *
     * @return Lista de las empresas de transporte disponibles.
     */ @Override
    public List<Transporte> getEmpresasTransportes() {
        MongoCollection<Transporte> coleccion = this.getCollectionEmpresasTransporte();
        List<Transporte> lista = new ArrayList<>();

        try {
            coleccion.find(Filters.eq("tipoEmpresa", "Empresa Transporte")).into(lista);
            return lista.isEmpty() ? new ArrayList<>() : lista;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Se encarga de consultar en la base de datos a la empresa productora por
     * medio de su id y la devuelve en caso de que la encuentre
     *
     * @param _id El id de la empresa productora.
     * @return La empresa transportadora obtenida.
     */ @Override
    public Productora getEmpresaProductora(ObjectId _id) {
        MongoCollection<Productora> coleccion = this.getCollectionEmpresasProductora();
        Productora productoraConsulta = new Productora();
        try {
            productoraConsulta = coleccion.find(Filters.eq("_id", _id)).first();
            return productoraConsulta != null ? productoraConsulta : null;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Se encarga de consultar en la base de datos a la empresa de transporte
     * por medio de su id y la devuelve en caso de que la encuentre
     *
     * @param _id El id de la empresa de transporte.
     * @return La empresa de transporte obtenida.
     */ @Override
    public Transporte getEmpresaTransporte(ObjectId _id) {
        MongoCollection<Transporte> coleccion = this.getCollectionEmpresasTransporte();
        Transporte transporteConsulta = new Transporte();
        try {
            transporteConsulta = coleccion.find(Filters.eq("_id", _id)).first();
            return transporteConsulta != null ? transporteConsulta : null;
        } catch (MongoException me) {
            return null;
        }

    }

    /**
     * Se encarga de insertar en la base de datos la empresa productora que
     * recibe de parámetro, la inserta y devuelve verdadero en caso de que
     * exista, si no devuelve falso y no inserta nada
     *
     * @param productora La empresa productora a guardar.
     * @return true si la empresa empresa productora se guardo con exito, false
     * en caso contrario.
     */ @Override
    public boolean guardarEmpresaProductora(Productora productora) {
        MongoCollection<Productora> coleccion = this.getCollectionEmpresasProductora();
        if (productora != null) {
            try {
                coleccion.insertOne(productora);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

    /**
     * Se encarga de insertar en la base de datos la empresa de transporte que
     * recibe de parámetro, la inserta y devuelve verdadero en caso de que
     * exista, si no devuelve falso y no inserta nada
     *
        * @param transporte La empresa de transporte a registrar.
     * @return true si la empresa de transporte registrada se guardo con exito,
     * false en caso contrario.
     */ @Override
    public boolean guardarEmpresaTransporte(Transporte transporte) {
        MongoCollection<Transporte> coleccion = this.getCollectionEmpresasTransporte();
        if (transporte != null) {
            try {
                coleccion.insertOne(transporte);
                return true;
            } catch (MongoException me) {
                return false;
            }

        }
        return false;
    }

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
     */ @Override
    public boolean guardarVehiculo(Vehiculo vehiculo, Transporte transporteEmpresa) {
        MongoCollection<Transporte> coleccion = this.getCollectionEmpresasTransporte();
        if (vehiculo != null) {
            try {
                Transporte transporte = null;
                transporte = coleccion.find(Filters.eq("vehiculos.matricula", vehiculo.getMatricula())).first();
                if (vehiculo != null && transporte == null) {
                    if (transporteEmpresa.getVehiculos() == null) {
                        transporteEmpresa.setVehiculos(Arrays.asList(vehiculo));
                    }
                    BasicDBObject f = new BasicDBObject("vehiculos", vehiculo);
                    coleccion.updateOne(Filters.eq("_id", transporteEmpresa.getId()), new Document("$push", f));
                    return true;
                } else {
                    return false;
                }
            } catch (MongoException me) {
                return false;
            }
        }
        return false;
    }

}
