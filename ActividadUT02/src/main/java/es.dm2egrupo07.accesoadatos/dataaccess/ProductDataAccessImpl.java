package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;

import java.sql.*;
import java.util.*;

public class ProductDataAccessImpl implements ProductDataAccess {

    /**
     * Sobreescribe el metodo de la interfaz y
     * cuenta el número total de productos en la base de datos.
     *
     * @return el número total de productos.
     *         Si ocurre un error durante la consulta, se lanza una excepción.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
    */
    @Override
    public long count() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from products");
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
     * Verifica si existe un producto en la base de datos con una id dada.
     *
     * @param id la id del producto a buscar.
     * @return true si el producto existe; false en caso contrario.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean existsById(String id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select count(*) from products where productCode = ?")) {
            ps.setString(1, id);
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
     * Busca un producto en la base de datos por su id.
     *
     * @param id la id del producto a buscar.
     * @return un Optional que contiene el producto si se encuentra,
     *         o vacío si no se encuentra.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public Optional<Product> findById(String id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from products where productCode = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRsProduct(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el producto por su ID", e);
        }
        return Optional.empty();
    }

    /**
     * Lista todos los productos de la base de datos.
     *
     * @return una lista de productos. Si ocurre un error durante la consulta,
     *         se lanza una excepción.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from products")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        products.add(mapRsProduct(rs));
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos", e);
        }
        return products;
    }

    /**
     * Guarda un producto en la base de datos. Si el producto ya existe,
     * se actualiza; de lo contrario, se crea uno nuevo.
     *
     * @param product el producto a guardar. Debe tener un código de producto válido.
     * @return el producto guardado, ya sea actualizado o creado.
     *
     * @throws RuntimeException si ocurre un error al crear o actualizar el producto.
     */
    @Override
    public Product save(Product product) {
        if (existsById(product.getProductCode())) {
            if (update(product)) {
                return product;
            } else {
                throw new RuntimeException("Error al actualizar el producto");
            }
        } else {
            if (create(product)) {
                return product;
            } else {
                throw new RuntimeException("Error al crear el producto");
            }
        }
    }


    /**
     * Elimina un producto de la base de datos por su id.
     *
     * @param id la id del producto a eliminar.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public void deleteById(String id) {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from products where productcode = ?")){
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar el producto", e);
        }
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param product el producto a guardar.
     * @return true si el producto se ha guardado correctamente; false en caso contrario.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    private boolean create(Product product) {

        String sqlSave = "INSERT INTO products (productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)){
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductLine());
            preparedStatement.setString(4, product.getProductScale());
            preparedStatement.setString(5, product.getProductVendor());
            preparedStatement.setString(6, product.getProductDescription());
            preparedStatement.setInt(7, product.getQuantityInStock());
            preparedStatement.setDouble(8, product.getBuyPrice());
            preparedStatement.setDouble(9, product.getMsrp());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param product el producto con los nuevos datos a guardar.
     * @return true si el producto se ha actualizado correctamente; false en caso contrario.
     *
     * @throws RuntimeException si ocurre un error al ejecutar la consulta SQL.
     */
    private boolean update(Product product) {
        String sqlUpdate =  "UPDATE products SET productName=?, productLine=?, productScale=?, productVendor=?, productDescription=?, quantityInStock=?, buyPrice=?, MSRP=? WHERE productCode=?";

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductLine());
            preparedStatement.setString(3, product.getProductScale());
            preparedStatement.setString(4, product.getProductVendor());
            preparedStatement.setString(5, product.getProductDescription());
            preparedStatement.setInt(6, product.getQuantityInStock());
            preparedStatement.setDouble(7, product.getBuyPrice());
            preparedStatement.setDouble(8, product.getMsrp());
            preparedStatement.setString(9, product.getProductCode());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    /**
     * Mapea un objeto ResultSet a un objeto Product.
     *
     * @param rs el ResultSet que contiene los datos del producto.
     * @return un objeto Product con los datos del ResultSet.
     *
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet.
     */
    private Product mapRsProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getString("productCode"),
            rs.getString("productName"),
            rs.getString("productLine"),
            rs.getString("productScale"),
            rs.getString("productVendor"),
            rs.getString("productDescription"),
            rs.getInt("quantityInStock"),
            rs.getDouble("buyPrice"),
            rs.getDouble("MSRP")
        );

    }

}
