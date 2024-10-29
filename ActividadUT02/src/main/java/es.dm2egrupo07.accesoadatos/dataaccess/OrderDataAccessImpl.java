package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Order;

import java.sql.*;
import java.util.*;

/**
 * Implementación de acceso a datos para la entidad Order.
 * Proporciona métodos para realizar operaciones CRUD sobre pedidos en la base de datos.
 */
public class OrderDataAccessImpl implements OrderDataAccess {

    /**
     * Cuenta el número total de pedidos en la base de datos.
     *
     * @return el número total de pedidos.
     */
    @Override
    public long count() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from orders");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar productos", e);
        }
        return 0;
    }

    /**
     * Verifica si existe un pedido con el identificador especificado.
     *
     * @param id el identificador del pedido a verificar.
     * @return true si existe un pedido con el id dado, false en caso contrario.
     */
    @Override
    public boolean existsById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select count(*) from orders where orderNumber = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de producto", e);
        }
        return false;
    }

    /**
     * Busca un pedido en la base de datos por su identificador.
     *
     * @param id el identificador del pedido a buscar.
     * @return un objeto Optional que contiene el pedido si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Order> findById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from orders where orderNumber = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRsOrders(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el producto", e);
        }
        return Optional.empty();
    }

    /**
     * Recupera todos los pedidos de la base de datos.
     *
     * @return una lista de todos los pedidos.
     */
    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from orders")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapRsOrders(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar orders", e);
        }
        return orders;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param order el pedido a guardar.
     * @return el pedido guardado, o null si no se realiza la operación.
     */
    @Override
    public Order save(Order order) {
        return null;
    }

    /**
     * Actualiza un pedido existente en la base de datos.
     *
     * @param order el pedido a actualizar.
     * @return el pedido actualizado, o null si no se realiza la operación.
     */
    @Override
    public Order update(Order order) {
        return null;
    }

    /**
     * Elimina un pedido de la base de datos por su identificador.
     *
     * @param id el identificador del pedido a eliminar.
     */
    @Override
    public void deleteById(int id) {

    }

    /**
     * Mapea un ResultSet a un objeto Order.
     *
     * @param rs el ResultSet que contiene los datos del pedido.
     * @return un objeto Order creado a partir de los datos del ResultSet.
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet.
     */
    private Order mapRsOrders(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("orderNumber"),
                rs.getDate("orderDate"),
                rs.getDate("requiredDate"),
                rs.getDate("shippedDate"),
                rs.getString("status"),
                rs.getString("comments"),
                rs.getInt("customerNuber")

        );
    }
}
