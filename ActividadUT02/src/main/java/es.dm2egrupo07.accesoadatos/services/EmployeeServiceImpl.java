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

    @Override
    public Optional<Employee> findById(int employeeNumber) {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findById(employeeNumber);
    }

    @Override
    public List<Employee> findAll() {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDataAccess.update(employee);
    }

    @Override
    public void deleteById(int employeeNumber) {
        employeeDataAccess.deleteById(employeeNumber);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDataAccess.save(employee);
    }

    @Override
    public long count() {
        return employeeDataAccess.count();
    }

    @Override
    public boolean existsById(int employeeNumber) {
        return employeeDataAccess.existById(employeeNumber);
    }

}
