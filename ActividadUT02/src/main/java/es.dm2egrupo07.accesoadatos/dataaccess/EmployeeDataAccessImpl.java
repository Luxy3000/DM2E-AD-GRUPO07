package es.dm2egrupo07.accesoadatos.dataaccess;

import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//La implementacion de la interfaz da el cómo funcionan las acciones establecidas en la interfaz
//Indica cómo se hacen esas acciones definidas en la interfaz.
public class EmployeeDataAccessImpl implements EmployeeDataAccess {


    //Atributo para el pool de conexiones. Se inyecta a través del constructor
    private final ConnectionPool connectionPool;

    //Constructor que recibe el pool, permite reuso para mejor rendimiento
    public EmployeeDataAccessImpl() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    /**
     * Sobreescribe el metodo de la interfaz y
     * busca un empleado en la base de datos por su id.
     *
     * @param employeeNumber el número identificador del empleado
     * @return  un Optional que contiene el empleado si se encuentra,
     *          o vacío si no se encuentra.
     *
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     * */
    @Override
    public Optional<Employee> findById(int employeeNumber) {
        //Predefinimos la consulta
        String sql = "SELECT * FROM employees WHERE employeeNumber = ?";

        //Try with resources para hacer una conexión segura
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Asignar el valor del parámetro en la consulta
            stmt.setInt(1, employeeNumber);

            //Ejecutar la consulta y obtener resultado
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    //Si hay result, lo hacemos objeto Employee
                    return Optional.of(mapToEmployee(rs));
                }
            }
        }catch ( SQLException e ) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Sobreescribe el metodo en la interfaz y genera una lista
     * de todos los empleados en la base de datos.
     *
     * @return list de todos los empleados
     *
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     * */
    @Override
    public List<Employee> findAll() {
        //Lista para almacenar todos los empleados de la BBDD
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        //Ejecutamos la consulta
        try(Connection conn = connectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            //Iteramos sobre el resultado mapeando cada fila
            while (rs.next()) {
                employees.add(mapToEmployee(rs));
            }
        }catch ( SQLException e ){
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * Sobreescribe el metodo de la interfaz y agrega o actualiza
     * un empleado en la base de datos.
     *
     * @param employee empleado a agregar o actualizar
     * @return  empleado agregado o actualizado
     *
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     * */
    @Override
    public Employee save(Employee employee) {
        //Verificar que el empleado no existe ya en la BBSS
        if(existById(employee.getEmployeeNumber())) {
            //Si ya existe se manda a update para guardar si hay algún cambio en el employees
            update(employee);
            return employee;
        }

        //Definir la consulta SQL para insertar nuevo empleado
        String sql = "INSERT INTO employees (employeeNumber ,lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = connectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            //Mapeamos todos los valores
            stmt.setInt(1, employee.getEmployeeNumber());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getFirstName());
            stmt.setString(4, employee.getExtension());
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getOfficeCode());
            stmt.setInt(7, employee.getReportsTo());
            stmt.setString(8, employee.getJobTitle());

            //Si es exitoso el valor de stmt.executeUpdate() será un número mayor a 0 por lo que el return dará un true.
            stmt.executeUpdate();
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Si hay algún error devuelve false
        return null;
    }

    /**
     * Sobreescribe el metodo en la interfaz y actualiza un empleado
     * en la base de datos.
     *
     * @param employee empleado a actualizar
     * @return empleado actualizado
     *
     * @throws SQLException si ocurre algún problema al realizar la consulta SQL
     * */
    @Override
    public Employee update(Employee employee) {
        //Definir la consulta SQL para actualizar
        String sql =  "UPDATE employees SET lastName = ?,firstName = ?,extension = ?,email = ?,officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?";

        try(Connection conn = connectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            //Asignamos los valores
            stmt.setString(1, employee.getLastName());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getExtension());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getOfficeCode());
            stmt.setInt(6, employee.getReportsTo());
            stmt.setString(7, employee.getJobTitle());
            stmt.setInt(8, employee.getEmployeeNumber());

            //Devuelve true si funciona
            stmt.executeUpdate();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sobreescribe el metodo de a interfaz y elimina un empleado
     * de la base de datos.
     *
     * @param employeeNumber número identificatibo del empleado
     *
     * @throws SQLException si ocurre algún problema con la consulta SQL
     * */
    @Override
    public void deleteById(int employeeNumber) {
        //Preparamos la consulta SQL para eliminar un employee
        String sql = "DELETE FROM employees WHERE employeeNumber = ?";

        try(Connection conn = connectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Damos valores a la consulta
            stmt.setInt(1, employeeNumber);

            //Devulvemos true si funciona
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sobreescribe el metodo en la interfaz y cuenta todos los empleados en la base de datos.
     *
     * @return número de empleados en la base de datos
     *
     * @throws SQLException si ocurre algún problema con la consulta SQL
     * */
    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM employees";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if(rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sobreescribe el metodo en la interfaz y busca un empleado en
     * la base de datos por su número identificativo de empleado.
     *
     * @param employeeNumber número identificatibo de empleado.
     * @return true si existe en la base de datos, false si no existe.
     *
     * @throws SQLException si ocurre algún problema en la consulta SQL
     * */
    @Override
    public boolean existById(int employeeNumber) {
        String sql = "SELECT * FROM employees WHERE employeeNumber = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, employeeNumber);
            try(ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Función privada para el mapeo del ResultSet a un objeto Employee
     * */
    private Employee mapToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employeeNumber"),
                rs.getString("lastName"),
                rs.getString("firstName"),
                rs.getString("extension"),
                rs.getString("email"),
                rs.getString("officeCode"),
                rs.getInt("reportsTo"),
                rs.getString("jobTitle")
        );
    }
}
