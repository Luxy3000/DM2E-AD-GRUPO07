package es.dm2egrupo07.accesoadatos.services;

import es.dm2egrupo07.accesoadatos.entities.Employee;

import java.util.List;
import java.util.Optional;

//Interfaz para los métodos para trabajar con los empleados en la capa de servicios
public interface EmployeeService {

    //Buscar un empleado por su ID
    Optional<Employee> findById(int employeeNumber);

    //Lista de todos los empleados
    List<Employee> findAll();

    //Agregar un empleado, si ya existe
    Employee save(Employee employee);

    //Actualizar indo de un empleado
    Employee update(Employee employee);

    //Borrar un empleado mediante su núero
    void deleteById(int employeeNumber);

    //Dar longitud de la tabla
    long count();

    //Verificar que un empleado existe
    boolean existsById(int employeeNumber);

}
