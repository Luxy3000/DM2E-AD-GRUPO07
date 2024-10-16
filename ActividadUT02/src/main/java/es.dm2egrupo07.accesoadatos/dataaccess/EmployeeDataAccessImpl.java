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

    @Override
    public Optional<Employee> findEmployee(int employeeNumber) {
        //Predefinimos la consulta
        String sql = "SELECT * FROM employee WHERE employee_number = ?";

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
    public Employee save(Employee employee) {
        //Verificar que el empleado no existe ya en la BBSS
        if(existById(employee.getEmployeeNumber())) {
            //Si ya existe se manda a update para guardar si hay algún cambio en el employees
            update(employee);
            return employee;
        }

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
            stmt.executeUpdate();
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Si hay algún error devuelve false
        return null;
    }

    @Override
    public Employee update(Employee employee) {
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
            stmt.executeUpdate();
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int employeeNumber) {
        //Preparamos la consulta SQL para eliminar un employee
        String sql = "DELETE FROM employees WHERE employee_number = ?";

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

    @Override
    public boolean existById(int employeeNumber) {
        String sql = "SELECT * FROM employees WHERE employee_number = ?";
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
