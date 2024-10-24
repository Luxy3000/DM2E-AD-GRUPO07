package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de clientes.
 * Tiene métodos para realizar operaciones sobre los clientes como
 * buscar por id del cliente, dar una lista de todos los clientes,
 * agregar, actualizar, borrar, contar o comprobar si existe.
 * */
public interface CustomerDataAccess {
    long count();
    boolean existsById(int id);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer update(Customer customer);
    void deleteById(int id);
}
