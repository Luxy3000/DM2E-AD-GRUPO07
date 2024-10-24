package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de los clientes.
 * Tiene métodos para realizar operaciones básicas como buscar, guardar,
 * actualizar, borrar, contar o comprobar si existe un cliente.
 * */
public interface CustomerService {
    long count();
    boolean existsById(int id);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer update(Customer customer);
    void deleteById(int id);
}
