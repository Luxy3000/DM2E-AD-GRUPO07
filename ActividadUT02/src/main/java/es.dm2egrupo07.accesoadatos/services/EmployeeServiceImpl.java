package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.dataaccess.EmployeeDataAccess;
import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDataAccess employeeDataAccess;

    //Constructor que recibe una abstracción de EmployeeDataAccess
    public EmployeeServiceImpl(EmployeeDataAccess employeeDataAccess) {
        this.employeeDataAccess = employeeDataAccess;
    }

    @Override
    public Employee findById(int employeeNumber) {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findById(employeeNumber);
    }

    @Override
    public List<Employee> findAll() {
        //Se delega la búsqueda a la parte de acceso a datos
        return employeeDataAccess.findAll();
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDataAccess.update(employee);
    }

    @Override
    public boolean deleteEmployee(int employeeNumber) {
        return employeeDataAccess.delete(employeeNumber);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDataAccess.save(employee);
    }
}
