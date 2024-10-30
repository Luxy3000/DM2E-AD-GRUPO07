package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de clientes.
 * Tiene métodos para realizar operaciones sobre los clientes como buscar por id del cliente, dar una lista de todos los
 * clientes, agregar, actualizar, borrar, contar o comprobar si existe.
 */
public interface CustomerDataAccess {
    /**
     * Método para contar las columnas de la tabla de clientes.
     * @return el número de columnas
     */
    long count();

    /**
     * Método para comprobar si existe un cliente por su ID.
     * @param id
     * @return true si existe y false si no existe
     */
    boolean existsById(int id);

    /**
     * Método para buscar un cliente por su ID.
     * @param id
     * @return el cliente con el ID correspondiente
     */
    Optional<Customer> findById(int id);

    /**
     * Método para buscar todos los clientes de la base de datos.
     * @return una lista con todos los clientes
     */
    List<Customer> findAll();

    /**
     * Método para crear y/o guardar un cliente nuevo.
     * @param customer
     */
    Customer save(Customer customer);

    /**
     * Método para actualizar un cliente.
     * @param customer
     */
    Customer update(Customer customer);

    /**
     * Método para eliminar un cliente por su ID.
     * @param id
     */
    void deleteById(int id);
}
