package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dataaccess.OrderDataAccess;
import es.dm2egrupo07.accesoadatos.dto.CreateOrderDto;
import es.dm2egrupo07.accesoadatos.entities.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderDataAccess orderDataAccess;

    public OrderServiceImpl(OrderDataAccess orderDataAccess) {
        this.orderDataAccess = orderDataAccess;
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para contar las
     * columnas de la tabla Order.
     * @return número de columnas totales de la tabla
     */
    @Override
    public long count() {
        return orderDataAccess.count();
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para comprobar
     * si existe un Order por su ID.
     * @param id
     * @return true si existe y false si no existe
     */
    @Override
    public boolean existsById(int id) {
        return orderDataAccess.existsById(id);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para buscar un
     * Order por su ID.
     * @param id
     * @return el Order correspondiente al ID dado
     */
    @Override
    public Optional<Order> findById(int id) {
        return orderDataAccess.findById(id);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para buscar
     * todos los Order.
     * @return una lista con todos los Order
     */
    @Override
    public List<Order> findAll() {
        return orderDataAccess.findAll();
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para crear un
     * Order nuevo.
     * @param orderDto
     */
    @Override
    public void create(CreateOrderDto orderDto) { orderDataAccess.create(orderDto); }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para actualizar
     * un Order.
     * @param order
     * @return el Customer actualizado
     */
    @Override
    public Order update(Order order) {
        return orderDataAccess.update(order);
    }

    /**
     * Sobreescribe el método de la interfaz para delegar su funcionamiento a la capa de acceso a datos para eliminar un
     * Order por su ID.
     * @param id
     */
    @Override
    public void deleteById(int id) {
        orderDataAccess.deleteById(id);
    }
}
