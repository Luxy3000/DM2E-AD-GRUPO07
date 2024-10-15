package es.dm2egrupo07.accesoadatos.dataaccess;

import com.zaxxer.hikari.HikariDataSource;
import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//La implementacion de la interfaz da el cómo funcionan las acciones establecidas en la interfaz
//Indica cómo se hacen esas acciones definidas en la interfaz.
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
    //Atributo para el pool de conexiones. Se inyecta a través del constructor
    private final ConnectionPool connectionPool;

    //Constructor que recibe el pool, permite reuso para mejor rendimiento
    public EmployeeDataAccessImpl(HikariDataSource dataSource) {
        this.connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public Employee findById(int employeeNumber) {
        //Predefinimos la consulta
        String sql = "SELECT * FROM employee WHERE employee_number = ?";

        //Try with resources para hacer una conexión segura
        try (Connection conn = connectionPool.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Asignar el valor del parámetro en la consulta
            stmt.setInt(1, employeeNumber);

            //Ejecutar la consulta y obtener resultado
            ResultSet rs = stmt.executeQuery();

            //Si hay result, lo hacemos objeto Employee
            if (rs.next()) {
                return mapToEmployee(rs);
            }
        }catch ( SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Employee> findAll() {
        //Lista para almacenar todos los empleados de la BBDD
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

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

    @Override
    public boolean save(Employee employee) {
        //Definir la consulta SQL para insertar nuevo empleado
        String sql = "INSERT INTO employees (employee_number,lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Si hay algún error devuelve false
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        //Definir la consulta SQL para actualizar
        String sql =  "UPDATE employees SET lastName = ?,firstName = ?,extension = ?,email = ?,officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employee_number = ?";

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
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int employeeNumber) {
        //Preparamos la consulta SQL para eliminar un employee
        String sql = "DELETE FROM employees WHERE employee_number = ?";

        try(Connection conn = connectionPool.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Damos valores a la consulta
            stmt.setInt(1, employeeNumber);

            //Devulvemos true si funciona
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Funcion privada para el mapeo del rs a un objeto Employee
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
