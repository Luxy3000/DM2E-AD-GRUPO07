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
    /**
     * Cuenta el número total de pedidos.
     *
     * @return el número total de pedidos en el sistema.
     */
    long count();

    /**
     * Comprueba si existe un pedido por su ID.
     *
     * @param id el ID del pedido a verificar.
     * @return true si el pedido existe, false en caso contrario.
     */
    boolean existsById(int id);

    /**
     * Busca un pedido por su ID.
     *
     * @param id el ID del pedido a buscar.
     * @return un {@link Optional} que contiene el pedido si se encuentra,
     * o vacío si no existe.
     */
    Optional<Order> findById(int id);

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return una lista que contiene todos los pedidos.
     */
    List<Order> findAll();

    /**
     * Guarda un nuevo pedido en el sistema.
     *
     * @param order el pedido a agregar.
     * @return el pedido guardado, que puede incluir un ID asignado.
     */
    Order save(Order order);

    /**
     * Actualiza un pedido existente en el sistema.
     *
     * @param order el pedido con los datos actualizados.
     * @return el pedido actualizado.
     */
    Order update(Order order);

    /**
     * Elimina un pedido del sistema por su ID.
     *
     * @param id el ID del pedido a eliminar.
     */
    void deleteById(int id);
}
