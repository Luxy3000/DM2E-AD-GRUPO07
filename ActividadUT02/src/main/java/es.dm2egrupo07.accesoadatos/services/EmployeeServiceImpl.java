package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dataaccess.EmployeeDataAccess;
import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDataAccess employeeDataAccess;

    //Constructor que recibe una abstracción de EmployeeDataAccess
    public EmployeeServiceImpl(EmployeeDataAccess employeeDataAccess) {
        this.employeeDataAccess = employeeDataAccess;
    }

    /**
     * Sobreescribe el metodo de la interfaz para delegar su
     * funcionamiento a la capa de acceso a datos para buscar
     * a un empleado mediante su número id.
     *
     * @param employeeNumber número identificador de empleado
     * @return Optional con el empleado o vacío si no existe.
     */
    @Override
    public Optional<Employee> findById(int employeeNumber) {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findById(employeeNumber);
    }

    /**
     * Sobreescribe el metodo de la interfaz para delegar
     * la búsqueda de todos los empleados a la capa
     * de acceso a datos.
     *
     * @return lista con todos los empleados
     */
    @Override
    public List<Employee> findAll() {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findAll();
    }

    /**
     * Sobreescrie el metodo de la interfaz para delegar la
     * actualización de un empleado de la BBDD a la capa de
     * acceso a datos.
     *
     * @param employee empleado a actualizar
     * @return empleado actualizado
     */
    @Override
    public Employee update(Employee employee) {
        return employeeDataAccess.update(employee);
    }

    /**
     * Sobreescribe el metodo de la interfaz para delegar el
     * borrado de un empleado de la BBDD a la capa de acceso a datos.
     *
     * @param employeeNumber número id del empleado a borrar
     */
    @Override
    public void deleteById(int employeeNumber) {
        employeeDataAccess.deleteById(employeeNumber);
    }

    /**
     * Sobreescribe el metodo de la interfaz para delegar el
     * gaurdado de un nuevo empleado en la BBDD a la capa de
     * acceso a datos.
     *
     * @param employee emplado a guardar
     * @return empleado guardado
     */
    @Override
    public Employee save(Employee employee) {
        return employeeDataAccess.save(employee);
    }

    /**
     * Delega la cuenta de los empleados en la BBDD a la capa
     * de acceso a datos.
     *
     * @return número de empleados en la BBDD
     */
    @Override
    public long count() {
        return employeeDataAccess.count();
    }

    /**
     * Sobreescribe el metodo de la interfaz para delegar la comprobación
     * de la existencia de un empleado por su número id en la BBDD a la
     * capa de acceso a datos.
     *
     * @param employeeNumber número id del empleado
     * @return true si el empleado existe, false si no existe.
     */
    @Override
    public boolean existsById(int employeeNumber) {
        return employeeDataAccess.existById(employeeNumber);
    }

}
