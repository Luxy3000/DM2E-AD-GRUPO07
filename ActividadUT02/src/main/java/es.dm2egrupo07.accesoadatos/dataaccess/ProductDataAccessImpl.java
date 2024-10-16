package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Product;

import java.sql.*;
import java.util.*;

public class ProductDataAccessImpl implements ProductDataAccess {

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
            throw new RuntimeException("Error al buscar productos", e);
        }
        return Optional.empty();
    }

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

    @Override
    public Product save(Product product) {
        if (existsById(product.getProductCode())){

        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from products where productcode = ?")){
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            System.out.println("El producto con id: " + id + "se ha eliminado correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos", e);
        }
    }

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
            throw new RuntimeException("Error al buscar productos", e);
        }
    }

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
            throw new RuntimeException("Error al buscar productos", e);
        }
    }


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
