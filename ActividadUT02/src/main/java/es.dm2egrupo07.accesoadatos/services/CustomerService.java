package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de los clientes.
 * Tiene métodos para realizar operaciones básicas como buscar, guardar, actualizar, borrar, contar o comprobar si
 * existe un cliente.
 */
public interface CustomerService {
    /**
     * Obtiene el número total de columnas de la tabla Customers.
     * @return número de columnas
     */
    long count();

    /**
     * Comprueba si existe un cliente por su ID.
     * @param id
     * @return true si existe y false si no existe
     */
    boolean existsById(int id);

    /**
     * Busca un cliente por su ID.
     * @param id
     * @return el cliente con el ID correspondiente
     */
    Optional<Customer> findById(int id);

    /**
     * Busca todos los clientes.
     * @return una lista con los clientes
     */
    List<Customer> findAll();

    /**
     * Crea y/o guarda un cliente nuevo.
     * @param customer
     * @return el cliente nuevo salvado
     */
    Customer save(Customer customer);

    /**
     * Actualiza un cliente.
     * @return el cliente actualizado
     */
    Customer update(Customer customer);

    /**
     * Elimina un cliente por su ID.
     * @param id
     */
    void deleteById(int id);
}
