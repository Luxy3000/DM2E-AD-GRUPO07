package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el acceso a datos de pedidos.
 * Tiene métodos para realizar operaciones sobre los pedidos como
 * buscar por id del pedido, dar una lista de todos los pedidos,
 * agregar, actualizar, borrar, contar o comprobar si existe.
 * */
public interface OrderDataAccess {
    long count();
    boolean existsById(int id);
    Optional<Order> findById(int id);
    List<Order> findAll();
    Order save(Order order);
    Order update(Order order);
    void deleteById(int id);
}
