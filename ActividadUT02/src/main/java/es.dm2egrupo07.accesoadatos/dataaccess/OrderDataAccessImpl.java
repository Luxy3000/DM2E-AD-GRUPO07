package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.dto.CreateOrderDto;
import es.dm2egrupo07.accesoadatos.entities.Order;

import java.sql.*;
import java.sql.Date;
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
    public void create(CreateOrderDto orderDto) {
        String orderSql = "INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String orderDetailSql = "INSERT INTO orderdetails (orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getInstance().getConnection();){
            conn.setAutoCommit(false);

            try(PreparedStatement ps = conn.prepareStatement(orderSql);) {
                ps.setInt(1, orderDto.getOrderNumber());
                ps.setDate(2, new Date(orderDto.getOrderDate().getTime()));
                ps.setDate(3, new Date(orderDto.getRequiredDate().getTime()));
                ps.setDate(4, orderDto.getShippedDate() != null ? new Date(orderDto.getShippedDate().getTime()) : null);
                ps.setString(5, orderDto.getStatus());
                ps.setString(6, orderDto.getComments());
                ps.setInt(7, orderDto.getCustomerNumber());
                ps.executeUpdate();
            }

            List<CreateOrderDto.OrderDetailDto> orderDetails = orderDto.getOrderDetails();
            try (PreparedStatement detailps = conn.prepareStatement(orderDetailSql)) {
                for(CreateOrderDto.OrderDetailDto detail : orderDetails) {
                    detailps.setInt(1, orderDto.getOrderNumber() );
                    detailps.setString(2, detail.getProductCode() );
                    detailps.setInt(3, detail.getQuantityOrdered() );
                    detailps.setDouble(4, detail.getPriceEach() );
                    detailps.setInt(5, detail.getOrderLineNumber() );
                    detailps.addBatch();
                }
                detailps.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement ps1 = connection.prepareStatement("delete from orders where orderNumber = ?");
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();

            PreparedStatement ps2 = connection.prepareStatement("delete from orderDetails where orderNumber = ?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();

        } catch (SQLException e) {
            System.out.println("ERROR al intentar eliminar un Order por su ID.\n");
            e.printStackTrace();
        }
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
