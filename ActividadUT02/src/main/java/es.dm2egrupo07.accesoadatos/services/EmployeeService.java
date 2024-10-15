package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.util.List;

//Interfaz para los métodos para trabajar con los empleados en la capa de servicios
public interface EmployeeService {

    //Buscar un empleado por su ID
    Employee findById(int employeeNumber);

    //Lista de todos los empleados
    List<Employee> findAll();

    //Actualizar indo de un empleado, true si funcionó
    boolean updateEmployee(Employee employee);

    //Borrar un empleado, true si funcinó
    boolean deleteEmployee(int employeeNumber);

    //Agregar un empleado, true si funcionó
    boolean addEmployee(Employee employee);

}
