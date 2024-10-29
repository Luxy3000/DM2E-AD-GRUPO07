package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dto.CreateOrderDto;
import es.dm2egrupo07.accesoadatos.entities.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios de los pedidos.
 * Tiene métodos para realizar operaciones básicas como buscar, guardar,
 * actualizar, borrar, contar o comprobar si existe un pedido.
 * */
public interface OrderService {
    long count();
    boolean existsById(int id);
    Optional<Order> findById(int id);
    List<Order> findAll();
    void create (CreateOrderDto orderDto);
    Order update(Order order);
    void deleteById(int id);
}
