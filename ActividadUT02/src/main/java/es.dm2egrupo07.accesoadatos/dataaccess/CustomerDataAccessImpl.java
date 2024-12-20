package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa la interfaz CustomerDataAccess para darle funcionalidad a las acciones establecidas en ella.
 */
public class CustomerDataAccessImpl implements CustomerDataAccess {

    private static final String SQL_COUNT = "select count(*) from customers";
    private static final String SQL_FIND_BY_ID = "select customerNumber, customerName, contactLastName, \n" +
            "contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, \n" +
            "salesRepEmployeeNumber, creditLimit from customers where customerNumber = ?";
    private static final String SQL_FIND_ALL = "select * from customers";
    private static final String SQL_INSERT = "insert into customers values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update customers set contactLastName = ?, contactFirstName = ?, " +
            "phone = ? where customerNumber = ?";
    private static final String SQL_DELETE_BY_ID = "delete from customers where customerNumber = ?";

    private final ConnectionPool connectionPool;

    public CustomerDataAccessImpl() {

        this.connectionPool = ConnectionPool.getInstance();
    }

    /**
     * Sobreescribe el metodo de la interfaz para contar el número total de entidades de la tabla Customer.
     * @return número de entidades
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public long count() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_COUNT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rs.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("ERROR al intentar contar el número de entidades totales de Customer.\n");
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sobreescribe el metodo de la interfaz para encontrar un Customer en la base de datos según su número.
     * @param id
     * @return el Customer correspondiente con todos sus datos
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public Optional<Customer> findById(int id) {
        Optional<Customer> customer = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int customerNumber = rs.getInt("customerNumber");
                    String customerName = rs.getString("customerName");
                    String contactLastName = rs.getString("contactLastName");
                    String contactFirstName = rs.getString("contactFirstName");
                    String phone = rs.getString("phone");
                    String addressLine1 = rs.getString("addressLine1");
                    String addressLine2 = rs.getString("addressLine2");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String postalCode = rs.getString("postalCode");
                    String country = rs.getString("country");
                    int salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
                    double creditLimit = rs.getDouble("creditLimit");

                    Customer customer1 = new Customer(customerNumber, customerName, contactLastName, contactFirstName,
                            phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber,
                            creditLimit);

                    customer = Optional.of(customer1);
                }
            } catch (SQLException e) {
                System.out.println("ERROR al intentar buscar un Customer por su ID.\n");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("ERROR al intentar abrir la conexión para buscar un Customer por su ID.\n");
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * Sobreescribe el metodo de la interfaz para indicar si existe o no un Customer en la base de datos según su número.
     * @param id
     * @return false si no existe y true si existe
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public boolean existsById(int id) {
        boolean existe = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR al intentar comprobar si un Customer existe por su ID.\n");
            e.printStackTrace();
        }

        return existe;
    }

    /**
     * Sobreescribe el metodo de la interfaz para buscar todos los Customer que hay en la base de datos.
     * @return una lista de Customer con todos los que haya
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int customerNumber = rs.getInt("customerNumber");
                    String customerName = rs.getString("customerName");
                    String contactLastName = rs.getString("contactLastName");
                    String contactFirstName = rs.getString("contactFirstName");
                    String phone = rs.getString("phone");
                    String addressLine1 = rs.getString("addressLine1");
                    String addressLine2 = rs.getString("addressLine2");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String postalCode = rs.getString("postalCode");
                    String country = rs.getString("country");
                    int salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
                    double creditLimit = rs.getDouble("creditLimit");

                    Customer customer = new Customer(customerNumber, customerName, contactLastName, contactFirstName,
                            phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber,
                            creditLimit);

                    customers.add(customer);
                }
            } catch (SQLException e) {
                System.out.println("ERROR al intentar buscar todos los Customer.\n");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("ERROR al intentar abrir la conexión para buscar todos los Customer.\n");
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Sobreescribe el metodo de la interfaz para insertar y guardar un Customer nuevo en la base de datos con los
     * valores que le de el usuario.
     * @param customer
     * @return el Customer guardado
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public Customer save(Customer customer) {

        if (existsById(customer.getCustomerNumber())) {
            //Corrección -->
            return update(customer);
            //Corrección -->
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, customer.getCustomerNumber());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getContactLastName());
            ps.setString(4, customer.getContactFirstName());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddressLine1());
            ps.setString(7, customer.getAddressLine2());
            ps.setString(8, customer.getCity());
            ps.setString(9, customer.getState());
            ps.setString(10, customer.getPostalCode());
            ps.setString(11, customer.getCountry());
            ps.setInt(12, customer.getSalesRepEmployeeNumber());
            ps.setDouble(13, customer.getCreditLimit());

            //Corrección -->
            int filas = ps.executeUpdate();
            if(filas > 0){
                return findById(customer.getCustomerNumber()).orElseThrow();
            } else {
               throw new SQLException("Error al insertar el cliente: no se ha creado ninguna fila");
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERROR al intentar guardar o crear un Customer.", e);
        }
        //Corrección -->
    }

    /**
     * Sobreescribe el metodo de la interfaz para actualizar un Customer.
     * @param customer
     * @return el customer modificado
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    public Customer update(Customer customer) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, customer.getContactLastName());
            ps.setString(2, customer.getContactFirstName());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getCustomerNumber());

            //Corrección -->
            int lineas = ps.executeUpdate();
            return findById(customer.getCustomerNumber()).orElseThrow();

        } catch (SQLException e) {
            throw new RuntimeException("ERROR al intentar actualizar un Customer.", e);
        }
        //Corrección -->
    }

    /**
     * Sobreescribe el metodo de la interfaz para eliminar un Customer de la base de datos según su número.
     * @param id
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     */
    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("ERROR al intentar eliminar un Customer por su ID.\n");
            e.printStackTrace();
        }
    }
}
